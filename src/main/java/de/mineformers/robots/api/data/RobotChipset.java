package de.mineformers.robots.api.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

/**
 * R0b0ts
 * <p/>
 * RobotChipset
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RobotChipset {

    private String identifier;
    private String iconPath;
    private final ResourceLocation headTexture;
    private String unlocalizedName;
    private String description;
    private int range;

    public RobotChipset(String identifier, String unlocalizedName, String iconPath, int range, ResourceLocation headTexture) {
        this.identifier = identifier;
        this.unlocalizedName = unlocalizedName;
        this.iconPath = iconPath;
        this.range = range;
        this.headTexture = headTexture;
    }

    public void registerRecipe(ItemStack result, ItemStack blank) {

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

    public ResourceLocation getHeadTexture() {
        return headTexture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getRange() {
        return range;
    }

    @SideOnly(Side.CLIENT)
    public String getLocalizedDescription() {
        return StatCollector.translateToLocal("chipset." + description + ".description");
    }


    @SideOnly(Side.CLIENT)
    public String getLocalizedName() {
        return StatCollector.translateToLocal("chipset." + unlocalizedName + ".name");
    }

}
