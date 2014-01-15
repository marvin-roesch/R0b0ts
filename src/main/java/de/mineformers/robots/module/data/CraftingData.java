package de.mineformers.robots.module.data;

import de.mineformers.robots.api.data.IModuleData;
import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * CraftingData
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CraftingData implements IModuleData {

    private int test;

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("Test", test);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
    }
}
