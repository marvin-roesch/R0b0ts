package de.mineformers.robots.client.gui.component.interaction;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.MouseButton;

/**
 * GUISystem
 * <p/>
 * UICheckBox
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UICheckBox extends UIComponent
{

    private boolean checked;
    private String label;

    public UICheckBox(String label)
    {
        super(Global.getTexture());
        this.label = label;
        this.addListener(this);
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        if (!isChecked())
        {
            this.drawRectangle(screenX, screenY, 20, 50, 16, 16);
        } else
        {
            this.drawRectangle(screenX, screenY, 38, 50, 16, 16);
        }

        if (isHovered(mouseX, mouseY))
            this.drawRectangle(0xFFFFFF, 0.5F, screenX + 1, screenY + 1, 14, 14);

        this.drawString(label, screenX + 18, screenY + (getHeight() - mc.fontRenderer.FONT_HEIGHT) / 2, 0x404040, false);
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

    public void toggle()
    {
        this.checked = !checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public boolean isChecked()
    {
        return checked;
    }

    @Subscribe
    public void onClick(MouseClickEvent event)
    {
        if (event.mouseButton == MouseButton.LEFT)
            this.toggle();
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY)
    {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + 16, screenY + 16);
    }

    @Override
    public int getWidth()
    {
        return 18 + this.getStringWidth(label);
    }

    @Override
    public int getHeight()
    {
        return 16;
    }
}
