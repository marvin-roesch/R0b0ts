package de.mineformers.robots.client.gui;

import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.inventory.UIInfoTab;
import de.mineformers.robots.client.gui.component.layout.UIFlowLayout;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.render.ResourceDrawingHelper;
import de.mineformers.robots.util.LangHelper;
import de.mineformers.robots.util.ResourceHelper;

/**
 * R0b0ts
 * <p/>
 * WindowRobotFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WindowRobotFactory extends UIWindow {

    public WindowRobotFactory() {
        super.initComponent();
        UIFlowLayout infoLayout = new UIFlowLayout(100, 50);
        UILabel label = new UILabel("Produces robots");
        label.setColor(0xe0e0e0);
        infoLayout.addComponent(label);
        UIInfoTab info = new UIInfoTab(infoLayout, new ResourceDrawingHelper(ResourceHelper.getModResource("textures/gui/info.png"), 16, 16, 0, 0, 16, 16), Orientation.HORIZONTAL_LEFT, LangHelper.translate("gui.robots:label.information"));
        this.addInfoTab(info);
    }
}
