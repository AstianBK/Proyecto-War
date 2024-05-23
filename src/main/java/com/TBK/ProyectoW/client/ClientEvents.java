package com.TBK.ProyectoW.client;

import com.TBK.ProyectoW.ProyectoWar;
import com.TBK.ProyectoW.client.renderers.WarHammerArmorRenderer;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import com.TBK.ProyectoW.common.registry.PWItems;
import com.TBK.ProyectoW.common.registry.PWtemProperties;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;


@Mod.EventBusSubscriber(modid = ProyectoWar.MODID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
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
