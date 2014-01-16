package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.WindowIngameManual;
import de.mineformers.robots.client.gui.component.decorative.UICraftingSpace;
import org.w3c.dom.Element;

/**
 * R0b0ts
 * <p/>
 * PageCrafting
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageCrafting extends PageText {

    private UICraftingSpace craftingSpace;

    private void init(String recipeKey) {
        int y = text.getHeight() + 4;
        this.craftingSpace = new UICraftingSpace();
        craftingSpace.setRecipe(WindowIngameManual.recipes.get(recipeKey));
        this.addComponent(craftingSpace, 23, y);
    }

    @Override
    public void loadFromXML(Element element) {
        super.loadFromXML(element);
        this.init(element.getElementsByTagName("recipe").item(0).getTextContent());
    }
}
