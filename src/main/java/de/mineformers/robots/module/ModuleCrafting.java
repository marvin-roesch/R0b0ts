package de.mineformers.robots.module;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.ai.AICrafting;
import de.mineformers.robots.module.data.CraftingData;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * R0b0ts
 * <p/>
 * ModuleCrafting
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleCrafting extends RobotModule
{

    public ModuleCrafting()
    {
        super("crafting", "robots:crafting", "robots:modules/crafting");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank)
    {
        GameRegistry.addShapelessRecipe(result, blank, new ItemStack(Block.workbench));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.openGui(R0b0ts.instance, 1, world, 0, 0, 0);
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public boolean validateStack(ItemStack stack)
    {
        CraftingData data = (CraftingData) getData();
        data.readFromNBT(stack.getTagCompound().getCompoundTag("ModuleData"));
        return data.getResult() != null;
    }

    @Override
    public IModuleData getData()
    {
        return new CraftingData();
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity)
    {
        return new AICrafting(entity);
    }
}
