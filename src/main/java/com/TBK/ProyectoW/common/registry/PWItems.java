package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.ProyectoWar;
import com.TBK.ProyectoW.common.items.Factions;
import com.TBK.ProyectoW.common.items.TemplateWarhammerItem;
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
                    props()));
    public static final RegistryObject<Item> WARHAMMER_CHEST = ITEMS.register("warhammer_chest",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST,
                    props()));
    public static final RegistryObject<Item> WARHAMMER_LEGGINGS= ITEMS.register("warhammer_leggings",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS,
                    props()));
    public static final RegistryObject<Item> WARHAMMER_BOOT = ITEMS.register("warhammer_boot",
            ()-> new WarHammerArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET,
                    props()));
    //TRIM
    public static final RegistryObject<Item> ROYAL_ZEALOT_TRIM_TEMPLATE = ITEMS.register("royal_zealot_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.ROYAL_ZEALOT));

    public static final RegistryObject<Item> SILVER_SKULL_TRIM_TEMPLATE = ITEMS.register("silver_skull_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.SILVER_SKULL));

    public static Item.Properties props(){
        return new Item.Properties().tab(PWCreativeModeTab.WARHAMMER_TAB);
    }
}
