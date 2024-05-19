package com.TBK.ProyectoW.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TemplateWarhammerItem extends Item {
    private final Factions faction;
    public TemplateWarhammerItem(Properties p_41383_,Factions faction) {
        super(p_41383_);
        this.faction=faction;
    }

    public Factions getFaction() {
        return this.faction;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("factions.decrip"));
        p_41423_.add(Component.translatable("factions."+this.faction.name()).withStyle(ChatFormatting.BLUE));
    }
}
