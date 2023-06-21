package com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer;

import com.mad_scientists.weird_science.content.item.gel.blob_launcher.GelBlobLauncherItem;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.PropulsionGelBlobLauncherModel;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.RepulsionGelBlobLauncherModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RepulsionGelBlobLauncherRenderer extends GeoItemRenderer<GelBlobLauncherItem> {
    public RepulsionGelBlobLauncherRenderer() {
        super(new RepulsionGelBlobLauncherModel());
    }
}
