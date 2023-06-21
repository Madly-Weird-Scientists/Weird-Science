package com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer;

import com.mad_scientists.weird_science.content.item.gel.blob_launcher.GelBlobLauncherItem;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.AcidicGelBlobLauncherModel;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.PropulsionGelBlobLauncherModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AcidicGelBlobLauncherRenderer extends GeoItemRenderer<GelBlobLauncherItem> {
    public AcidicGelBlobLauncherRenderer() {
        super(new AcidicGelBlobLauncherModel());
    }
}
