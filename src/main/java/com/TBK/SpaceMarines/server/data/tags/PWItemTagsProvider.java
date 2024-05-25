package com.TBK.SpaceMarines.server.data.tags;

import com.TBK.SpaceMarines.SpaceMarines;
import com.TBK.SpaceMarines.common.registry.PWItemTags;
import com.TBK.SpaceMarines.common.registry.PWItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;

import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class PWItemTagsProvider extends ItemTagsProvider {

    public PWItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, SpaceMarines.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(PWItemTags.FACTION_ARMOR).add(PWItems.WARHAMMER_HELMET.get(),PWItems.WARHAMMER_CHEST.get(),PWItems.WARHAMMER_LEGGINGS.get(),PWItems.WARHAMMER_BOOT.get());
    }
}
