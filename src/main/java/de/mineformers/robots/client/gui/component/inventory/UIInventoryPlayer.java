package de.mineformers.robots.client.gui.component.inventory;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.util.LangHelper;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * WidgetInventoryPlayer
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIInventoryPlayer extends UIComponent
{

    private UIInventory widget;

    public UIInventoryPlayer()
    {
        super(Global.getTexture());
        this.widget = new UIInventory(9, 3);
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        this.drawString(LangHelper.translate("container.inventory"), screenX, screenY,
                0x404040, false);
        GL11.glColor4f(1, 1, 1, 1);
        widget.setScreenPos(screenX, screenY + 10);
        widget.setSlots(9, 3);
        widget.draw(mouseX, mouseY);

        widget.setScreenPos(screenX, screenY + 68);
        widget.setSlots(9, 1);
        widget.draw(mouseX, mouseY);
    }

}
