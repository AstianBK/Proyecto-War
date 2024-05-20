package com.TBK.ProyectoW.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public abstract class GeoItemLayerRenderer<T extends Item & IAnimatable> {
    protected final IGeoRenderer<T> entityRenderer;

    public GeoItemLayerRenderer(IGeoRenderer<T> entityRendererIn) {
        this.entityRenderer = entityRendererIn;
    }

    protected void renderCopyModel(GeoModelProvider<T> modelProvider,
                                   ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource,
                                   int packedLight, T animatable, float partialTick, float red, float green, float blue) {
        renderModel(modelProvider, texture, poseStack, bufferSource, packedLight, animatable,
                partialTick, red, green, blue);

    }

    protected void renderModel(GeoModelProvider<T> modelProvider,
                               ResourceLocation texture, PoseStack poseStack, MultiBufferSource bufferSource,
                               int packedLight, T animatable, float partialTick, float red, float green, float blue) {
        RenderType renderType = getRenderType(texture);
        MultiBufferSource bufferSource1= Minecraft.getInstance().renderBuffers().bufferSource();

        getRenderer().render(modelProvider.getModel(modelProvider.getModelResource(animatable)),
                animatable, partialTick, renderType, poseStack, bufferSource1, bufferSource1.getBuffer(renderType),
                packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1);

    }

    public RenderType getRenderType(ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    public GeoModelProvider<T> getEntityModel() {
        return this.entityRenderer.getGeoModelProvider();
    }

    public IGeoRenderer<T> getRenderer(){
        return this.entityRenderer;
    }

    protected ResourceLocation getEntityTexture(T entityIn) {
        return this.entityRenderer.getTextureLocation(entityIn);
    }

    public abstract void render(GeoModel model, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn,
                                LivingEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                                float netHeadYaw, float headPitch);
}