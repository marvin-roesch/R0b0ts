package de.mineformers.robots.client.gui.component.layout;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.Padding;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * UIFlowLayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIFlowLayout extends UILayout<UILayout.LayoutConstraints>
{

    private Padding padding;

    public UIFlowLayout(int width, int height)
    {
        super();
        this.width = width;
        this.height = height;
        this.padding = Padding.ALL5;
    }

    public void setPadding(Padding padding)
    {
        this.padding = padding;
    }

    public Padding getPadding()
    {
        return padding;
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        for (int i = 0, j = 0, offX = 0, offY = 0, highest = 0; i < components.size(); i++, j++)
        {
            UIComponent component = components.get(i);
            int x = offX;
            int y = offY;
            offX += component.getWidth() + padding.right;
            if (component.getHeight() > highest)
                highest = component.getHeight();
            if (offX > width)
            {
                offX = 0;
                offY += highest + padding.bottom;
                j = 0;
                x = offX;
                y = offY;
            }
            component.setScreenPos(screenX + x, screenY + y);
            GL11.glColor4f(1, 1, 1, 1);
            component.draw(mouseX, mouseY);
        }
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }
}
