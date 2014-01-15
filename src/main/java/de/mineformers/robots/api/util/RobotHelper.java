package de.mineformers.robots.api.util;

import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.Robot;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * RobotHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RobotHelper {

    public static Robot getRobotFromItemStack(ItemStack stack) {
        RobotModule module = getModuleFromItemStack(stack);
        RobotChipset chipset = getChipsetFromItemStack(stack);
        int armorId = stack.getTagCompound().hasKey("ArmorID") ? stack.getTagCompound().getInteger("ArmorID") : -1;
        stack.getTagCompound().setInteger("ArmorID", armorId);
        IModuleData data = module.getData();
        if(data != null)
            data.readFromNBT(stack.getTagCompound().getCompoundTag("ModuleData"));
        return new Robot(module, chipset, armorId, data);
    }

    public static RobotModule getModuleFromItemStack(ItemStack stack) {
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

    public static RobotChipset getChipsetFromItemStack(ItemStack stack) {
        if (stack.hasTagCompound()) {
            String key = stack.getTagCompound().getString("ChipsetName");
            RobotChipset chipset = ChipsetRegistry.instance().getChipset(key);
            return chipset;
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("ChipsetName", "blank");
            stack.setTagCompound(tag);
            return ChipsetRegistry.instance().getChipset("blank");
        }
    }

}
