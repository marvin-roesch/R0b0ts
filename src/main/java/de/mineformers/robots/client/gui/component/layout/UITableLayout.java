package de.mineformers.robots.client.gui.component.layout;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.Padding;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

/**
 * GUISystem
 * <p/>
 * UITableLayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UITableLayout extends UILayout<UITableLayout.TableLayoutConstraints> {

    public class TableLayoutConstraints extends UILayout.LayoutConstraints {

        public int row, column, rowSpan, columnSpan;
        public Padding padding;

        public TableLayoutConstraints(int row, int column, int rowSpan,
                                      int columnSpan, Padding padding) {
            this.row = row;
            this.column = column;
            this.rowSpan = rowSpan;
            this.columnSpan = columnSpan;
            this.padding = padding;
        }

    }

    @Override
    public void addComponent(UIComponent component) {
        addComponent(component, 0, 0);
    }

    public void addComponent(UIComponent component, int row, int column) {
        addComponent(component, row, column, 1, 1);
    }

    public void addComponent(UIComponent component, int row, int column, int rowSpan,
                             int columnSpan) {
        addComponent(component, row, column, rowSpan, columnSpan, new Padding(2, 2, 2, 2));
    }

    public void addComponent(UIComponent component, int row, int column, int rowSpan, int columnSpan, Padding padding) {
        components.add(component);
        constraints.add(new TableLayoutConstraints(row, column, rowSpan,
                columnSpan, padding));
        component.setParent(this);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);

        HashMap<Integer, Integer> widestColumns = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> highestRows = new HashMap<Integer, Integer>();

        for (int i = 0; i < constraints.size(); i++) {
            UIComponent component = components.get(i);
            TableLayoutConstraints tlc = constraints.get(i);
            if (widestColumns.get(tlc.column) != null) {
                if (component.getWidth() > components.get(
                        widestColumns.get(tlc.column))
                        .getWidth()) {
                    if (tlc.columnSpan == 1)
                        widestColumns.put(tlc.column, i);
                }
            } else {
                if (tlc.columnSpan == 1)
                    widestColumns.put(tlc.column, i);
            }
        }

        for (int i = 0; i < constraints.size(); i++) {
            UIComponent component = components.get(i);
            TableLayoutConstraints tlc = constraints.get(i);
            if (highestRows.get(tlc.row) != null) {
                if (component.getHeight() > components.get(
                        highestRows.get(tlc.row)).getHeight()) {
                    if (tlc.rowSpan == 1)
                        highestRows.put(tlc.row, i);
                }
            } else {
                if (tlc.rowSpan == 1)
                    highestRows.put(tlc.row, i);
            }
        }

        for (int i = 0; i < constraints.size(); i++) {
            UIComponent component = components.get(i);
            TableLayoutConstraints tlc = constraints.get(i);
            int yOff = 0;
            for (int j = 0; j < tlc.row; j++) {
                UIComponent comp = components.get(highestRows.get(j));
                Padding padding = constraints.get(j).padding;
                if (comp != null)
                    yOff += comp.getHeight() + padding.top + padding.bottom;
            }

            yOff += (components.get(highestRows.get(tlc.row)).getHeight() - component.getHeight()) / 2;

            int xOff = 0;
            for (int j = 0; j < tlc.column; j++) {
                UIComponent comp = components.get(widestColumns.get(j));
                Padding padding = constraints.get(j).padding;
                if (comp != null)
                    xOff += comp.getWidth() + padding.left + padding.right;
            }

            if (component.isVisible()) {
                GL11.glPushMatrix();
                component.setScreenPos(screenX + xOff + tlc.padding.left, screenY + yOff + tlc.padding.top);
                GL11.glColor4f(1, 1, 1, 1);
                component.draw(mouseX, mouseY);
                GL11.glPopMatrix();
            }
        }
    }

    @Override
    public int getWidth() {
        int width = 0;
        HashMap<Integer, Integer> widestColumns = new HashMap<Integer, Integer>();
        for (int i = 0; i < constraints.size(); i++) {
            UIComponent component = components.get(i);
            TableLayoutConstraints tlc = constraints.get(i);
            if (widestColumns.get(tlc.column) != null) {
                if (component.getWidth() > components.get(
                        widestColumns.get(tlc.column))
                        .getWidth()) {
                    if (tlc.columnSpan == 1)
                        widestColumns.put(tlc.column, i);
                }
            } else {
                if (tlc.columnSpan == 1)
                    widestColumns.put(tlc.column, i);
            }
        }
        for (int i : widestColumns.values()) {
            TableLayoutConstraints tlc = constraints.get(i);
            width += components.get(i).getWidth() + tlc.padding.right + tlc.padding.left;
        }
        return width;
    }

    @Override
    public int getHeight() {
        int height = 0;
        HashMap<Integer, Integer> highestRows = new HashMap<Integer, Integer>();
        for (int i = 0; i < constraints.size(); i++) {
            UIComponent component = components.get(i);
            TableLayoutConstraints tlc = constraints.get(i);
            if (highestRows.get(tlc.row) != null) {
                if (component.getHeight() > components.get(
                        highestRows.get(tlc.row)).getHeight()) {
                    if (tlc.rowSpan == 1)
                        highestRows.put(tlc.row, i);
                }
            } else {
                if (tlc.rowSpan == 1)
                    highestRows.put(tlc.row, i);
            }
        }
        for (int i : highestRows.values()) {
            TableLayoutConstraints tlc = constraints.get(i);
            height += components.get(i).getHeight() + tlc.padding.top + tlc.padding.bottom;
        }
        return height;
    }
}
