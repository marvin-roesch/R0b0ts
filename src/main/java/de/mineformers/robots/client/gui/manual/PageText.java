package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UILabel;
import org.w3c.dom.Element;

/**
 * R0b0ts
 * <p/>
 * PageText
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageText extends ManualPage
{

    protected UILabel text;

    private void init(String text)
    {
        this.text = new UILabel(text, true);
        this.addComponent(this.text, 0, this.mc.fontRenderer.FONT_HEIGHT + 2);
    }

    @Override
    public void loadFromXML(Element element)
    {
        super.loadFromXML(element);
        this.init(element.getElementsByTagName("text").item(0).getTextContent());
    }
}
