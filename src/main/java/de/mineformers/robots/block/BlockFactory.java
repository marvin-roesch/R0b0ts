package de.mineformers.robots.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

/**
 * R0b0ts
 * <p/>
 * BlockFactoryGlass
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFactory extends BlockBase {

    private Icon[] glassIcons = new Icon[16];
    private Icon[] frameIcons = new Icon[16];

    public BlockFactory(int id) {
        super(id, Material.glass, Strings.FACTORY_BASE_NAME);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(id, 1, 0));
        subItems.add(new ItemStack(id, 1, 1));
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world,
                                  int x, int y, int z) {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }

    public boolean shouldConnectToBlock(IBlockAccess world, int x, int y, int z, int id, int meta, int checkMeta) {
        return id == this.blockID && (meta == checkMeta || checkMeta == 1);
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        return world.getBlockMetadata(x, y, z) == 1 ? getConnectedBlockTexture(world, x, y, z, side, world.getBlockMetadata(x, y, z), frameIcons) : getConnectedBlockTexture(world, x, y, z, side, world.getBlockMetadata(x, y, z), glassIcons);
    }

    public Icon getConnectedBlockTexture(IBlockAccess world, int x, int y, int z, int side, int meta, Icon[] icons) {
        boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;
        switch (side) {
            case 0:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x - 1, y, z), world.getBlockMetadata(x - 1, y, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x + 1, y, z), world.getBlockMetadata(x + 1, y, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z - 1), world.getBlockMetadata(x, y, z - 1), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z + 1), world.getBlockMetadata(x, y, z + 1), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[11];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[12];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[13];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[14];
                } else if (isOpenDown && isOpenUp) {
                    return icons[5];
                } else if (isOpenLeft && isOpenRight) {
                    return icons[6];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[8];
                } else if (isOpenDown && isOpenRight) {
                    return icons[10];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[7];
                } else if (isOpenUp && isOpenRight) {
                    return icons[9];
                } else if (isOpenDown) {
                    return icons[3];
                } else if (isOpenUp) {
                    return icons[4];
                } else if (isOpenLeft) {
                    return icons[2];
                } else if (isOpenRight) {
                    return icons[1];
                }
                break;
            case 1:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x - 1, y, z), world.getBlockMetadata(x - 1, y, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x + 1, y, z), world.getBlockMetadata(x + 1, y, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z - 1), world.getBlockMetadata(x, y, z - 1), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z + 1), world.getBlockMetadata(x, y, z + 1), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[11];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[12];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[13];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[14];
                } else if (isOpenDown && isOpenUp) {
                    return icons[5];
                } else if (isOpenLeft && isOpenRight) {
                    return icons[6];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[8];
                } else if (isOpenDown && isOpenRight) {
                    return icons[10];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[7];
                } else if (isOpenUp && isOpenRight) {
                    return icons[9];
                } else if (isOpenDown) {
                    return icons[3];
                } else if (isOpenUp) {
                    return icons[4];
                } else if (isOpenLeft) {
                    return icons[2];
                } else if (isOpenRight) {
                    return icons[1];
                }
                break;
            case 2:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y + 1, z), world.getBlockMetadata(x, y + 1, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x - 1, y, z), world.getBlockMetadata(x - 1, y, z), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x + 1, y, z), world.getBlockMetadata(x + 1, y, z), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[13];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[14];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[11];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[12];
                } else if (isOpenDown && isOpenUp) {
                    if (world.getBlockTileEntity(x - 1, y, z) instanceof TileFactoryController) {
                        return icons[13];
                    }
                    if (world.getBlockTileEntity(x + 1, y, z) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[6];
                } else if (isOpenLeft && isOpenRight) {
                    if (world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController) {
                        return icons[11];
                    }
                    if (world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController) {
                        return icons[12];
                    }
                    return icons[5];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[9];
                } else if (isOpenDown && isOpenRight) {
                    return icons[10];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[7];
                } else if (isOpenUp && isOpenRight) {
                    return icons[8];
                } else if (isOpenDown) {
                    return icons[1];
                } else if (isOpenUp) {
                    return icons[2];
                } else if (isOpenLeft) {
                    return icons[4];
                } else if (isOpenRight) {
                    return icons[3];
                }
                break;
            case 3:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y + 1, z), world.getBlockMetadata(x, y + 1, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x - 1, y, z), world.getBlockMetadata(x - 1, y, z), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x + 1, y, z), world.getBlockMetadata(x + 1, y, z), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[14];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[13];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[11];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[12];
                } else if (isOpenDown && isOpenUp) {
                    if (world.getBlockTileEntity(x + 1, y, z) instanceof TileFactoryController) {
                        return icons[13];
                    }
                    if (world.getBlockTileEntity(x - 1, y, z) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[6];
                } else if (isOpenLeft && isOpenRight) {
                    if (world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController) {
                        return icons[11];
                    }
                    if (world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[5];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[10];
                } else if (isOpenDown && isOpenRight) {
                    return icons[9];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[8];
                } else if (isOpenUp && isOpenRight) {
                    return icons[7];
                } else if (isOpenDown) {
                    return icons[1];
                } else if (isOpenUp) {
                    return icons[2];
                } else if (isOpenLeft) {
                    return icons[3];
                } else if (isOpenRight) {
                    return icons[4];
                }
                break;
            case 4:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y + 1, z), world.getBlockMetadata(x, y + 1, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z - 1), world.getBlockMetadata(x, y, z - 1), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z + 1), world.getBlockMetadata(x, y, z + 1), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[14];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[13];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[11];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[12];
                } else if (isOpenDown && isOpenUp) {
                    if (world.getBlockTileEntity(x, y, z + 1) instanceof TileFactoryController) {
                        return icons[13];
                    }
                    if (world.getBlockTileEntity(x, y, z - 1) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[6];
                } else if (isOpenLeft && isOpenRight) {
                    if (world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController) {
                        return icons[11];
                    }
                    if (world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[5];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[10];
                } else if (isOpenDown && isOpenRight) {
                    return icons[9];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[8];
                } else if (isOpenUp && isOpenRight) {
                    return icons[7];
                } else if (isOpenDown) {
                    return icons[1];
                } else if (isOpenUp) {
                    return icons[2];
                } else if (isOpenLeft) {
                    return icons[3];
                } else if (isOpenRight) {
                    return icons[4];
                }
                break;
            case 5:
                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z), meta)) {
                    isOpenDown = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y + 1, z), world.getBlockMetadata(x, y + 1, z), meta)) {
                    isOpenUp = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z - 1), world.getBlockMetadata(x, y, z - 1), meta)) {
                    isOpenLeft = true;
                }

                if (shouldConnectToBlock(world, x, y, z, world.getBlockId(x, y, z + 1), world.getBlockMetadata(x, y, z + 1), meta)) {
                    isOpenRight = true;
                }

                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
                    return icons[15];
                } else if (isOpenUp && isOpenDown && isOpenLeft) {
                    return icons[13];
                } else if (isOpenUp && isOpenDown && isOpenRight) {
                    return icons[14];
                } else if (isOpenUp && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[11];
                } else if (isOpenDown && isOpenLeft && isOpenRight) {
                    if(world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController)
                        return icons[15];
                    return icons[12];
                } else if (isOpenDown && isOpenUp) {
                    if (world.getBlockTileEntity(x, y, z - 1) instanceof TileFactoryController) {
                        return icons[13];
                    }
                    if (world.getBlockTileEntity(x, y, z + 1) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[6];
                } else if (isOpenLeft && isOpenRight) {
                    if (world.getBlockTileEntity(x, y + 1, z) instanceof TileFactoryController) {
                        return icons[11];
                    }
                    if (world.getBlockTileEntity(x, y - 1, z) instanceof TileFactoryController) {
                        return icons[14];
                    }
                    return icons[5];
                } else if (isOpenDown && isOpenLeft) {
                    return icons[9];
                } else if (isOpenDown && isOpenRight) {
                    return icons[10];
                } else if (isOpenUp && isOpenLeft) {
                    return icons[7];
                } else if (isOpenUp && isOpenRight) {
                    return icons[8];
                } else if (isOpenDown) {
                    return icons[1];
                } else if (isOpenUp) {
                    return icons[2];
                } else if (isOpenLeft) {
                    return icons[4];
                } else if (isOpenRight) {
                    return icons[3];
                }
                break;
        }

        return icons[0];
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!par1World.isRemote)
            par5EntityPlayer.addChatMessage("" + par6);
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
        return id == this.blockID && meta == world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    public Icon getIcon(int side, int meta) {
        if (meta == 1)
            return frameIcons[1];
        return glassIcons[0];
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        glassIcons[0] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass");
        glassIcons[1] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_1_d");
        glassIcons[2] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_1_u");
        glassIcons[3] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_1_l");
        glassIcons[4] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_1_r");
        glassIcons[5] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_h");
        glassIcons[6] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_v");
        glassIcons[7] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_dl");
        glassIcons[8] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_dr");
        glassIcons[9] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_ul");
        glassIcons[10] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_2_ur");
        glassIcons[11] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_3_d");
        glassIcons[12] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_3_u");
        glassIcons[13] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_3_l");
        glassIcons[14] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_3_r");
        glassIcons[15] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "glass/" + "/glass_4");
        frameIcons[0] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame");
        frameIcons[1] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_1_d");
        frameIcons[2] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_1_u");
        frameIcons[3] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_1_l");
        frameIcons[4] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_1_r");
        frameIcons[5] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_h");
        frameIcons[6] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_v");
        frameIcons[7] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_dl");
        frameIcons[8] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_dr");
        frameIcons[9] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_ul");
        frameIcons[10] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_2_ur");
        frameIcons[11] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_3_d");
        frameIcons[12] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_3_u");
        frameIcons[13] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_3_l");
        frameIcons[14] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_3_r");
        frameIcons[15] = par1IconRegister.registerIcon(Reference.RESOURCE_PREFIX + "frame/" + "/frame_4");
    }

    public boolean isOpaqueCube() {
        return false;
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
