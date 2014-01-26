package de.mineformers.robots.api.data;

import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * IModuleData
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IModuleData
{

    public void writeToNBT(NBTTagCompound tag);

    public void readFromNBT(NBTTagCompound tag);

}
