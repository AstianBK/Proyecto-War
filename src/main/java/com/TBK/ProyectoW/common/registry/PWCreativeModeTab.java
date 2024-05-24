package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.common.registry.PWItems;
import com.google.common.collect.Ordering;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PWCreativeModeTab {

    static Comparator<ItemStack> stackComparator;
    public static final CreativeModeTab WARHAMMER_TAB = new CreativeModeTab("warhammer_tab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PWItems.WARHAMMER_HELMET.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> pItems) {
            super.fillItemList(pItems);
            PreOrdenInit();
            pItems.sort(stackComparator);
        }
    };

    public static void PreOrdenInit(){
        List<Item> itemList= Arrays.asList(
                PWItems.CERAMITE_INGOT.get(),
                PWItems.WARHAMMER_HELMET.get(),
                PWItems.WARHAMMER_CHEST.get(),
                PWItems.WARHAMMER_LEGGINGS.get(),PWItems.WARHAMMER_BOOT.get(),
                PWItems.ROYAL_ZEALOT_TRIM_TEMPLATE.get(),
                PWItems.SILVER_SKULL_TRIM_TEMPLATE.get(),
                PWItems.PROTO_SUIT_TRIM_TEMPLATE.get(),
                PWItems.INFERNAL_CHAOS_TRIM_TEMPLATE.get(),
                PWItems.HOLY_CRUSADER_TRIM_TEMPLATE.get(),
                PWItems.MARINE_ARMOR_SMITHING_TEMPLATE.get()
        );

        stackComparator= Ordering.explicit(itemList).onResultOf(ItemStack::getItem);
    }
}
