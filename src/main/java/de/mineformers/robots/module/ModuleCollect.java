package de.mineformers.robots.module;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.ai.AICollect;
import de.mineformers.robots.module.data.EmptyData;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ModuleCollect
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleCollect extends RobotModule {

    public ModuleCollect() {
        super("collect", "robots:collect", "robots:modules/collect");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank) {
        GameRegistry.addShapelessRecipe(result, blank, new ItemStack(Block.hopperBlock));
    }

    @Override
    public IModuleData getData() {
        return new EmptyData();
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity) {
        return new AICollect(entity);
    }
}
