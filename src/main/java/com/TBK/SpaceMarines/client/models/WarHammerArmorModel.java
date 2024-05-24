package com.TBK.SpaceMarines.client.models;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarHammerArmorModel<T extends WarHammerArmorItem> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(SpaceMarines.MODID,"geo/warhammer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation(SpaceMarines.MODID,"textures/armor/warhammer_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(SpaceMarines.MODID,"animations/warhammer_armor.animation.json");
    }
}
