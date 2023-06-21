package com.mad_scientists.weird_science.content.item.component;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ComponentRenderer extends GeoItemRenderer<ComponentItem> {
    public ComponentRenderer() {
        super(new ComponentModel());
    }
}
