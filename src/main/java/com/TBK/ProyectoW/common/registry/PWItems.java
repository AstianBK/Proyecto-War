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

    public static final RegistryObject<Item> MARINE_ARMOR_SMITHING_TEMPLATE = ITEMS.register("marine_armor_smithing_template",
            ()-> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ROYAL_ZEALOT_TRIM_TEMPLATE = ITEMS.register("royal_zealot_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.ROYAL_ZEALOT));

    public static final RegistryObject<Item> PROTO_SUIT_TRIM_TEMPLATE = ITEMS.register("proto_suit_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.PROTO_SUIT));

    public static final RegistryObject<Item> INFERNAL_CHAOS_TRIM_TEMPLATE = ITEMS.register("infernal_chaos_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.INFERNAL_CHAOS));

    public static final RegistryObject<Item> HOLY_CRUSADER_TRIM_TEMPLATE = ITEMS.register("holy_crusader_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.NONE));
    public static final RegistryObject<Item> SILVER_SKULL_TRIM_TEMPLATE = ITEMS.register("silver_skull_trim_template",
            ()-> new TemplateWarhammerItem(props(), Factions.SILVER_SKULL));

    public static Item.Properties props(){
        return new Item.Properties().tab(PWCreativeModeTab.WARHAMMER_TAB);
    }
}
