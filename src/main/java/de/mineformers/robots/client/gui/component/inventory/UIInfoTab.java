package de.mineformers.robots.client.gui.component.inventory;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.layout.UILayout;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.RenderHelper;
import de.mineformers.robots.client.gui.util.render.IDrawingHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import java.util.EnumSet;

/**
 * GUISystem
 * <p/>
 * UIInfoTab
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIInfoTab extends UIComponent {

    private int speed = 16;
    private int minWidth = 24;
    private int minHeight = 26;

    private UILayout layout;
    private IDrawingHelper icon;
    private Orientation orientation;
    private boolean open;
    private int growth;
    private String title;
    private int maxWidth;
    private int maxHeight;
    private int color;

    public UIInfoTab(UILayout layout, IDrawingHelper icon, Orientation orientation) {
        this(layout, icon, orientation, "");
    }

    public UIInfoTab(UILayout layout, IDrawingHelper icon, Orientation orientation, String title) {
        super(Global.getTexture());
        this.layout = layout;
        layout.setParent(this);
        this.icon = icon;
        this.orientation = orientation;
        this.title = title;
        if (!EnumSet.of(Orientation.HORIZONTAL_LEFT, Orientation.HORIZONTAL_RIGHT).contains(orientation))
            this.orientation = Orientation.HORIZONTAL_RIGHT;
        this.maxWidth = this.getStringWidth(title) + 16 + 2 + 5;
        if (layout.getWidth() > maxWidth) {
            maxWidth = layout.getWidth() + 10;
        }
        this.maxHeight = 16 + 2 + layout.getHeight() + 10;
        this.width = minWidth;
        this.height = minHeight;
        this.addListener(this);
        growth = 1;
        color = 0x424242;
    }

    public String getTitle() {
        return title;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isOpen() {
        return open;
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

    @Override
    public void update(int mouseX, int mouseY) {
        if (open) {
            if (width != maxWidth) {
                width += speed * growth;
                if (width >= maxWidth)
                    width = maxWidth;

            }

            if (height != maxHeight) {
                height += speed * growth;
                if (height >= maxHeight)
                    height = maxHeight;
            }
        }

        if (growth < 0) {
            if (height != minHeight) {
                height += speed * growth;
                if (height <= minHeight)
                    height = minHeight;
            }

            if (width != minWidth) {
                width += speed * growth;
                if (width <= minWidth) {
                    width = minWidth;
                }
            }

            if (height == minHeight && width == minWidth) {
                growth = 1;
            }
        }
    }

    public void open() {
        UIWindow window = (UIWindow) getParent();
        window.closeAllInfoTabs(this.orientation);
        this.open = true;
    }

    public void close() {
        growth = -1;
        this.open = false;
    }

    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0)
            if (isHovered(mouseX, mouseY)) {
                if (!open)
                    this.open();
                else
                    this.close();
            }
        layout.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void onMouseScroll(int dir, int mouseX, int mouseY) {
        layout.mouseScroll(dir, mouseX, mouseY);
    }

    public void onKeyTyped(char keyChar, int keyCode) {
        layout.keyTyped(keyChar, keyCode);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    public boolean isClosedAndHovered(int mouseX, int mouseY) {
        if (!open)
            return isHovered(mouseX, mouseY);
        return false;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        GL11.glColor4f((1F / 255F) * getColorRGB().getRed(), (1F / 255F) * getColorRGB().getGreen(), (1F / 255F) * getColorRGB().getBlue(), 1f);
        if (orientation == Orientation.HORIZONTAL_RIGHT) {
            this.drawRectangle(screenX + width - 5, screenY, 39, 1, 5, 5);
            this.drawRectangle(screenX + width - 5, screenY + height - 5, 39, 9, 5, 5);

            this.drawRectangleStretched(screenX, screenY, 37, 1, width - 5, 5, 1, 5);
            this.drawRectangleStretched(screenX + width - 5, screenY + 5, 39, 7, 5,
                    height - 10, 5, 1);
            this.drawRectangleStretched(screenX, screenY + height - 5, 37, 9, width - 5,
                    5, 1, 5);

            this.drawRectangleStretched(screenX, screenY + 5, 37, 7, width - 5,
                    height - 10, 1, 1);
            GL11.glColor4f(1, 1, 1, 1);
            icon.draw(screenX + 2, screenY + 5);

            if (open) {
                if (width == maxWidth) {
                    this.drawString(title, screenX + 5 + 16,
                            screenY + 7 + ((16 - mc.fontRenderer.FONT_HEIGHT) / 2),
                            0xE1C92F, true);
                }
                if (height == maxHeight && width == maxWidth) {
                    layout.setScreenPos(screenX + 5, screenY + 16 + 7);
                    layout.draw(mouseX, mouseY);
                }
            }
        } else {
            this.drawRectangle(screenX, screenY, 31, 1, 5, 5);
            this.drawRectangle(screenX, screenY + height - 5, 31, 9, 5, 5);

            this.drawRectangleStretched(screenX + 5, screenY, 37, 1, width - 5, 5, 1, 5);
            this.drawRectangleStretched(screenX + 5, screenY + height - 5, 37, 9, width - 5,
                    5, 1, 5);
            this.drawRectangleStretched(screenX, screenY + 5, 31, 7, 5, height - 10, 5, 1);

            this.drawRectangleStretched(screenX + 5, screenY + 5, 37, 7, width - 5,
                    height - 10, 1, 1);
            GL11.glColor4f(1, 1, 1, 1);
            icon.draw(screenX + 6, screenY + 5);

            if (open) {
                if (width == maxWidth) {
                    this.drawString(title, screenX + 7 + 16,
                            screenY + 7 + ((16 - mc.fontRenderer.FONT_HEIGHT) / 2),
                            0xE1C92F, true);
                }
                if (height == maxHeight && width == maxWidth) {
                    layout.setScreenPos(screenX + 5, screenY + 16 + 7);
                    layout.draw(mouseX, mouseY);
                }
            }
        }
    }
}
