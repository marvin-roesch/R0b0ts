package de.mineformers.robots.item;

import de.mineformers.robots.lib.Reference;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ItemBlockSub
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockSub extends ItemBlockBase
{

    private String name;
    private String[] subNames;

    public ItemBlockSub(int id, String name, String[] subNames)
    {
        super(id, name);
        this.setHasSubtypes(true);
        this.name = name;
        this.subNames = subNames;
    }

    @Override
    public String getUnlocalizedName(ItemStack is)
    {
        if (is.getItemDamage() >= subNames.length)
            return "";
        return "tile." + Reference.RESOURCE_PREFIX + name + "."
                + subNames[is.getItemDamage()];
    }

    @Override
    public int getMetadata(int damageValue)
    {
        return damageValue;
    }

}
