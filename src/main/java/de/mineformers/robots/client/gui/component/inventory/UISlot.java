package de.mineformers.robots.client.gui.component.inventory;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * WidgetSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UISlot extends UIComponent {

    public UISlot(int width, int height) {
        super(Global.getTexture());
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        // Corners clockwise
        this.drawRectangle(screenX, screenY, 46, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY, 54, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY + height - 5, 54, 9, 5, 5);
        this.drawRectangle(screenX, screenY + height - 5, 46, 9, 5, 5);

        // Sides clockwise
        this.drawRectangleStretched(screenX + 5, screenY, 52, 1, width - 10, 5, 1, 5);
        this.drawRectangleStretched(screenX + width - 5, screenY + 5, 54, 7, 5,
                height - 10, 5, 1);
        this.drawRectangleStretched(screenX + 5, screenY + height - 5, 52, 9, width - 10,
                5, 1, 5);
        this.drawRectangleStretched(screenX, screenY + 5, 46, 7, 5, height - 10, 5, 1);

        // Canvas
        this.drawRectangleStretched(screenX + 5, screenY + 5, 52, 7, width - 10,
                height - 10, 1, 1);
    }

}
