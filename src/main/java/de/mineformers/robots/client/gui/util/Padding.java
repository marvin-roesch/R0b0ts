package de.mineformers.robots.client.gui.util;

/**
 * GUISystem
 * <p/>
 * Padding
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Padding
{

    public static Padding NONE = new Padding(0, 0);
    public static Padding ALL5 = new Padding(5, 5);
    public static Padding VERTICAL5 = new Padding(5, 0);
    public static Padding HORIZONTAL5 = new Padding(0, 5);

    public int top, right, bottom, left;

    public Padding(int top, int right, int bottom, int left)
    {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Padding(int vertical, int horizontal)
    {
        this(vertical, horizontal, vertical, horizontal);
    }

}
