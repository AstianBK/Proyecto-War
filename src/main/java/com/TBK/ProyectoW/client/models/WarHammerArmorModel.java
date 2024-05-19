package com.TBK.ProyectoW.client.models;

import com.TBK.ProyectoW.ProyectoWar;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarHammerArmorModel<T extends WarHammerArmorItem> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(ProyectoWar.MODID,"geo/warhammer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation(ProyectoWar.MODID,"textures/armor/warhammer_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(ProyectoWar.MODID,"animations/warhammer_armor.animation.json");
    }
}
