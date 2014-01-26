package de.mineformers.robots.client.gui.component.interaction;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.inventory.UISlot;
import de.mineformers.robots.client.gui.event.KeyTypedEvent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.system.Global;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * GUISystem
 * <p/>
 * WidgetTextBox
 * <p/>
 * WARNING: Don't use me yet, I'm very WIP! (I'm working, but my graphics are
 * just ugly)
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UITextBox extends UIComponent
{

    private String text;

    private UISlot slotWidget;
    private boolean useSlotBg;
    private boolean focused;
    private int renderStart;
    private int cursorPos;
    private int blinkTick;
    private int color;

    public UITextBox(int width, int height, String startText,
                     boolean useSlotBg)
    {
        super(Global.getTexture());
        this.width = width;
        this.height = height;
        this.text = startText;
        this.slotWidget = new UISlot(width, height);
        this.useSlotBg = useSlotBg;
        this.addListener(this);
        this.focused = false;
        this.cursorPos = startText.length();
        this.color = 0xe0e0e0;
    }

    public void setColor(Color color)
    {
        this.color = (0xFF0000 & (color.getRed() << 16)) | (0x00FF00 & (color.getGreen() << 8)) | (0x0000FF & color.getBlue());
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public Color getColorRGB()
    {
        return new Color((0xFF0000 & color) >> 16, (0x00FF00 & color) >> 8, (0x0000FF & color));
    }

    public int getColor()
    {
        return color;
    }

    public String getText()
    {
        return text;
    }

    public boolean isFocused()
    {
        return focused;
    }

    public void clear()
    {
        this.text = "";
        setCursorPos(0);
    }

    public void setCursorPos(int pos)
    {
        cursorPos = pos;
        int j = text.length();
        if (this.renderStart > j)
        {
            this.renderStart = j;
        }

        int k = this.getWidth();
        String s = this.mc.fontRenderer.trimStringToWidth(this.text.substring(this.renderStart), k);
        int l = s.length() + this.renderStart;

        if (pos == this.renderStart)
        {
            this.renderStart -= this.mc.fontRenderer.trimStringToWidth(this.text, k, true).length();
        }

        if (pos > l)
        {
            this.renderStart += pos - l;
        } else if (pos <= this.renderStart)
        {
            this.renderStart -= this.renderStart - pos;
        }

        if (this.renderStart < 0)
        {
            this.renderStart = 0;
        }

        if (this.renderStart > j)
        {
            this.renderStart = j;
        }
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        GL11.glColor4f(1, 1, 1, 1);

        if (!useSlotBg)
        {
            // Corners clockwise
            this.drawRectangle(screenX, screenY, 31, 16, 5, 5);
            this.drawRectangle(screenX + width - 5, screenY, 39, 16, 5, 5);
            this.drawRectangle(screenX + width - 5, screenY + height - 5, 39, 24, 5, 5);
            this.drawRectangle(screenX, screenY + height - 5, 31, 24, 5, 5);

            // Sides clockwise
            this.drawRectangleStretched(screenX + 5, screenY, 37, 16, width - 10, 5, 1, 5);
            this.drawRectangleStretched(screenX + width - 5, screenY + 5, 39, 22, 5,
                    height - 10, 5, 1);
            this.drawRectangleStretched(screenX + 5, screenY + height - 5, 37, 24,
                    width - 10, 5, 1, 5);
            this.drawRectangleStretched(screenX, screenY + 5, 31, 22, 5, height - 10, 5, 1);

            // Canvas
            this.drawRectangleStretched(screenX + 5, screenY + 5, 37, 22, width - 10,
                    height - 10, 1, 1);
        } else
        {
            slotWidget.setScreenPos(screenX, screenY);
            slotWidget.draw(mouseX, mouseY);
        }

        int posVisible = this.cursorPos - this.renderStart;
        String toDraw = this.mc.fontRenderer.trimStringToWidth(this.text.substring(this.renderStart), this.getWidth() - 4);
        boolean flag = posVisible >= 0 && posVisible <= toDraw.length();
        int x = this.screenX + 2;
        int y = this.screenY + (this.height - 8) / 2;

        if (toDraw.length() > 0)
        {
            String s = flag ? toDraw.substring(0, posVisible) : toDraw;
            x = this.mc.fontRenderer.drawStringWithShadow(s, x, y, color);
        }

        if (toDraw.length() > 0 && flag && posVisible < toDraw.length())
        {
            this.mc.fontRenderer.drawStringWithShadow(toDraw.substring(posVisible), x, y, color);
        }

        if (focused)
        {
            if (blinkTick >= 30)
            {
                int start = this.getStringWidth(toDraw) + screenX + 2;
                int index = posVisible;
                while (index > toDraw.length())
                    index -= 1;
                start -= this.getStringWidth(toDraw.substring(index));
                drawCursorVertical(start, y, 1, this.mc.fontRenderer.FONT_HEIGHT);
            }

            if (blinkTick >= 80)
            {
                blinkTick = 0;
            }
            blinkTick++;
        }
    }

    private void drawCursorVertical(int x, int y, int width, int height)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(getColorRGB().getRed(), getColorRGB().getGreen(), getColorRGB().getBlue(), 1F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double) x, (double) y, 0.0D);
        tessellator.addVertex((double) x, (double) y + height, 0.0D);
        tessellator.addVertex((double) x + width, (double) y + height, 0.0D);
        tessellator.addVertex((double) x + width, (double) y, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY)
    {
        return true;
    }

    @Subscribe
    public void onClick(MouseClickEvent event)
    {
        if (this.isInsideRegion(event.mouseX, event.mouseY, screenX, screenY, screenX + width, screenY + height))
        {
            switch (event.mouseButton)
            {
                case LEFT:
                    this.focused = true;
                    break;
                case RIGHT:
                    clear();
                    break;
            }
        } else
        {
            this.focused = false;
        }
    }

    @Subscribe
    public void onKeyTyped(KeyTypedEvent event)
    {
        if (focused)
        {
            switch (event.keyCode)
            {
                case Keyboard.KEY_LEFT:
                    this.setCursorPos(cursorPos - 1);
                    if (cursorPos < 0)
                        this.setCursorPos(0);
                    break;
                case Keyboard.KEY_RIGHT:
                    if (cursorPos == text.length())
                        break;
                    if (cursorPos == text.length() - 1)
                    {
                        this.cursorPos = text.length();
                        break;
                    }
                    this.setCursorPos(cursorPos + 1);
                    if (cursorPos >= text.length())
                        this.setCursorPos(text.length() - 1);
                    break;
                case Keyboard.KEY_BACK:
                    if (this.text.length() > 0)
                    {
                        if (this.text.length() > 1)
                            this.text = this.text.substring(0, cursorPos - 1)
                                    + this.text.substring(cursorPos);
                        else
                            this.text = "";
                        this.setCursorPos(cursorPos - 1);
                        if (cursorPos < 0)
                            this.setCursorPos(0);
                    }
                    break;
                case Keyboard.KEY_DELETE:
                    if (this.text.length() > 0)
                    {
                        if (this.text.length() > 1)
                        {
                            String second = this.text.substring(cursorPos + 1);
                            this.text = this.text.substring(0, cursorPos);
                            if (cursorPos != text.length() - 1)
                                text += second;
                        } else
                        {
                            if (this.cursorPos == 0)
                                this.text = "";
                        }
                    }
                    break;
                default:
                    if (ChatAllowedCharacters.isAllowedCharacter(event.keyChar))
                    {
                        this.text = text.substring(0, cursorPos)
                                + Character.toString(event.keyChar)
                                + text.substring(cursorPos);
                        this.setCursorPos(cursorPos + 1);
                    }
                    break;
            }
        }
    }

}
