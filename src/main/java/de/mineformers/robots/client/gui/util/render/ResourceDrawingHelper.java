package de.mineformers.robots.client.gui.util.render;

import de.mineformers.robots.client.gui.util.RenderHelper;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * ResourceDrawingHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ResourceDrawingHelper implements IDrawingHelper {

    private ResourceLocation resource;
    private int width, height;
    private float u, v;
    private float uMax, vMax;

    public ResourceDrawingHelper(ResourceLocation resource, int width, int height) {
        this(resource, width, height, 0, 0);
    }

    public ResourceDrawingHelper(ResourceLocation resource, int width, int height, int u, int v) {
        this(resource, width, height, u, v, u + width, v + height);
    }

    public ResourceDrawingHelper(ResourceLocation resource, int width, int height, int u, int v, int uMax, int vMax) {
        this.resource = resource;
        this.width = width;
        this.height = height;
        this.u = u;
        this.v = v;
        this.uMax = uMax;
        this.vMax = vMax;
    }

    public void draw(int x, int y) {
        RenderHelper.drawRectangleStretched(resource, x, y, u, v, width, height, uMax, vMax, true, 1);
    }

}
