package de.mineformers.robots.module.data;

import de.mineformers.robots.api.data.IModuleData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * R0b0ts
 * <p/>
 * CraftingData
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CraftingData implements IModuleData {

    private ItemStack result;
    private ItemStack[] input;

    public CraftingData() {
        input = new ItemStack[9];
    }

    public IRecipe asRecipe() {
        return null;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public ItemStack getResult() {
        return result;
    }

    public void setInput(ItemStack[] input) {
        this.input = input;
    }

    public ItemStack[] getInput() {
        return input;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        if (result != null) {
            NBTTagCompound compound = new NBTTagCompound();
            result.writeToNBT(compound);
            tag.setCompoundTag("Result", compound);
        }
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < input.length; ++currentIndex) {
            if (input[currentIndex] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) currentIndex);
                input[currentIndex].writeToNBT(compound);
                tagList.appendTag(compound);
            }
        }
        tag.setTag("Input", tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        NBTTagList tagList = tag.getTagList("Input");
        input = new ItemStack[9];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound compound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = compound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < input.length) {
                input[slotIndex] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
        if (tag.hasKey("Result"))
            result = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Result"));
    }
}
