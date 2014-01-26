package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import org.w3c.dom.Element;

/**
 * R0b0ts
 * <p/>
 * ManualPage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ManualPage extends UIAbsoluteLayout
{

    protected UILabel heading;

    private void init(String heading)
    {
        this.heading = new UILabel("\247l" + heading, true);
        this.addComponent(this.heading);
    }

    public void loadFromXML(Element element)
    {
        this.init(element.getElementsByTagName("heading").item(0).getTextContent());
    }

}
