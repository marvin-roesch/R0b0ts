package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.RenderHelper;
import de.mineformers.robots.proxy.ClientProxy;
import org.lwjgl.util.Color;

/**
 * GUISystem
 * <p/>
 * UILabel
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UILabel extends UIComponent {

    private String text;
    private String fontRenderer;
    private int color;
    private boolean drawShadow;

    public UILabel(String text, String fontRenderer) {
        super(Global.getTexture());
        this.text = text;
        this.color = 0x404040;
        this.width = this.getStringWidth(text);
        this.setFontRenderer(fontRenderer);
    }

    public UILabel(String text) {
        this(text, "normal");
    }

    public void setFontRenderer(String fontRenderer) {
        this.fontRenderer = fontRenderer;
        this.height = (text.split("<br>").length + 1) * ((fontRenderer.equals("normal") ? mc.fontRenderer.FONT_HEIGHT : ClientProxy.smallFontRenderer.FONT_HEIGHT) + 1);
    }

    public void setDrawShadow(boolean drawShadow) {
        this.drawShadow = drawShadow;
    }

    public void setColor(Color color) {
        this.color = RenderHelper.getColorFromRGB(color);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Color getColorRGB() {
        return RenderHelper.getRGBFromColor(color);
    }

    public int getColor() {
        return color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        String[] splits = text.split("<br>");
        for (int i = 0; i < splits.length; i++) {
            if (fontRenderer.equals("normal"))
                mc.fontRenderer.drawString(splits[i], screenX, screenY + i * (mc.fontRenderer.FONT_HEIGHT + 1), color,
                        drawShadow);
            else
                ClientProxy.smallFontRenderer.drawString(splits[i], screenX, screenY + i * (ClientProxy.smallFontRenderer.FONT_HEIGHT + 1), color,
                        drawShadow);
        }
    }
}
