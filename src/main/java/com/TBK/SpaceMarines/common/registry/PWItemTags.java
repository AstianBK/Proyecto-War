package com.TBK.SpaceMarines.common.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PWItemTags {
    public static final TagKey<Item> FACTION_ARMOR=bind("faction_armor");
    private static TagKey<Item> bind(String p_203855_) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(p_203855_));
    }
}
