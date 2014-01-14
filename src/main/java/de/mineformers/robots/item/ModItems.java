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
    public static Item buddy;
    public static Item chipset;
    public static Item robot;

    public static void init() {
        module = new ItemModule(ItemIds.MODULE);
        buddy = new ItemBuddy(ItemIds.BUDDY);
        chipset = new ItemChipset(ItemIds.CHIPSET);
        robot = new ItemRobot(ItemIds.ROBOT);

        GameRegistry.registerItem(module, Strings.MODULE_BASE_NAME);
        GameRegistry.registerItem(buddy, Strings.BUDDY_NAME);
        GameRegistry.registerItem(chipset, Strings.CHIPSET_BASE_NAME);
        GameRegistry.registerItem(robot, Strings.ROBOT_NAME);
    }

}
