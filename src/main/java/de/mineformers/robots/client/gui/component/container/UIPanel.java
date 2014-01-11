package de.mineformers.robots.client.gui.component.container;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.layout.UILayout;
import de.mineformers.robots.client.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * UIPanel
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIPanel extends UIComponent {

    protected UILayout layout;

    public UIPanel() {
        super(Global.getTexture());
    }

    @Override
    public void initComponent() {
        super.initComponent();
        if (layout != null)
            layout.initComponent();
    }

    public void setLayout(UILayout layout) {
        this.layout = layout;
        if (layout != null)
            layout.setParent(this);
    }

    public UILayout getLayout() {
        return layout;
    }

    @Override
    public void update(int mouseX, int mouseY) {
        if (layout != null)
            layout.update(mouseX, mouseY);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {
        super.drawBackground(mouseX, mouseY);
        if (layout != null)
            layout.drawBackground(mouseX, mouseY);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        super.drawForeground(mouseX, mouseY);
        if (layout != null)
            layout.drawForeground(mouseX, mouseY);
    }

    public void drawTooltips(int mouseX, int mouseY) {
        if (layout != null)
            layout.drawTooltips(mouseX, mouseY);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (layout != null) {
            layout.setScreenPos(screenX, screenY);
            layout.draw(mouseX, mouseY);
        }
        this.drawTooltips(mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        if (layout != null)
            return layout.getWidth();
        return width;
    }

    @Override
    public int getHeight() {
        if (layout != null)
            return layout.getHeight();
        return height;
    }

    public void mouseScroll(int dir, int mouseX, int mouseY) {
        if (layout != null)
            layout.mouseScroll(dir, mouseX, mouseY);
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        if (layout != null)
            layout.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void keyTyped(char keyChar, int keyCode) {
        if (layout != null)
            layout.keyTyped(keyChar, keyCode);
    }
}
