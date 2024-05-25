package com.TBK.SpaceMarines.server;

import com.TBK.SpaceMarines.common.items.TemplateWarhammerItem;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.TBK.SpaceMarines.common.registry.PWtemProperties;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(PWtemProperties::register);
    }


}