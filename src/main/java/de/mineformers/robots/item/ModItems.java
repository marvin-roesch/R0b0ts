package de.mineformers.robots.item;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.lib.ItemIds;
import de.mineformers.robots.lib.Strings;
import net.minecraft.item.Item;

/**
 * R0b0ts
 * <p/>
 * ModItems
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModItems {

    public static Item module;

    public static void init() {
        module = new ItemModule(ItemIds.MODULE);

        GameRegistry.registerItem(module, Strings.MODULE_BASE_NAME);
    }

}
