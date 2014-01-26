package de.mineformers.robots.block;

import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * R0b0ts
 * <p/>
 * BlockBase
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockBase extends Block
{

    public BlockBase(int id, Material material, String name)
    {
        super(id, material);
        this.setUnlocalizedName(Reference.RESOURCE_PREFIX + name);
        this.setTextureName(Reference.RESOURCE_PREFIX + name);
        this.setCreativeTab(R0b0ts.creativeTab);
    }

}
