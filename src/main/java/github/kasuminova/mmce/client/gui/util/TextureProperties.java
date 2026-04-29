package github.kasuminova.mmce.client.gui.util;

import net.minecraft.util.ResourceLocation;

public class TextureProperties {

    public ResourceLocation texture;
    public int u;
    public int v;
    public int width;
    public int height;

    public TextureProperties(ResourceLocation texture, int u, int v, int width, int height) {
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.width = width;
        this.height = height;
    }

}
