package com.TBK.ProyectoW.common.items;

import com.TBK.ProyectoW.ProyectoWar;
import com.google.common.collect.ImmutableMultimap;
import com.ibm.icu.impl.coll.Collation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.maps.MapBanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public enum Factions {
    NONE(null,false,false,false),
    ROYAL_ZEALOT("royal_zealot",true,false,false),
    SILVER_SKULL("silver_skull",true,true,false);

    private final String name;
    private final boolean detailGolden;
    private final boolean textureChange;
    private final boolean detailEye;

    Factions(String name,boolean detailGolden,boolean textureChange,boolean detailEye){
        this.name=name;
        this.detailGolden=detailGolden;
        this.textureChange=textureChange;
        this.detailEye=detailEye;
    }
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(ProyectoWar.MODID,"textures/armor/faction/"+this.name+"_trim.png");
    }
    public boolean hasDetailGolden(){
        return this.detailGolden;
    }
    public boolean hasDetailEyes(){
        return this.detailEye;
    }
    public boolean hasChangeTextures(){
        return this.textureChange;
    }
}
