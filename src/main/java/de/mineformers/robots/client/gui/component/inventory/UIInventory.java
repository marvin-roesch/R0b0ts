package de.mineformers.robots.client.gui.component.inventory;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * WidgetInventory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIInventory extends UIComponent {

    private UISlot slot;

    public UIInventory(int slotsX, int slotsY) {
        super(Global.getTexture());
        slot = new UISlot(18, 18);
        this.width = slotsX;
        this.height = slotsY;
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    public void draw(int mouseX, int mouseY) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                slot.setScreenPos(screenX + i * 18, screenY + j * 18);
                slot.draw(mouseX, mouseY);
            }
        }
    }

    public void setSlots(int slotsX, int slotsY) {
        this.width = slotsX;
        this.height = slotsY;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return false;
    }

}
