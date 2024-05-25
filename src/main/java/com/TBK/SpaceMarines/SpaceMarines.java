package com.TBK.SpaceMarines;

import com.TBK.SpaceMarines.common.registry.PWItems;
import com.TBK.SpaceMarines.common.registry.PWRecipeSerializer;
import com.TBK.SpaceMarines.server.data.tags.PWItemTagsProvider;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
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
        PWRecipeSerializer.RECIPE_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::dataSetup);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        generator.addProvider( includeServer,new PWItemTagsProvider(generator, new BlockTagsProvider(generator,MODID,existingFileHelper),existingFileHelper));
    }
}
