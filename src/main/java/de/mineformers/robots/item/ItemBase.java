package de.mineformers.robots.item;

import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.lib.Reference;
import net.minecraft.item.Item;

/**
 * R0b0ts
 * <p/>
 * ItemBase
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBase extends Item {

    public ItemBase(int id, String name) {
        super(id - Reference.ITEM_ID_SHIFT);
        this.setUnlocalizedName(Reference.RESOURCE_PREFIX + name);
        this.setTextureName(Reference.RESOURCE_PREFIX + name);
        this.setCreativeTab(R0b0ts.creativeTab);
    }

}
