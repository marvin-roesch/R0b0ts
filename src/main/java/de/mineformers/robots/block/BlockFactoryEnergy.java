package de.mineformers.robots.block;

import cofh.util.EnergyHelper;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.tileentity.TileFactoryEnergy;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * BlockFactoryEnergy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFactoryEnergy extends BlockBase implements ITileEntityProvider
{

    private Icon iconSide;
    private Icon iconBottom;

    public BlockFactoryEnergy(int id)
    {
        super(id, Material.iron, Strings.FACTORY_ENERGY_NAME);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int oldId)
    {
        super.onNeighborBlockChange(world, x, y, z, oldId);
        if (!world.isRemote)
            ((TileFactoryEnergy) world.getBlockTileEntity(x, y, z)).checkController();
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        iconSide = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryEnergySide");
        iconBottom = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "factoryEnergyBottom");
    }

    @Override
    public Icon getIcon(int side, int meta)
    {
        return side <= 1 ? iconBottom : iconSide;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            player.addChatMessage(((TileFactoryEnergy) world.getBlockTileEntity(x, y, z)).extractEnergy(ForgeDirection.DOWN, 2000, false) + ";" + ((TileFactoryEnergy) world.getBlockTileEntity(x, y, z)).getEnergyStored(ForgeDirection.DOWN));
        }
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileFactoryEnergy();
    }
}
