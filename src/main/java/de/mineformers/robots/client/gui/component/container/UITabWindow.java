package de.mineformers.robots.client.gui.component.container;

import de.mineformers.robots.client.gui.component.decorative.UITooltip;
import de.mineformers.robots.client.gui.component.layout.UILayout;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.render.IDrawingHelper;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * GUISystem
 * <p/>
 * UITabWindow
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UITabWindow extends UIWindow {

    public class Tab {

        public String title;
        public IDrawingHelper icon;
        public UILayout layout;

        public Tab(String title, IDrawingHelper icon, UILayout layout) {
            this.title = title;
            this.icon = icon;
            this.layout = layout;
        }

    }

    private String selectedTab;
    private LinkedHashMap<String, Tab> tabs;
    private Orientation orientation;

    public UITabWindow() {
        super();
        tabs = new LinkedHashMap<String, Tab>();
        this.orientation = Orientation.VERTICAL_TOP;
    }

    public void addTab(String key, String title, IDrawingHelper icon, UILayout layout) {
        tabs.put(key, new Tab(title, icon, layout));
        if (selectedTab == null) {
            this.setSelectedTab(key);
        }
    }

    public void setTabOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Tab getSelectedTab() {
        return tabs.get(selectedTab);
    }

    public void setSelectedTab(String selectedTab) {
        this.setLayout(tabs.get(selectedTab).layout);
        if (layout != null)
            layout.initComponent();
        this.selectedTab = selectedTab;
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        super.mouseClick(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            ArrayList<String> keys = new ArrayList<String>();
            keys.addAll(tabs.keySet());
            for (int i = 0; i < keys.size(); i++) {
                if (!keys.get(i).equals(selectedTab)) {
                    GL11.glColor4f(1, 1, 1, 1);
                    if (isTabHovered(keys.get(i), mouseX, mouseY)) {
                        this.setSelectedTab(keys.get(i));
                    }
                }
            }
        }
    }

    public boolean isTabHovered(String key, int mouseX, int mouseY) {
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(tabs.keySet());
        int index = keys.indexOf(key);
        switch (orientation) {
            case VERTICAL_TOP:
                return this.isInsideRegion(mouseX, mouseY, screenX + index * (1 + 28), screenY - 28, screenX + index * (1 + 28) + 28, screenY);
            case VERTICAL_BOTTOM:
                return this.isInsideRegion(mouseX, mouseY, screenX + index * (1 + 28), screenY + height, screenX + index * (1 + 28) + 28, screenY + height + 28);
            case HORIZONTAL_LEFT:
                return this.isInsideRegion(mouseX, mouseY, screenX - 28, screenY + index * (1 + 28), screenX, screenY + index * (1 + 28) + 28);
            case HORIZONTAL_RIGHT:
                return this.isInsideRegion(mouseX, mouseY, screenX + width, screenY + index * (1 + 28), screenX + width + 28, screenY + index * (1 + 28) + 28);
        }
        return false;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(tabs.keySet());
        for (int i = 0; i < keys.size(); i++) {
            if (!keys.get(i).equals(selectedTab)) {
                GL11.glColor4f(1, 1, 1, 1);
                drawTab(keys.get(i));
            }
        }
        super.draw(mouseX, mouseY);
        GL11.glColor4f(1, 1, 1, 1);
        drawTab(selectedTab);
        for (int i = 0; i < keys.size(); i++) {
            Tab tab = tabs.get(keys.get(i));
            if (!keys.get(i).equals(selectedTab)) {
                GL11.glColor4f(1, 1, 1, 1);
                if (isTabHovered(keys.get(i), mouseX, mouseY)) {
                    UITooltip tooltip = new UITooltip();
                    tooltip.addLine(tab.title);
                    tooltip.draw(mouseX, mouseY);
                }
            }
        }
    }

    protected void drawTab(String key) {
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(tabs.keySet());
        int index = keys.indexOf(key);
        Tab tab = tabs.get(key);
        if (!key.equals(selectedTab)) {
            int x = 0;
            int y = 0;
            switch (orientation) {
                case VERTICAL_TOP:
                    x = screenX + index * (1 + 28);
                    y = screenY - 28;
                    this.drawRectangle(x, y, 0, 68, 28, 32);
                    tab.icon.draw(x + 6, y + 8);
                    break;
                case VERTICAL_BOTTOM:
                    x = screenX + index * (1 + 28);
                    y = screenY + height - 4;
                    this.drawRectangle(x, y, 0, 101, 28, 32);
                    tab.icon.draw(x + 6, y + 6);
                    break;
                case HORIZONTAL_LEFT:
                    x = screenX - 28;
                    y = screenY + index * (1 + 28);
                    this.drawRectangle(x, y, 0, 134, 32, 28);
                    tab.icon.draw(x + 8, y + 6);
                    break;
                case HORIZONTAL_RIGHT:
                    x = screenX + width - 4;
                    y = screenY + index * (1 + 28);
                    this.drawRectangle(x, y, 33, 134, 32, 28);
                    tab.icon.draw(x + 6, y + 6);
                    break;
            }
        } else {
            int uOff = (index > 0) ? 2 * 28 : 1 * 28;
            switch (orientation) {
                case VERTICAL_TOP:
                    this.drawRectangle(screenX + index * (1 + 28), screenY - 28, uOff, 68, 28, 32);
                    tab.icon.draw(screenX + index * (1 + 28) + 6, screenY - 20);
                    break;
                case VERTICAL_BOTTOM:
                    this.drawRectangle(screenX + index * (1 + 28), screenY + height - 4, uOff, 101, 28, 32);
                    tab.icon.draw(screenX + index * (1 + 28) + 6, screenY + height + 2);
                    break;
                case HORIZONTAL_LEFT:
                    this.drawRectangle(screenX - 28, screenY + index * (1 + 28), 0, 134 + uOff, 32, 28);
                    tab.icon.draw(screenX - 20, screenY + index * (1 + 28) + 6);
                    break;
                case HORIZONTAL_RIGHT:
                    this.drawRectangle(screenX + width - 4, screenY + index * (1 + 28), 33, 134 + uOff, 32, 28);
                    tab.icon.draw(screenX + width + 2, screenY + index * (1 + 28) + 6);
                    break;
            }
        }
    }
}
