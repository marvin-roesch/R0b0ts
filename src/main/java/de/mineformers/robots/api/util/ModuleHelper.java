package de.mineformers.robots.api.util;

import de.mineformers.robots.api.RobotModule;
import de.mineformers.robots.api.registry.ModuleRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * ModuleHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleHelper {

    public static RobotModule fromItemStack(ItemStack stack) {
        if (stack.hasTagCompound()) {
            String key = stack.getTagCompound().getString("ModuleName");
            RobotModule module = ModuleRegistry.instance().getModule(key);
            return module;
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("ModuleName", "blank");
            stack.setTagCompound(tag);
            return ModuleRegistry.instance().getModule("blank");
        }
    }

}
