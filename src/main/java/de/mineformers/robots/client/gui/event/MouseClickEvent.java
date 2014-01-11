package de.mineformers.robots.client.gui.event;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.MouseButton;

/**
 * GUISystem
 * <p/>
 * MouseClickEvent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MouseClickEvent extends Event {

    public int mouseX, mouseY;
    public MouseButton mouseButton;

    public MouseClickEvent(UIComponent component, int mouseX, int mouseY, MouseButton mouseBtn) {
        super(component);
        this.mouseButton = mouseBtn;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

}
