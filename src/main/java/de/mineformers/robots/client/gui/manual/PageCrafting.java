package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UICraftingSpace;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 * R0b0ts
 * <p/>
 * PageCrafting
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageCrafting extends ManualPage {

    private UILabel text;
    private UICraftingSpace craftingSpace;

    public PageCrafting(String heading, String text, ItemStack result, ItemStack[] items) {
        super(heading);
        this.text = new UILabel(text);
        int y = (text.split("<br>").length + 1) * (mc.fontRenderer.FONT_HEIGHT + 1) + 4;
        this.craftingSpace = new UICraftingSpace();
        craftingSpace.setRecipe(new ShapedRecipes(3, 3, items, result));
        this.addComponent(this.text, 0, mc.fontRenderer.FONT_HEIGHT + 2);
        this.addComponent(craftingSpace, 23, y);
    }

}
