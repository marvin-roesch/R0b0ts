package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.RenderHelper;
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
    private int color;
    private boolean drawShadow;

    public UILabel(String text) {
        super(Global.getTexture());
        this.text = text;
        this.color = 0x404040;
        this.width = this.getStringWidth(text);
        this.height = this.mc.fontRenderer.FONT_HEIGHT;
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
        this.drawString(text, screenX, screenY, color, drawShadow);
    }
}
