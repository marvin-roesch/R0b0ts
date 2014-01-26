package de.mineformers.robots.chipset;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ChipsetBasic
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetBasic extends RobotChipset
{

    public ChipsetBasic()
    {
        super("basic", "robots:basic", "robots:chipsets/basic", 8, ResourceHelper.getModResource("textures/entities/chipset_basic.png"));
        this.setDescription("robots:basic");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank)
    {
        GameRegistry.addShapelessRecipe(result, blank, new ItemStack(Item.redstone));
    }
}
