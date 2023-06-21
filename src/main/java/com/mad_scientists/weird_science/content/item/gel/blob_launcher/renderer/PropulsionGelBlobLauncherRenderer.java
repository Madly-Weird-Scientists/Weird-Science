package com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer;

import com.mad_scientists.weird_science.content.item.gel.blob_launcher.GelBlobLauncherItem;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.GelBlobLauncherModel;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.PropulsionGelBlobLauncherModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PropulsionGelBlobLauncherRenderer extends GeoItemRenderer<GelBlobLauncherItem> {
    public PropulsionGelBlobLauncherRenderer() {
        super(new PropulsionGelBlobLauncherModel());
    }
}
