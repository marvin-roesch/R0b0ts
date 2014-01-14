package de.mineformers.robots.util;

import de.mineformers.robots.api.Robot;
import de.mineformers.robots.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * PrivateRobotHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PrivateRobotHelper {

    public static ItemStack createItemStackFromRobot(String customName, Robot robot) {
        ItemStack stack = new ItemStack(ModItems.robot);
        stack.setTagCompound(new NBTTagCompound());
        if(customName != null)
            stack.setItemName(customName);
        stack.getTagCompound().setString("ModuleName", robot.getModule().getIdentifier());
        stack.getTagCompound().setString("ChipsetName", robot.getChipset().getIdentifier());
        stack.getTagCompound().setInteger("ArmorID", robot.getArmorId());
        return stack;
    }

}
