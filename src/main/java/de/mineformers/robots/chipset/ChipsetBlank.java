package de.mineformers.robots.chipset;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * R0b0ts
 * <p/>
 * ChipsetBlank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetBlank extends RobotChipset {

    public ChipsetBlank() {
        super("blank", "robots:blank", "robots:chipsets/blank", -1, ResourceHelper.getModResource("textures/entities/robot.png"));
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank) {
        GameRegistry.addRecipe(new ShapedOreRecipe(result, "rir", "igi", "rir", 'r', new ItemStack(Item.redstone), 'i', new ItemStack(Item.ingotGold), Character.valueOf('g'), "dyeGreen"));
    }

    @Override
    public String getLocalizedName() {
        return "\2477\247o" + super.getLocalizedName() + "\247r";
    }

}
