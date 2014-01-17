package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.item.ItemModule;
import de.mineformers.robots.module.data.CraftingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * R0b0ts
 * <p/>
 * PacketSetRecipe
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketSetRecipe extends PacketBase {

    private ShapedRecipes recipe;

    public PacketSetRecipe() {

    }

    public PacketSetRecipe(ShapedRecipes recipe) {
        this.recipe = recipe;
    }

    @Override
    public void write(ByteArrayDataOutput out) {
        NBTTagCompound data = new NBTTagCompound();
        NBTTagCompound resultTag = new NBTTagCompound();
        if (recipe.getRecipeOutput() != null)
            recipe.getRecipeOutput().writeToNBT(resultTag);
        data.setCompoundTag("Result", resultTag);
        NBTTagList inputTag = new NBTTagList();
        for (int currentIndex = 0; currentIndex < recipe.recipeItems.length; ++currentIndex) {
            if (recipe.recipeItems[currentIndex] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) currentIndex);
                recipe.recipeItems[currentIndex].writeToNBT(compound);
                inputTag.appendTag(compound);
            }
        }
        data.setTag("Input", inputTag);
        NBTBase.writeNamedTag(data, out);
    }

    @Override
    public void read(ByteArrayDataInput in) {
        NBTTagCompound data = (NBTTagCompound) NBTBase.readNamedTag(in);
        ItemStack result = ItemStack.loadItemStackFromNBT(data.getCompoundTag("Result"));
        NBTTagList tagList = data.getTagList("Input");
        ItemStack[] input = new ItemStack[9];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound compound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = compound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < input.length) {
                input[slotIndex] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
        this.recipe = new ShapedRecipes(3, 3, input, result);
    }

    @Override
    public void execute(EntityPlayer player, Side side) {
        if (side.isServer()) {
            if (player.getHeldItem() != null)
                if (player.getHeldItem().getItem() instanceof ItemModule) {
                    RobotModule module = RobotHelper.getModuleFromItemStack(player.getHeldItem());
                    NBTTagCompound moduleData = new NBTTagCompound();
                    if (module.getIdentifier().equals("crafting")) {
                        CraftingData data = (CraftingData) module.getData();
                        data.setResult(recipe.getRecipeOutput());
                        data.setInput(recipe.recipeItems);
                        data.writeToNBT(moduleData);
                    }
                    player.getHeldItem().getTagCompound().setCompoundTag("ModuleData", moduleData);
                }
        }
    }
}
