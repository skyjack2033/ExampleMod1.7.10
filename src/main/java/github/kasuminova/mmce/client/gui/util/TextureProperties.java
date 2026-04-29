package github.kasuminova.mmce.client.gui.util;

import github.kasuminova.mmce.client.gui.GuiContainerDynamic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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

    public void render(RenderPos pos, GuiContainerDynamic<?> gui) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        float texW = 256F;
        float texH = 256F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(pos.x, pos.y + height, 0, (float) u / texW, (float) (v + height) / texH);
        tessellator.addVertexWithUV(pos.x + width, pos.y + height, 0, (float) (u + width) / texW, (float) (v + height) / texH);
        tessellator.addVertexWithUV(pos.x + width, pos.y, 0, (float) (u + width) / texW, (float) v / texH);
        tessellator.addVertexWithUV(pos.x, pos.y, 0, (float) u / texW, (float) v / texH);
        tessellator.draw();
    }

}
