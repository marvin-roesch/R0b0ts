package de.mineformers.robots.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

/**
 * R0b0ts
 * <p/>
 * BlockFactoryGlass
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFactoryGlass extends BlockBase {

    private Icon iconConTop;
    private Icon iconConBottom;
    private Icon iconConAll;

    public BlockFactoryGlass(int id) {
        super(id, Material.glass, Strings.FACTORY_GLASS_NAME);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        this.iconConTop = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryGlassTop");
        this.iconConBottom = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryGlassBottom");
        this.iconConAll = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryGlassAll");
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        if (side > 1) {
            boolean connectedTop = world.getBlockId(x, y + 1, z) == this.blockID;
            boolean connectedBottom = world.getBlockId(x, y - 1, z) == this.blockID;
            if(connectedTop && connectedBottom)
                return iconConAll;
            if(connectedTop)
                return iconConTop;
            if(connectedBottom)
                return iconConBottom;
        }
        return blockIcon;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        int id = world.getBlockId(x, y, z);
        return !false && id == this.blockID ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass() {
        return 0;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

}
