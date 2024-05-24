package com.TBK.SpaceMarines.client.renderers;

import com.TBK.SpaceMarines.client.GeoItemLayerRenderer;
import com.TBK.SpaceMarines.client.layers.EyeLayer;
import com.TBK.SpaceMarines.client.layers.GoldenDetailsLayer;
import com.TBK.SpaceMarines.client.models.WarHammerArmorModel;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import software.bernie.geckolib3.compat.PatchouliCompat;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.EModelRenderCycle;
import software.bernie.geckolib3.util.RenderUtils;

import java.util.Arrays;
import java.util.List;

public class WarHammerArmorRenderer<T extends WarHammerArmorItem> extends GeoArmorRenderer<T> {

    protected final List<GeoItemLayerRenderer<T>> layerRenderers = new ObjectArrayList<>();
    
    public WarHammerArmorRenderer() {
        super(new WarHammerArmorModel<>());
        this.addLayer(new EyeLayer<>(this));
        this.addLayer(new GoldenDetailsLayer<>(this));
        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }

    public void render(float partialTick, PoseStack poseStack, VertexConsumer buffer, int packedLight) {
        GeoModel model = this.getGeoModelProvider().getModel(this.getGeoModelProvider().getModelResource(this.currentArmorItem));
        AnimationEvent animationEvent = new AnimationEvent(this.currentArmorItem, 0, 0,
                Minecraft.getInstance().getFrameTime(), false,
                Arrays.asList(this.itemStack, this.entityLiving, this.armorSlot));
        float ageInTicks = this.entityLiving.tickCount + partialTick;

        poseStack.pushPose();
        poseStack.translate(0, 24 / 16F, 0);
        poseStack.scale(-1, -1, 1);

        this.dispatchedMat = poseStack.last().pose().copy();

        this.getGeoModelProvider().setCustomAnimations(this.currentArmorItem, getInstanceId(this.currentArmorItem), animationEvent); // TODO change to setCustomAnimations in 1.20+
        setCurrentModelRenderCycle(EModelRenderCycle.INITIAL);
        fitToBiped();
        RenderSystem.setShaderTexture(0, getTextureLocation(this.currentArmorItem));
        float red = 1.0F;
        float green = 1.0F;
        float blue = 1.0F;
        if(this.itemStack.getItem() instanceof DyeableLeatherItem dyeableLeatherItem && dyeableLeatherItem.hasCustomColor(this.itemStack)){
            if(dyeableLeatherItem.getColor(this.itemStack)!=0){
                int i = dyeableLeatherItem.getColor(this.itemStack);
                red = (float)(i >> 16 & 255)/255;
                green = (float)(i >> 8 & 255)/255;
                blue = (float)(i & 255)/255;
            }
        }

        RenderType renderType = getRenderType(this.currentArmorItem, partialTick, poseStack, null, buffer, packedLight,
                getTextureLocation(this.currentArmorItem));

        MultiBufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        buffer = ItemRenderer.getArmorFoilBuffer(bufferSource,
                RenderType.armorCutoutNoCull(getTextureLocation(currentArmorItem)),
                false, itemStack.hasFoil());

        render(model, this.currentArmorItem, partialTick, renderType, poseStack, bufferSource, buffer, packedLight,
                OverlayTexture.NO_OVERLAY, red, green,
                blue, 1.0F);

        if (!this.entityLiving.isSpectator()) {
            for (GeoItemLayerRenderer<T> layerRenderer : this.layerRenderers) {
                renderLayer(model, poseStack, bufferSource, packedLight, this.entityLiving, 0, 0, partialTick, ageInTicks,
                        0, 0, bufferSource, layerRenderer);
            }
        }

        if (ModList.get().isLoaded("patchouli"))
            PatchouliCompat.patchouliLoaded(poseStack);

        poseStack.popPose();
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack poseStack, VertexConsumer buffer, int packedLight,
                                  int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.isTrackingXform()) {
            Matrix4f poseState = poseStack.last().pose();
            Vec3 renderOffset = getRenderOffset(this.currentArmorItem, 1);
            Matrix4f localMatrix = RenderUtils.invertAndMultiplyMatrices(poseState, this.dispatchedMat);

            bone.setModelSpaceXform(RenderUtils.invertAndMultiplyMatrices(poseState, this.renderEarlyMat));
            localMatrix.translate(new Vector3f(renderOffset));
            bone.setLocalSpaceXform(localMatrix);
        }

        poseStack.pushPose();
        RenderUtils.prepMatrixForBone(poseStack, bone);
        renderCubesOfBone(bone, poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        renderChildBones(bone, poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }

    protected void renderLayer(GeoModel model, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity animatable,
                               float limbSwing, float limbSwingAmount, float partialTick, float rotFloat, float netHeadYaw,
                               float headPitch, MultiBufferSource bufferSource2, GeoItemLayerRenderer<T> layerRenderer) {
        layerRenderer.render(model,this.armorSlot,poseStack, bufferSource, packedLight, animatable, limbSwing, limbSwingAmount, partialTick, rotFloat,
                netHeadYaw, headPitch);
    }

    public final boolean addLayer(GeoItemLayerRenderer<T> layer) {
        return this.layerRenderers.add(layer);
    }
}
