package com.TBK.ProyectoW.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;



@OnlyIn(Dist.CLIENT)
public class GoldenDetailsLayer<T extends LivingEntity,M extends EntityModel<T>> extends RenderLayer<T,M>{
    private final ResourceLocation LINKED_ARMOR=new ResourceLocation("textures/entity/creeper/creeper_armor.png");

    public GoldenDetailsLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }
    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}