package com.TBK.ProyectoW.common.registry;

import com.TBK.ProyectoW.ProyectoWar;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class PWtemProperties {
    public static void register() {

        ItemProperties.register(PWItems.WARHAMMER_HELMET.get(), new ResourceLocation(ProyectoWar.MODID, "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> {
            return 1;
        });
        ItemProperties.register(PWItems.WARHAMMER_CHEST.get(), new ResourceLocation(ProyectoWar.MODID, "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> {
            return 1;
        });
        ItemProperties.register(PWItems.WARHAMMER_LEGGINGS.get(), new ResourceLocation(ProyectoWar.MODID, "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> {
            return 1;
        });
        ItemProperties.register(PWItems.WARHAMMER_BOOT.get(), new ResourceLocation(ProyectoWar.MODID, "faction"), (p_239426_0_, p_239426_1_, p_239426_2_, intIn) -> {
            return 1;
        });

    }
}
