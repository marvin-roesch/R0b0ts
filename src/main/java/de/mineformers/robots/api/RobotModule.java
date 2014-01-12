package de.mineformers.robots.api;

import net.minecraft.entity.ai.EntityAIBase;

/**
 * R0b0ts
 * <p/>
 * RobotModule
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RobotModule {

    private String identifier;
    private String iconPath;
    private EntityAIBase aiTask;
    private String unlocalizedName;
    private Object[] recipe;

    public RobotModule(String identifier, String unlocalizedName, String iconPath, EntityAIBase aiTask) {
        this.identifier = identifier;
        this.unlocalizedName = unlocalizedName;
        this.iconPath = iconPath;
        this.aiTask = aiTask;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public EntityAIBase getAiTask() {
        return aiTask;
    }
}
