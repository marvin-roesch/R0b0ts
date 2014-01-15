package de.mineformers.robots.module;

import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.ai.AICollect;
import de.mineformers.robots.module.data.EmptyData;
import net.minecraft.entity.ai.EntityAIBase;

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
    public IModuleData getData() {
        return new EmptyData();
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity) {
        return new AICollect(entity);
    }
}
