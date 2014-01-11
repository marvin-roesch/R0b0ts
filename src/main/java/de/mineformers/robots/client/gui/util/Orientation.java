package de.mineformers.robots.client.gui.util;

/**
 * GUISystem
 * <p/>
 * Orientation
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public enum Orientation {

    VERTICAL_TOP(0, 1), VERTICAL_BOTTOM(0, -1), HORIZONTAL_LEFT(1, 0), HORIZONTAL_RIGHT(-1, 0);

    public int xOff, yOff;

    private Orientation(int xOff, int yOff) {
        this.xOff = xOff;
        this.yOff = yOff;
    }

}
