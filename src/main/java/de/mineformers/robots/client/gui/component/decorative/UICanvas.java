package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.render.IDrawingHelper;

/**
 * GUISystem
 * <p/>
 * UICanvas
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UICanvas extends UIComponent
{

    private IDrawingHelper drawingHelper;

    public UICanvas(IDrawingHelper drawingHelper, int width, int height)
    {
        super(Global.getTexture());
        this.drawingHelper = drawingHelper;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        drawingHelper.draw(screenX, screenY);
    }

}
