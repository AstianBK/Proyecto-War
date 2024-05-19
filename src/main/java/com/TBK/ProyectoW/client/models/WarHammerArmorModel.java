package com.TBK.ProyectoW.client.models;

import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.example.client.model.armor.GeckoArmorModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WarHammerArmorModel<T extends WarHammerArmorItem> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return null;
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return null;
    }
}
