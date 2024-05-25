package com.TBK.SpaceMarines.common.registry;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.common.recipes.SmithingArmorRecipes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PWRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SpaceMarines.MODID);

    public static final RegistryObject<RecipeSerializer<SmithingArmorRecipes>> SMITHING_WARHAMMER_RECIPE = RECIPE_SERIALIZERS.register("smithing_warhammer", SmithingArmorRecipes.Serializer::new);


    static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, SpaceMarines.MODID);

}
