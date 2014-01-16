package de.mineformers.robots.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 * R0b0ts
 * <p/>
 * RecipeHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RecipeHelper {

    public static ShapedRecipes createShaped(ItemStack result, ItemStack... input) {
        ItemStack[] temp = input;
        if (input.length < 9) {
            temp = new ItemStack[9];
            for (int i = 0; i < input.length; i++) {
                temp[i] = input[i];
            }
        }
        return new ShapedRecipes(3, 3, temp, result);
    }

}
