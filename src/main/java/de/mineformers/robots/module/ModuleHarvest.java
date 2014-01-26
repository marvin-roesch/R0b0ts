package de.mineformers.robots.module;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.ai.AIHarvest;
import de.mineformers.robots.module.data.EmptyData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ModuleHarvest
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleHarvest extends RobotModule
{

    public ModuleHarvest()
    {
        super("harvest", "robots:harvest", "robots:modules/harvest");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank)
    {
        GameRegistry.addShapelessRecipe(result, blank, new ItemStack(Item.wheat));
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity)
    {
        return new AIHarvest(entity);
    }

    @Override
    public IModuleData getData()
    {
        return new EmptyData();
    }
}
