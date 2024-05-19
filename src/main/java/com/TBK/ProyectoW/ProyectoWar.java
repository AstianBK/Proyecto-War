package com.TBK.ProyectoW;

import com.TBK.ProyectoW.common.registry.PWItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ProyectoWar.MODID)
public class ProyectoWar
{
    public static final String MODID = "proyecto_war";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ProyectoWar()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PWItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
