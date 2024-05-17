package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.ProyectoWar;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PWItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ProyectoWar.MODID);
}
