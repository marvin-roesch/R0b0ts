package de.mineformers.robots.client.gui.component.interaction;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.system.Global;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiButton
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIButton extends UIComponent {

    protected boolean enabled;

    protected String text;

    public UIButton(int width, int height, String text) {
        super(Global.getTexture());
        this.width = width;
        this.height = height;
        this.text = text;
        this.enabled = true;
        this.addListener(this);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        boolean hovering = isHovered(mouseX, mouseY);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int state = getHoverState(hovering);

        // Corners clockwise
        this.drawRectangle(screenX, screenY, 1 + 19 * state, 31, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY, 13 + 19 * state, 31, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY + height - 5, 13 + 19 * state, 43,
                5, 5);
        this.drawRectangle(screenX, screenY + height - 5, 1 + 19 * state, 43, 5, 5);

        // Sides clockwise
        /*this.drawRectangleStretched(screenX + 5, screenY, 7 + 19 * state, 31, width - 10, // Top
                5, 5, 5);
        this.drawRectangleStretched(screenX + 5, screenY, 7 + 19 * state, 31, width - 10, // Top
                5, 5, 5);
        this.drawRectangleStretched(screenX + width - 5, screenY + 5, 13 + 19 * state, 37, // Right
		5, height - 10, 5, 5);
        this.drawRectangleStretched(screenX + 5, screenY + height - 5, 7 + 19 * state, 43, // Bottom
                width - 10, 5, 5, 5);
        this.drawRectangleStretched(screenX, screenY + 5, 1 + 19 * state, 37, 5, // Left
                height - 10, 5, 5);*/


        this.drawRectangleRepeated(this.texture, screenX + 5, screenY, 7 + 19 * state, 31, 5, 5, width - 10, // Top
                5);
        this.drawRectangleRepeated(this.texture, screenX + width - 5, screenY + 5, 13 + 19 * state, 37, 5, 5, // Right
                5, height - 10);
        this.drawRectangleRepeated(this.texture, screenX + 5, screenY + height - 5, 7 + 19 * state, 43, 5, 5, // Bottom
                width - 10, 5);
        this.drawRectangleRepeated(this.texture, screenX, screenY + 5, 1 + 19 * state, 37, 5, 5, 5, // Left
                height - 10);

        // Canvas
        /*this.drawRectangleStretched(screenX + 5, screenY + 5, 7 + 19 * state, 37,
                width - 10, height - 10, 5, 5);*/
        this.drawRectangleRepeated(this.texture, screenX + 5, screenY + 5, 7 + 19 * state, 37,
                5, 5, width - 10, height - 10);

        int color = 0xe0e0e0;

        if (!this.enabled) {
            color = -0x5f5f60;
        } else if (hovering) {
            color = 0xffffa0;
        }

        this.drawString(text, screenX + ((width - this.getStringWidth(text)) / 2), screenY
                + ((height - 8) / 2), color, true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected int getHoverState(boolean hovering) {
        byte b0 = 1;

        if (!this.enabled) {
            b0 = 0;
        } else if (hovering) {
            b0 = 2;
        }

        return b0;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Subscribe
    public void onClick(MouseClickEvent event) {
        switch (event.mouseButton) {
            case LEFT:
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                break;
        }
    }

}
