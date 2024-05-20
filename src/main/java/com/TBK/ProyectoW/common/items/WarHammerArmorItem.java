package com.TBK.ProyectoW.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;


public class WarHammerArmorItem extends GeoArmorItem implements IAnimatable, DyeableLeatherItem {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private Factions faction=Factions.NONE;

    public WarHammerArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if(!p_41405_.isClientSide){
            this.setFaction(this.getFaction(p_41404_));
        }
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }

    public Factions getFaction(ItemStack stack) {
        Factions faction=Factions.NONE;
        CompoundTag nbt=stack.getOrCreateTag();
        if(nbt.contains("faction")){
            String name = nbt.getString("faction");
            faction=Factions.getForName(name.toUpperCase());
            this.faction=faction;
        }
        return faction;
    }

    public Factions getFaction() {
        return this.faction;
    }

    public void setFaction(Factions faction){
        this.faction=faction;
    }

    public void setFaction(Factions faction, ItemStack stack){
        this.faction=faction;
        this.saveFaction(stack,this.faction.name());
    }
    public CompoundTag saveFaction(ItemStack stack,String name){
        CompoundTag nbt=stack.getOrCreateTag();
        nbt.putString("faction",name);
        return nbt;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<WarHammerArmorItem>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("factions.decrip"));
        p_41423_.add(Component.translatable("factions."+this.getFaction(p_41421_).getName()).withStyle(ChatFormatting.BLUE));
    }
}
