package de.mineformers.robots.client.gui.component.container;


import de.mineformers.robots.client.gui.component.decorative.UITooltip;
import de.mineformers.robots.client.gui.component.inventory.UIInfoTab;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.Padding;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;

/**
 * GUISystem
 * <p/>
 * WidgetWindow
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIWindow extends UIPanel
{

    private Padding padding;
    private LinkedList<UIInfoTab> infoTabs;

    public UIWindow()
    {
        super();
        this.padding = Padding.ALL5;
        this.infoTabs = new LinkedList<UIInfoTab>();
    }

    public void setPadding(Padding padding)
    {
        this.padding = padding;
    }

    public Padding getPadding()
    {
        return padding;
    }

    public void addInfoTab(UIInfoTab tab)
    {
        this.infoTabs.add(tab);
        tab.setParent(this);
    }

    public void closeAllInfoTabs(Orientation orientation)
    {
        for (UIInfoTab tab : infoTabs)
        {
            if (tab.getOrientation() == orientation && tab.isOpen())
            {
                tab.close();
            }
        }
    }

    @Override
    public void update(int mouseX, int mouseY)
    {
        super.update(mouseX, mouseY);
        for (UIInfoTab tab : infoTabs)
        {
            tab.update(mouseX, mouseY);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        this.drawRectangle(screenX, screenY, 31, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY, 39, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY + height - 5, 39, 9, 5, 5);
        this.drawRectangle(screenX, screenY + height - 5, 31, 9, 5, 5);

        // Sides clockwise
        this.drawRectangleStretched(screenX + 5, screenY, 37, 1, width - 10, 5, 1, 5);
        this.drawRectangleStretched(screenX + width - 5, screenY + 5, 39, 7, 5,
                height - 10, 5, 1);
        this.drawRectangleStretched(screenX + 5, screenY + height - 5, 37, 9, width - 10,
                5, 1, 5);
        this.drawRectangleStretched(screenX, screenY + 5, 31, 7, 5, height - 10, 5, 1);

        // Canvas
        this.drawRectangleStretched(screenX + 5, screenY + 5, 37, 7, width - 10,
                height - 10, 1, 1);

        if (layout != null)
        {
            layout.setScreenPos(screenX + padding.left, screenY + padding.top);
            layout.draw(mouseX, mouseY);
            layout.drawTooltips(mouseX, mouseY);
        }

        int left = 0;
        int right = 0;
        for (UIInfoTab tab : infoTabs)
        {
            if (tab.getOrientation() == Orientation.HORIZONTAL_RIGHT)
            {
                tab.setScreenPos(screenX + width, screenY + 5 + right);
                right += tab.getHeight() + 2;
            } else
            {
                tab.setScreenPos(screenX - tab.getWidth(), screenY + 5 + left);
                left += tab.getHeight() + 2;
            }
            GL11.glColor4f(1, 1, 1, 1);
            tab.draw(mouseX, mouseY);
        }
        for (UIInfoTab tab : infoTabs)
        {
            if (tab.isClosedAndHovered(mouseX, mouseY))
            {
                UITooltip tooltip = new UITooltip();
                tooltip.addLine(tab.getTitle());
                tooltip.draw(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClick(mouseX, mouseY, mouseButton);
        for (UIInfoTab tab : infoTabs)
        {
            tab.onMouseClick(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void mouseScroll(int dir, int mouseX, int mouseY)
    {
        super.mouseScroll(dir, mouseX, mouseY);
        for (UIInfoTab tab : infoTabs)
        {
            tab.onMouseScroll(dir, mouseX, mouseY);
        }
    }

    @Override
    public void keyTyped(char keyChar, int keyCode)
    {
        super.keyTyped(keyChar, keyCode);
        for (UIInfoTab tab : infoTabs)
        {
            tab.onKeyTyped(keyChar, keyCode);
        }
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    public LinkedList<UIInfoTab> getInfoTabs()
    {
        return infoTabs;
    }
}
