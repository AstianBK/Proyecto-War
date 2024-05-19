package com.TBK.ProyectoW.client.renderers;

import com.TBK.ProyectoW.client.models.WarHammerArmorModel;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.compat.PatchouliCompat;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.EModelRenderCycle;

import java.util.Arrays;

public class WarHammerArmorRenderer<T extends WarHammerArmorItem> extends GeoArmorRenderer<T> {

    private final AnimatedGeoModel<T> modelProvider;

    public WarHammerArmorRenderer() {
        this(new WarHammerArmorModel<>());
    }
    public WarHammerArmorRenderer(AnimatedGeoModel<T> geoModel) {
        super(geoModel);
        this.modelProvider=geoModel;
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
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelResource(this.currentArmorItem));
        AnimationEvent animationEvent = new AnimationEvent(this.currentArmorItem, 0, 0,
                Minecraft.getInstance().getFrameTime(), false,
                Arrays.asList(this.itemStack, this.entityLiving, this.armorSlot));

        poseStack.pushPose();
        poseStack.translate(0, 24 / 16F, 0);
        poseStack.scale(-1, -1, 1);

        this.dispatchedMat = poseStack.last().pose().copy();

        this.modelProvider.setCustomAnimations(this.currentArmorItem, getInstanceId(this.currentArmorItem), animationEvent); // TODO change to setCustomAnimations in 1.20+
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

        if (ModList.get().isLoaded("patchouli"))
            PatchouliCompat.patchouliLoaded(poseStack);

        poseStack.popPose();
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if(this.itemStack.getItem() instanceof DyeableLeatherItem dyeableLeatherItem){
            int i = dyeableLeatherItem.getColor(this.itemStack);
            red = (float)(i >> 16 & 255);
            green = (float)(i >> 8 & 255);
            blue = (float)(i & 255);
        }
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
