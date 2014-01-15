package de.mineformers.robots.chipset;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.util.PrivateRobotHelper;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ChipsetAdvanced
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetAdvanced extends RobotChipset {

    public ChipsetAdvanced() {
        super("advanced", "robots:advanced", "robots:chipsets/advanced", 16, ResourceHelper.getModResource("textures/entities/chipset_advanced.png"));
        this.setDescription("robots:advanced");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank) {
        GameRegistry.addShapelessRecipe(result, PrivateRobotHelper.createChipsetStack(ChipsetRegistry.instance().getChipset("basic")), new ItemStack(Item.dyePowder, 1, 4));
    }

}
