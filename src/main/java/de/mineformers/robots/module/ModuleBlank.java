package de.mineformers.robots.module;

import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.entity.EntityRobot;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * R0b0ts
 * <p/>
 * ModuleBlank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleBlank extends RobotModule {

    public ModuleBlank() {
        super("blank", "robots:blank", "robots:modules/blank");
    }

    @Override
    public IModuleData getData() {
        return null;
    }

    @Override
    public EntityAIBase getAI(EntityRobot entity) {
        return null;
    }

    @Override
    public String getLocalizedName() {
        return "\2477\247o" + super.getLocalizedName() + "\247r";
    }
}
