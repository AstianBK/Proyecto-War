package com.TBK.SpaceMarines;

import com.TBK.SpaceMarines.common.registry.PWItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SpaceMarines.MODID)
public class SpaceMarines
{
    public static final String MODID = "space_marines";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SpaceMarines()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        PWItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }
}
