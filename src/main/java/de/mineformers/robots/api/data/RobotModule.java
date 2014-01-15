package de.mineformers.robots.api.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.entity.EntityRobot;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * R0b0ts
 * <p/>
 * RobotModule
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class RobotModule {

    private String identifier;
    private String iconPath;
    private String unlocalizedName;
    private Object[] recipe;

    public RobotModule(String identifier, String unlocalizedName, String iconPath) {
        this.identifier = identifier;
        this.unlocalizedName = unlocalizedName;
        this.iconPath = iconPath;
    }

    public void setRecipe(Object[] recipe) {
        this.recipe = recipe;
    }

    public Object[] getRecipe() {
        return recipe;
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

    public abstract EntityAIBase getAI(EntityRobot entity);

    public abstract IModuleData getData();

    @SideOnly(Side.CLIENT)
    public String getLocalizedName() {
        return StatCollector.translateToLocal("module." + unlocalizedName + ".name");
    }
}
