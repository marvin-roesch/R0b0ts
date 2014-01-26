package de.mineformers.robots.client.gui.event;

import de.mineformers.robots.client.gui.component.UIComponent;

/**
 * GUISystem
 * <p/>
 * UIEvent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Event
{

    private UIComponent component;

    public Event(UIComponent component)
    {
        this.component = component;
    }

    public UIComponent getComponent()
    {
        return component;
    }
}
