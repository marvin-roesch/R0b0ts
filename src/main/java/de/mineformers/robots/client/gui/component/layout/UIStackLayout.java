package de.mineformers.robots.client.gui.component.layout;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.Padding;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * UIStackLayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIStackLayout extends UILayout<UILayout.LayoutConstraints> {

    private Padding padding;

    public UIStackLayout() {
        super();
        padding = Padding.VERTICAL5;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public Padding getPadding() {
        return padding;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        for (int i = 0, offY = 0; i < components.size(); i++) {
            UIComponent component = components.get(i);
            int x = screenX;
            int y = offY;
            offY += component.getHeight() + padding.bottom;
            component.setScreenPos(x, screenY + y);
            GL11.glColor4f(1, 1, 1, 1);
            component.draw(mouseX, mouseY);
        }
    }
}
