package com.TBK.ProyectoW.client.renderers;

import com.TBK.ProyectoW.client.models.WarHammerArmorModel;
import com.TBK.ProyectoW.common.items.WarHammerArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class WarHammerArmorRenderer<T extends WarHammerArmorItem> extends GeoArmorRenderer<T> {
    public WarHammerArmorRenderer() {
        super(new WarHammerArmorModel<>());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
