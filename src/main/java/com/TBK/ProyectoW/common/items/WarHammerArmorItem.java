package com.TBK.ProyectoW.common.items;

import com.TBK.ProyectoW.client.renderers.WarHammerArmorRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;


public class WarHammerArmorItem extends GeoArmorItem implements IAnimatable, DyeableLeatherItem {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private Factions faction=Factions.NONE;

    public WarHammerArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    public Factions getFaction(ItemStack stack) {
        Factions faction=Factions.NONE;
        if(stack.getOrCreateTag().contains("faction")){
            String name = stack.getOrCreateTag().getString("faction");
            faction=Factions.valueOf(name);
        }
        return faction;
    }

    public void setFaction(Factions faction, ItemStack stack){
        CompoundTag nbt=stack.getOrCreateTag();
        this.faction=faction;
        this.saveFaction(stack,faction.name());
    }
    public CompoundTag saveFaction(ItemStack stack,String name){
        CompoundTag nbt=stack.getOrCreateTag();
        nbt.putString(name,"faction");
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
        p_41423_.add(Component.translatable("factions."+this.faction.name()).withStyle(ChatFormatting.BLUE));

    }
}
