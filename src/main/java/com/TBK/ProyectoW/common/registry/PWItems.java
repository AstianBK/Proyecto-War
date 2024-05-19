package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.ProyectoWar;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PWItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProyectoWar.MODID);

    public static final RegistryObject<Item> WARHAMMER_HELMET = ITEMS.register("warhammer_helmet",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD,
                    new Item.Properties()));
    public static final RegistryObject<Item> WARHAMMER_CHEST = ITEMS.register("warhammer_chest",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST,
                    new Item.Properties()));
    public static final RegistryObject<Item> WARHAMMER_LEGGINGS= ITEMS.register("warhammer_leggings",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> WARHAMMER_BOOT = ITEMS.register("warhammer_boost",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET,
                    new Item.Properties()));
}
