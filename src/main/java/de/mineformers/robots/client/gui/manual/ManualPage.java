package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;

/**
 * R0b0ts
 * <p/>
 * ManualPage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ManualPage extends UIAbsoluteLayout {

    protected UILabel heading;

    public ManualPage(String heading) {
        super();
        this.heading = new UILabel("\247l" + heading);
        this.addComponent(this.heading);
    }

}
