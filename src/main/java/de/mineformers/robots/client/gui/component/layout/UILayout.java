package de.mineformers.robots.client.gui.component.layout;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.component.decorative.UITooltip;
import de.mineformers.robots.client.gui.event.KeyTypedEvent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.event.MouseScrollEvent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.MouseButton;

import java.util.LinkedList;

/**
 * GUISystem
 * <p/>
 * UILayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UILayout<T extends UILayout.LayoutConstraints> extends UIComponent
{

    public class LayoutConstraints
    {

    }

    protected LinkedList<UIComponent> components;
    protected LinkedList<T> constraints;
    protected UITooltip tooltip;

    public UILayout()
    {
        super(Global.getTexture());
        this.components = new LinkedList<UIComponent>();
        this.constraints = new LinkedList<T>();
        this.tooltip = new UITooltip();
    }

    @Override
    public void initComponent()
    {
        super.initComponent();

        for (UIComponent component : components)
            component.initComponent();
    }

    @Override
    public void update(int mouseX, int mouseY)
    {
        for (UIComponent component : components)
            component.update(mouseX, mouseY);
    }

    public void drawTooltips(int mouseX, int mouseY)
    {
        for (UIComponent component : components)
        {
            if (component instanceof UILayout)
            {
                ((UILayout) component).drawTooltips(mouseX, mouseY);
                continue;
            }
            if (component instanceof UIPanel)
            {
                ((UIPanel) component).drawTooltips(mouseX, mouseY);
                continue;
            }
            if (component.getTooltip() != null && !component.getTooltip().isEmpty())
            {
                tooltip.reset();
                if (component.isHovered(mouseX, mouseY) &&
                        this.isInsideRegion(mouseX, mouseY, component.getScreenX(), component.getScreenY(),
                                component.getScreenX() + component.getWidth(), component.getScreenY() + component.getHeight()))
                {
                    String[] lines = component.getTooltip().split("\n");
                    for (String line : lines)
                    {
                        tooltip.addLine(line);
                    }
                    tooltip.draw(mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void drawBackground(int mouseX, int mouseY)
    {
        super.drawBackground(mouseX, mouseY);

        for (int i = 0; i < components.size(); i++)
        {
            UIComponent component = components.get(i);
            component.drawBackground(mouseX, mouseY);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {
        super.drawForeground(mouseX, mouseY);

        for (int i = 0; i < components.size(); i++)
        {
            UIComponent component = components.get(i);
            component.drawForeground(mouseX, mouseY);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
    }

    public void addComponent(UIComponent component)
    {
        this.components.add(component);
        component.setParent(this);
    }

    public LinkedList<UIComponent> getComponents()
    {
        return components;
    }

    @Override
    public int getWidth()
    {
        int width = 0;
        for (UIComponent component : components)
        {
            width += component.getWidth();
        }
        return width;
    }

    @Override
    public int getHeight()
    {
        int height = 0;
        for (UIComponent component : components)
        {
            height += component.getHeight();
        }
        return height;
    }

    public void mouseScroll(int dir, int mouseX, int mouseY)
    {
        for (UIComponent component : components)
        {
            if (component.isVisible())
                if (component.isHovered(mouseX, mouseY))
                {
                    component.postEvent(new MouseScrollEvent(component, dir, mouseX, mouseY));
                } else if (component instanceof UIPanel)
                {
                    ((UIPanel) component).mouseScroll(dir, mouseX, mouseY);
                } else if (component instanceof UILayout)
                {
                    ((UILayout) component).mouseScroll(dir, mouseX, mouseY);
                }
        }
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton)
    {
        for (UIComponent component : components)
        {
            if (component.isVisible())
                if (component.isHovered(mouseX, mouseY))
                {
                    component.postEvent(new MouseClickEvent(component, mouseX, mouseY, MouseButton.values()[mouseButton]));
                } else if (component instanceof UIPanel)
                {
                    ((UIPanel) component).mouseClick(mouseX, mouseY, mouseButton);
                } else if (component instanceof UILayout)
                {
                    ((UILayout) component).mouseClick(mouseX, mouseY, mouseButton);
                }
        }
    }

    public void keyTyped(char keyChar, int keyCode)
    {
        for (UIComponent component : components)
        {
            if (component.isVisible())
            {
                if (component instanceof UIPanel)
                    ((UIPanel) component).keyTyped(keyChar, keyCode);
                else if (component instanceof UILayout)
                    ((UILayout) component).keyTyped(keyChar, keyCode);
                else
                    component.postEvent(new KeyTypedEvent(component, keyChar, keyCode));
            }
        }
    }
}
