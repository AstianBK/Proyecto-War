package com.TBK.SpaceMarines.client.layers;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.client.GeoItemLayerRenderer;
import com.TBK.SpaceMarines.client.renderers.WarHammerArmorRenderer;
import com.TBK.SpaceMarines.common.items.Factions;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;


@OnlyIn(Dist.CLIENT)
public class GoldenDetailsLayer<T extends WarHammerArmorItem> extends GeoItemLayerRenderer<T> {

    public GoldenDetailsLayer(IGeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    public void render(GeoModel model,EquipmentSlot slot, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        renderGoldenDetails(model,slot,pMatrixStack,pBuffer,pPackedLight,pLivingEntity,pLimbSwing,pLimbSwingAmount,pPartialTicks,pAgeInTicks,pNetHeadYaw,pHeadPitch);
    }

    public void renderGoldenDetails(GeoModel model,EquipmentSlot slot, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch){
        ItemStack stack=pLivingEntity.getItemBySlot(slot);
        if(stack.getItem() instanceof WarHammerArmorItem armor){
            ((WarHammerArmorRenderer<?>)getRenderer()).applySlot(slot);
            ResourceLocation texture=getTexture(armor.getFaction(stack));
            RenderType renderType = RenderType.armorCutoutNoCull(texture);
            VertexConsumer consumer=pBuffer.getBuffer(renderType);
            getRenderer().render(model,(T)armor,pPartialTicks,renderType,
                    pMatrixStack,pBuffer, consumer,pPackedLight, OverlayTexture.NO_OVERLAY,
                    1.0F,1.0F,1.0F,1.0F);
        }
    }

    public ResourceLocation getTexture(Factions factions){
        String path="base";
        if(factions.hasDetailGolden()){
            path=factions.getName();
        }
        return new ResourceLocation(SpaceMarines.MODID,"textures/armor/golden_details/"+path+".png");
    }
}