package de.mineformers.robots.plugin.nei;

import codechicken.lib.vec.Rectangle4i;
import codechicken.nei.api.INEIGuiAdapter;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.inventory.UIInfoTab;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiContainer;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * GUISystem
 * <p/>
 * NEIGuiHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class NEIGuiHandler extends INEIGuiAdapter
{

    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h)
    {
        Rectangle4i rect;
        if ((gui instanceof WidgetGuiContainer) && ((WidgetGuiContainer) gui).getPanel() instanceof UIWindow)
        {
            UIWindow panel = (UIWindow) ((WidgetGuiContainer) gui).getPanel();
            rect = new Rectangle4i(x, y, w, h);
            for (UIInfoTab tab : panel.getInfoTabs())
            {
                Rectangle4i bounds = new Rectangle4i(tab.getScreenX(), tab.getScreenY(), tab.getWidth(), tab.getHeight());
                if (bounds.intersects(rect))
                {
                    return true;
                }
            }
        }
        return false;
    }

}
