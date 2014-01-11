package de.mineformers.robots.item;

import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.lib.Reference;
import net.minecraft.item.ItemBlock;

/**
 * R0b0ts
 * <p/>
 * ItemBlockBase
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(int id, String name) {
        super(id);
        this.setUnlocalizedName(Reference.RESOURCE_PREFIX + name);
        this.setCreativeTab(R0b0ts.creativeTab);
    }

}
