package de.mineformers.robots.module;

import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.ai.AICrafting;
import de.mineformers.robots.module.data.CraftingData;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * R0b0ts
 * <p/>
 * ModuleCrafting
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleCrafting extends RobotModule {

    public ModuleCrafting() {
        super("crafting", "robots:crafting", "robots:modules/crafting");
    }

    @Override
    public IModuleData getData() {
        return new CraftingData();
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity) {
        return new AICrafting(entity);
    }
}
