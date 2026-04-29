package github.kasuminova.ecoaeextension.client.gui;

import appeng.client.gui.AEBaseGui;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.common.container.ContainerEFabricatorPatternBus;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorPatternBus;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiEFabricatorPatternBus extends AEBaseGui {

    public static final ResourceLocation GUI_TEXTURE =
            new ResourceLocation(ECOAEExtension.MOD_ID, "textures/gui/efabricator_pattern_bus.png");

    public GuiEFabricatorPatternBus(final EFabricatorPatternBus owner, final EntityPlayer player) {
        super(new ContainerEFabricatorPatternBus(owner, player));
        this.xSize = 230;
        this.ySize = 232;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void drawFG(final int offsetX, final int offsetY, final int mouseX, final int mouseY) {
        this.fontRendererObj.drawStringWithShadow(I18n.format("gui.efabricator_pattern_bus.title"), 8, 10, 0x404040);
        this.fontRendererObj.drawString(I18n.format("gui.efabricator_pattern_bus.inventory"), 8 + 27, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    public void drawBG(final int offsetX, final int offsetY, final int mouseX, final int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
