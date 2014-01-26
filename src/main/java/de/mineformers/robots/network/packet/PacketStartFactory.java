package de.mineformers.robots.network.packet;

import de.mineformers.robots.tileentity.TileBase;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.NetworkHelper;
import net.minecraft.entity.player.EntityPlayer;

/**
 * R0b0ts
 * <p/>
 * PacketStartFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketStartFactory extends PacketTileSync
{

    public PacketStartFactory()
    {

    }

    public PacketStartFactory(int x, int y, int z)
    {
        super(x, y, z);
    }

    @Override
    public void sync(EntityPlayer player, TileBase tileEntity)
    {
        ((TileFactoryController) tileEntity).setProgressing(true);
        NetworkHelper.sendTilePacket(tileEntity);
    }
}
