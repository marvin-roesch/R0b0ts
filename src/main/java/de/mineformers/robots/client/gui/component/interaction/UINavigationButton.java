package de.mineformers.robots.client.gui.component.interaction;

import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * WidgetButtonPage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UINavigationButton extends UIButton
{

    public static final int TYPE_NEXT = 0;
    public static final int TYPE_PREV = 1;

    private int buttonType;

    public UINavigationButton(int buttonType)
    {
        super(10, 15, "");
        this.buttonType = buttonType;
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        boolean hovering = isHovered(mouseX, mouseY);
        int state = getHoverState(hovering);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawRectangle(screenX, screenY, state * 10, buttonType * 15, 10, 15);
    }

    protected int getHoverState(boolean hovering)
    {
        byte b0 = 0;

        if (!this.enabled)
        {
            b0 = 2;
        } else if (hovering)
        {
            b0 = 1;
        }

        return b0;
    }

}
