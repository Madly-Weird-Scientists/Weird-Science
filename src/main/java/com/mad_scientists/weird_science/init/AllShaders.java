package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.client.shockwave.ShaderReference;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.server.packs.resources.ResourceProvider;

import java.util.ArrayList;
import java.util.List;

public class AllShaders {
    private static final List<ShaderReference> SHADERS = new ArrayList<>();

    public static final ShaderReference
            SCAN_EFFECT = shader("scan_effect", DefaultVertexFormat.POSITION_TEX);


    private static ShaderReference shader(String shader, VertexFormat format) {
        ShaderReference result = new ShaderReference(shader, format);
        SHADERS.add(result);
        return result;
    }

    public static void reloadShaders(ResourceProvider resources) {
        SHADERS.forEach(shaderReference -> shaderReference.reload(resources));
    }
}
