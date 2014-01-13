package de.mineformers.robots.block;

import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * BlockFactoryController
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFactoryController extends BlockBase implements ITileEntityProvider {

    private Icon frontIcon;
    private Icon activeIcon;
    private Icon sideIcon;
    private Icon topIcon;

    public BlockFactoryController(int id) {
        super(id, Material.iron, Strings.FACTORY_CONTROLLER_NAME);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
        if (world.getBlockTileEntity(x, y, z) instanceof TileFactoryController) {
            TileFactoryController tile = (TileFactoryController) world.getBlockTileEntity(x, y, z);
            tile.validateMultiblock();
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) {
            if (!((TileFactoryController) world.getBlockTileEntity(x, y, z)).isValidMultiblock()) {
                if (world.isRemote) {
                    player.addChatMessage(LangHelper.translate("chat", "invalidMultiblock"));
                }
            } else
                player.openGui(R0b0ts.instance, 0, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        super.registerIcons(iconRegister);
        frontIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryController");
        activeIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryControllerActive");
        sideIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryControllerSide");
        topIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryControllerTop");
    }

    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        if (world.getBlockTileEntity(x, y, z) instanceof TileFactoryController) {
            TileFactoryController tile = (TileFactoryController) world.getBlockTileEntity(x, y, z);
            if (tile.getOrientation() != null)
                if (side == tile.getOrientation().ordinal())
                    return tile.isValidMultiblock() ? activeIcon : frontIcon;
        }

        if (side == 0 || side == 1)
            return topIcon;

        return sideIcon;
    }

    @Override
    public Icon getIcon(int side, int meta) {
        switch (side) {
            case 0:
            case 1:
                return topIcon;
            case 4:
                return frontIcon;
            default:
                return sideIcon;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        if (world.getBlockTileEntity(x, y, z) instanceof TileFactoryController) {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if (facing == 0) {
                direction = ForgeDirection.NORTH.ordinal();
            } else if (facing == 1) {
                direction = ForgeDirection.EAST.ordinal();
            } else if (facing == 2) {
                direction = ForgeDirection.SOUTH.ordinal();
            } else if (facing == 3) {
                direction = ForgeDirection.WEST.ordinal();
            }

            TileFactoryController controller = (TileFactoryController) world.getBlockTileEntity(x, y, z);
            controller.setOrientation(direction);
            if (!world.isRemote)
                controller.validateMultiblock();
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileFactoryController();
    }

}
