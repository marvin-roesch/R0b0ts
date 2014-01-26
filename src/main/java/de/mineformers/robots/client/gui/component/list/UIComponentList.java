package de.mineformers.robots.client.gui.component.list;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.event.KeyTypedEvent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;

public class UIComponentList extends UIList<UIAbsoluteLayout>
{
    public UIComponentList(int width, int height)
    {
        super(width, height);
    }

    @Override
    public void addItem(UIAbsoluteLayout item)
    {
        super.addItem(item);
    }

    @Subscribe
    @Override
    public void onClick(MouseClickEvent event)
    {
        super.onClick(event);

        for (int i = 0; i < items.size(); ++i)
        {
            UIAbsoluteLayout l = items.get(i);

            l.mouseClick(event.mouseX, event.mouseY, event.mouseButton.ordinal());
        }
    }

    @Subscribe
    @Override
    public void onKeyTyped(KeyTypedEvent event)
    {
        super.onKeyTyped(event);

        for (int i = 0; i < items.size(); ++i)
        {
            UIAbsoluteLayout l = items.get(i);

            l.keyTyped(event.keyChar, event.keyCode);
        }
    }

    @Override
    public int getItemHeight()
    {
        return 20;
    }

    @Override
    public void update(int mouseX, int mouseY)
    {
        super.update(mouseX, mouseY);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY)
    {
        super.drawBackground(mouseX, mouseY);

        for (int i = 0; i < items.size(); ++i)
        {
            UIComponent item = items.get(i);

            item.drawBackground(mouseX, mouseY);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {
        super.drawForeground(mouseX, mouseY);

        for (int i = 0; i < items.size(); ++i)
        {
            UIComponent item = items.get(i);

            item.drawForeground(mouseX, mouseY);
        }
    }

    @Override
    protected void drawItem(UIAbsoluteLayout item, int x, int y, int width,
                            int height)
    {

        item.setScreenPos(x, y);
        item.draw(mouseX, mouseY);

    }
}
