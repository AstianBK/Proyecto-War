package com.TBK.SpaceMarines.common.recipes;

import com.TBK.SpaceMarines.common.items.TemplateWarhammerItem;
import com.TBK.SpaceMarines.common.items.WarHammerArmorItem;
import com.TBK.SpaceMarines.common.registry.PWRecipeSerializer;
import com.google.gson.JsonObject;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;


public class SmithingArmorRecipes extends UpgradeRecipe{
    private final ResourceLocation id;
    public final Ingredient base;
    public final Ingredient addition;
    public SmithingArmorRecipes(ResourceLocation p_44523_, Ingredient p_44524_, Ingredient p_44525_) {
        super(p_44523_,p_44524_,p_44525_,ItemStack.EMPTY);
        this.id=p_44523_;
        this.base=p_44524_;
        this.addition=p_44525_;
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return this.base.test(p_44002_.getItem(0)) && this.addition.test(p_44002_.getItem(1));
    }

    @Override
    public ItemStack assemble(Container p_44001_) {
        ItemStack itemstack = p_44001_.getItem(0);
        if (this.base.test(itemstack)) {
            ItemStack itemstack1=p_44001_.getItem(1);
            ItemStack itemstack2=itemstack.copy();
            itemstack2.setCount(1);
            if(itemstack2.getItem() instanceof WarHammerArmorItem armor && itemstack1.getItem() instanceof TemplateWarhammerItem template){
                armor.setFaction(template.getFaction(),itemstack2);
                return itemstack2;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return p_43999_ * p_44000_ >= 2;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }
    public RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch((p_151284_) -> {
            return net.minecraftforge.common.ForgeHooks.hasNoElements(p_151284_);
        });
    }
    public boolean isAdditionIngredient(ItemStack p_44536_) {
        return this.addition.test(p_44536_);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PWRecipeSerializer.SMITHING_WARHAMMER_RECIPE.get();
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public static class Serializer implements RecipeSerializer<SmithingArmorRecipes> {
        public SmithingArmorRecipes fromJson(ResourceLocation p_44562_, JsonObject p_44563_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "addition"));
            return new SmithingArmorRecipes(p_44562_, ingredient, ingredient1);
        }

        public SmithingArmorRecipes fromNetwork(ResourceLocation p_44565_, FriendlyByteBuf p_44566_) {
            Ingredient ingredient = Ingredient.fromNetwork(p_44566_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_44566_);
            return new SmithingArmorRecipes(p_44565_, ingredient, ingredient1);
        }

        public void toNetwork(FriendlyByteBuf p_44553_, SmithingArmorRecipes p_44554_) {
            p_44554_.base.toNetwork(p_44553_);
            p_44554_.addition.toNetwork(p_44553_);
        }
    }
}
