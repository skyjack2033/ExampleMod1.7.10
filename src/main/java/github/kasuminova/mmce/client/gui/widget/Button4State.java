package github.kasuminova.mmce.client.gui.widget;

import github.kasuminova.mmce.client.gui.util.TextureProperties;

public class Button4State extends Button {

    protected TextureProperties textureNormal;
    protected TextureProperties textureHovered;
    protected TextureProperties textureDisabled;
    protected TextureProperties textureActive;

    public TextureProperties getTextureNormal() {
        return textureNormal;
    }

    public void setTextureNormal(TextureProperties textureNormal) {
        this.textureNormal = textureNormal;
    }

    public TextureProperties getTextureHovered() {
        return textureHovered;
    }

    public void setTextureHovered(TextureProperties textureHovered) {
        this.textureHovered = textureHovered;
    }

    public TextureProperties getTextureDisabled() {
        return textureDisabled;
    }

    public void setTextureDisabled(TextureProperties textureDisabled) {
        this.textureDisabled = textureDisabled;
    }

    public TextureProperties getTextureActive() {
        return textureActive;
    }

    public void setTextureActive(TextureProperties textureActive) {
        this.textureActive = textureActive;
    }

}
