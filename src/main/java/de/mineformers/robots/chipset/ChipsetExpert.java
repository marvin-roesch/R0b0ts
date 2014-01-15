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
 * ChipsetExpert
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetExpert extends RobotChipset {

    public ChipsetExpert() {
        super("expert", "robots:expert", "robots:chipsets/expert", 32, ResourceHelper.getModResource("textures/entities/chipset_expert.png"));
        this.setDescription("robots:expert");
    }

    @Override
    public void registerRecipe(ItemStack result, ItemStack blank) {
        GameRegistry.addShapelessRecipe(result, PrivateRobotHelper.createChipsetStack(ChipsetRegistry.instance().getChipset("advanced")), new ItemStack(Item.glowstone));
    }

}
