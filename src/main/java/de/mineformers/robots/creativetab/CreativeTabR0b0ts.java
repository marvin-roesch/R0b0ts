package de.mineformers.robots.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.robots.block.ModBlocks;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * R0b0ts
 * <p/>
 * CreativeTabR0b0ts
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CreativeTabR0b0ts extends CreativeTabs {

    public CreativeTabR0b0ts(int id) {
        super(id, Reference.MOD_ID);
    }

    @Override
    public int getTabIconItemIndex() {
        return ModBlocks.factoryController.blockID;
    }
}
