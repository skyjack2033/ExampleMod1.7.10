package github.kasuminova.mmce.client.gui.widget;

import github.kasuminova.mmce.client.gui.util.RenderPos;
import github.kasuminova.mmce.client.gui.widget.base.DynamicWidget;
import github.kasuminova.mmce.client.gui.widget.base.WidgetGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class MultiLineLabel extends DynamicWidget {

    private List<String> lines = new ArrayList<>();
    private List<String> displayLines;
    private boolean autoWrap;
    private float scale = 1.0F;
    private boolean verticalCentering;
    private boolean rightAligned;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private int color = 0x404040;

    public MultiLineLabel(List<String> lines) {
        this.lines = lines != null ? lines : new ArrayList<>();
    }

    public MultiLineLabel setAutoWrap(boolean autoWrap) {
        this.autoWrap = autoWrap;
        return this;
    }

    public MultiLineLabel setAutoRecalculateSize(boolean autoRecalculateSize) {
        return this;
    }

    public boolean isAutoWrap() {
        return autoWrap;
    }

    public MultiLineLabel setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public float getScale() {
        return scale;
    }

    public MultiLineLabel setVerticalCentering(boolean verticalCentering) {
        this.verticalCentering = verticalCentering;
        return this;
    }

    public boolean isVerticalCentering() {
        return verticalCentering;
    }

    public MultiLineLabel setRightAligned(boolean rightAligned) {
        this.rightAligned = rightAligned;
        return this;
    }

    public boolean isRightAligned() {
        return rightAligned;
    }

    @Override
    public MultiLineLabel setWidth(int width) {
        super.setWidth(width);
        return this;
    }

    @Override
    public MultiLineLabel setHeight(int height) {
        super.setHeight(height);
        return this;
    }

    @Override
    public MultiLineLabel setMargin(int margin) {
        this.marginTop = margin;
        this.marginBottom = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
        return this;
    }

    public MultiLineLabel setContents(List<String> contents) {
        this.lines = contents != null ? new ArrayList<>(contents) : new ArrayList<>();
        return this;
    }

    public MultiLineLabel setMargin(int top, int bottom, int left, int right) {
        this.marginTop = top;
        this.marginBottom = bottom;
        this.marginLeft = left;
        this.marginRight = right;
        return this;
    }

    public MultiLineLabel setColor(int color) {
        this.color = color;
        return this;
    }

    public int getColor() {
        return color;
    }

    @Override
    public void render(RenderPos renderPos, WidgetGui widgetGui) {
        if (!isVisible() || lines.isEmpty()) {
            return;
        }

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        int renderX = renderPos.posX() + getAbsX() + marginLeft;
        int renderY = renderPos.posY() + getAbsY() + marginTop;

        displayLines = new ArrayList<>(lines);

        if (autoWrap && width > 0) {
            displayLines = wrapText(fontRenderer);
        }

        int lineHeight = (int) (fontRenderer.FONT_HEIGHT * scale);
        int totalHeight = displayLines.size() * lineHeight;

        int startY = renderY;
        if (verticalCentering && height > 0) {
            startY = renderY + (height - totalHeight) / 2;
        }

        for (int i = 0; i < displayLines.size(); i++) {
            String line = displayLines.get(i);
            int y = startY + i * lineHeight;

            int x = renderX;
            if (rightAligned && width > 0) {
                int textWidth = (int) (fontRenderer.getStringWidth(line) * scale);
                x = renderX + width - textWidth - marginRight;
            }

            fontRenderer.drawString(line, x, y, color);
        }
    }

    private List<String> wrapText(FontRenderer fontRenderer) {
        List<String> wrappedLines = new ArrayList<>();
        int availableWidth = width - marginLeft - marginRight;

        for (String line : lines) {
            if (fontRenderer.getStringWidth(line) <= availableWidth / scale) {
                wrappedLines.add(line);
            } else {
                StringBuilder currentLine = new StringBuilder();
                String[] words = line.split(" ");

                for (String word : words) {
                    String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
                    if (fontRenderer.getStringWidth(testLine) * scale <= availableWidth) {
                        currentLine = new StringBuilder(testLine);
                    } else {
                        if (currentLine.length() > 0) {
                            wrappedLines.add(currentLine.toString());
                            currentLine = new StringBuilder(word);
                        } else {
                            wrappedLines.add(word);
                        }
                    }
                }

                if (currentLine.length() > 0) {
                    wrappedLines.add(currentLine.toString());
                }
            }
        }

        return wrappedLines;
    }

}
