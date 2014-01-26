package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.TextHelper;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * GUISystem
 * <p/>
 * UITooltip
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UITooltip extends UIComponent
{

    private ArrayList<String> lines;

    public UITooltip()
    {
        super(Global.getTexture());
        this.lines = new ArrayList<String>();
        this.setZIndex(301);
    }

    public void addLine(String text)
    {
        lines.add(text);
    }

    public void reset()
    {
        lines.clear();
    }

    @Override
    public void update(int mouseX, int mouseY)
    {
        this.screenX = mouseX;
        this.screenY = mouseY;
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        int startX = mouseX + 5;
        int startY = mouseY + 5;
        width = mc.fontRenderer.getStringWidth(TextHelper.getLongestString(lines.toArray(new String[lines.size()]))) + 10;
        height = lines.size() * (2 + mc.fontRenderer.FONT_HEIGHT) + 8;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.drawRectangle(startX, startY, 1, 50, 5, 5);
        this.drawRectangle(startX, startY + height - 5, 1, 62, 5, 5);
        this.drawRectangle(startX + width - 5, startY, 13, 50, 5, 5);
        this.drawRectangle(startX + width - 5, startY + height - 5, 13, 62, 5, 5);

        this.drawRectangleStretched(startX + 5, startY, 7, 50, width - 10, 5, 5, 5);
        this.drawRectangleStretched(startX + width - 5, startY + 5, 13, 56, 5, height - 10, 5, 5);
        this.drawRectangleStretched(startX + 5, startY + height - 5, 7, 62, width - 9, 5, 5, 5);
        this.drawRectangleStretched(startX, startY + 5, 1, 56, 5, height - 10, 5, 5);

        this.drawRectangleStretched(startX + 5, startY + 5, 7, 56, width - 10, height - 10, 5, 5);

        GL11.glDisable(GL11.GL_BLEND);

        startX += 6;
        startY += 6;
        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            this.drawString(line, startX, startY + i * (2 + mc.fontRenderer.FONT_HEIGHT), 0xEEEEEE, false);
        }
    }
}
