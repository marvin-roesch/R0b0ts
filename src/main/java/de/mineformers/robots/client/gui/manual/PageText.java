package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UILabel;

/**
 * R0b0ts
 * <p/>
 * PageText
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageText extends ManualPage {

    private UILabel text;

    public PageText(String heading, String text) {
        super(heading);
        this.text = new UILabel(text);
        this.addComponent(this.text, 0, this.mc.fontRenderer.FONT_HEIGHT + 2);
    }

}
