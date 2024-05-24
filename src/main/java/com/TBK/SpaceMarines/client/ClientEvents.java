package com.TBK.SpaceMarines.client;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.client.renderers.WarHammerArmorRenderer;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.TBK.SpaceMarines.common.registry.PWItems;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;


@Mod.EventBusSubscriber(modid = SpaceMarines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @OnlyIn(Dist.CLIENT)
    public static void registerArmorRenderers(EntityRenderersEvent.AddLayers event){
        GeoArmorRenderer.registerArmorRenderer(WarHammerArmorItem.class, WarHammerArmorRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(RegisterColorHandlersEvent.Item event) {
        event.register((p_92708_, p_92709_) -> {
            return p_92709_ > 0 ? -1 : ((DyeableLeatherItem)p_92708_.getItem()).getColor(p_92708_);
        }, PWItems.WARHAMMER_HELMET.get(),PWItems.WARHAMMER_CHEST.get(),PWItems.WARHAMMER_LEGGINGS.get(),PWItems.WARHAMMER_BOOT.get());
    }

}
