package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.RenderHelper;
import de.mineformers.robots.client.gui.util.TextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
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

    private static final FontRenderer smallFontRenderer = new FontRenderer(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().renderEngine, true);
    private String text;
    private FontRenderer fontRenderer;
    private int color;
    private boolean drawShadow;

    public UILabel(String text, boolean useSmallRenderer) {
        this(text, useSmallRenderer ? smallFontRenderer : Minecraft.getMinecraft().fontRenderer);
    }

    public UILabel(String text, FontRenderer fontRenderer) {
        super(Global.getTexture());
        this.text = text.replace("\n", "").replace("\r", "");
        this.color = 0x404040;
        this.width = this.getStringWidth(TextHelper.getLongestString(text.split("<br>")));
        this.setFontRenderer(fontRenderer);
    }

    public UILabel(String text) {
        this(text, Minecraft.getMinecraft().fontRenderer);
    }

    public void setFontRenderer(FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
        this.height = (text.split("<br>").length + 1) * (fontRenderer.FONT_HEIGHT + 1);
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
            fontRenderer.drawString(splits[i], screenX, screenY + i * (mc.fontRenderer.FONT_HEIGHT + 1), color,
                    drawShadow);
        }
    }
}
