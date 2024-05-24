package com.TBK.SpaceMarines.client.layers;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.client.GeoItemLayerRenderer;
import com.TBK.SpaceMarines.client.renderers.WarHammerArmorRenderer;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class EyeLayer<T extends WarHammerArmorItem> extends GeoItemLayerRenderer<T> {
    public EyeLayer(IGeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(GeoModel model,EquipmentSlot slot, PoseStack matrixStackIn, MultiBufferSource pBuffer, int packedLightIn, LivingEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entityLivingBaseIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof WarHammerArmorItem armor){
            ((WarHammerArmorRenderer)getRenderer()).applySlot(EquipmentSlot.HEAD);
            ResourceLocation texture=getTexture();
            RenderType renderType = RenderType.eyes(texture);
            VertexConsumer consumer=pBuffer.getBuffer(renderType);

            getRenderer().render(model,(T)armor,partialTicks,renderType,
                    matrixStackIn,pBuffer, consumer,packedLightIn, OverlayTexture.NO_OVERLAY,
                    1.0F,1.0F,1.0F,1.0F);
        }
    }

    public ResourceLocation getTexture(){
        return new ResourceLocation(SpaceMarines.MODID,"textures/armor/eye_details.png");
    }
}
