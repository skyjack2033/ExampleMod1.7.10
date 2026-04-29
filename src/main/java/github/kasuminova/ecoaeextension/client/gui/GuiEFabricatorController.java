package github.kasuminova.ecoaeextension.client.gui;

import github.kasuminova.mmce.client.gui.GuiContainerDynamic;
import github.kasuminova.mmce.client.gui.widget.base.WidgetController;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;
import github.kasuminova.ecoaeextension.ECOAEExtension;
import github.kasuminova.ecoaeextension.client.gui.widget.efabricator.*;
import github.kasuminova.ecoaeextension.client.gui.widget.efabricator.event.EFGUIDataUpdateEvent;
import github.kasuminova.ecoaeextension.common.container.ContainerEFabricatorController;
import github.kasuminova.ecoaeextension.common.container.data.EFabricatorData;
import github.kasuminova.ecoaeextension.common.tile.ecotech.efabricator.EFabricatorController;
import lombok.Getter;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiEFabricatorController extends GuiContainerDynamic<ContainerEFabricatorController> {

    public static final ResourceLocation TEXTURES_BACKGROUND_LIGHT = new ResourceLocation(
            ECOAEExtension.MOD_ID, "textures/gui/efabricator_light.png");
    public static final ResourceLocation TEXTURES_BACKGROUND_DARK = new ResourceLocation(
            ECOAEExtension.MOD_ID, "textures/gui/efabricator_dark.png");
    public static final ResourceLocation TEXTURES_INVENTORY = new ResourceLocation(
            ECOAEExtension.MOD_ID, "textures/gui/efabricator_inventory.png");
    public static final ResourceLocation TEXTURES_ELEMENTS = new ResourceLocation(
            ECOAEExtension.MOD_ID, "textures/gui/efabricator_elements.png");

    @Getter
    private EFabricatorData data = null;

    public GuiEFabricatorController(final EFabricatorController controller, final EntityPlayer opening) {
        super(new ContainerEFabricatorController(controller, opening));
        this.xSize = 243;
        this.ySize = 202;
        this.widgetController = new WidgetController(WidgetGui.of(this));
        this.widgetController.addWidget(new TotalCraftedLabel().setAbsXY(5, 1));
        this.widgetController.addWidget(new CraftingStatusPanel().setAbsXY(7, 15));
        this.widgetController.addWidget(new ControlPanel().setAbsXY(7, 77));
        this.widgetController.addWidget(new HeatStatisticPanel().setAbsXY(184, 117));
        final TitleButtonLine buttonLine = new TitleButtonLine(false);
        this.widgetController.addWidget(buttonLine.setAbsXY(this.xSize - buttonLine.getWidth() - 1, 1));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES_BACKGROUND_DARK);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        drawModalRectWithCustomSizedTexture(x, y, 0, 0, 256, 202, 256, 256);
        this.mc.getTextureManager().bindTexture(TEXTURES_INVENTORY);
        drawModalRectWithCustomSizedTexture(x + 17, y + 118, 1, 120, 162, 76, 256, 256);
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }

    private static void drawModalRectWithCustomSizedTexture(final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight) {
        final float fw = 1.0F / textureWidth;
        final float fh = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0.0D, u * fw, (v + height) * fh);
        tessellator.addVertexWithUV(x + width, y + height, 0.0D, (u + width) * fw, (v + height) * fh);
        tessellator.addVertexWithUV(x + width, y, 0.0D, (u + width) * fw, v * fh);
        tessellator.addVertexWithUV(x, y, 0.0D, u * fw, v * fh);
        tessellator.draw();
    }

    public void onDataUpdate(final EFabricatorData data) {
        this.data = data;
        this.widgetController.postGuiEvent(new EFGUIDataUpdateEvent(this));
    }


}
