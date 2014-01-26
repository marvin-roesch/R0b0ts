package de.mineformers.robots.module;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.entity.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ModuleBlank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleBlank extends RobotModule
{

    public ModuleBlank()
    {
        super("blank", "robots:blank", "robots:modules/blank");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank)
    {
        GameRegistry.addShapedRecipe(result, "isi", "srs", "isi", 'r', new ItemStack(Item.redstone),
                'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Block.stone));
    }

    @Override
    public IModuleData getData()
    {
        return null;
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity)
    {
        return null;
    }

    @Override
    public String getLocalizedName()
    {
        return "\2477\247o" + super.getLocalizedName() + "\247r";
    }
}
