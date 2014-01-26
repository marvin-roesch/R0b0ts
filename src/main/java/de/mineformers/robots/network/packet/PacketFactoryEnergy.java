package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.tileentity.TileBase;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.tileentity.TileFactoryEnergy;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.player.EntityPlayer;

/**
 * R0b0ts
 * <p/>
 * PacketFactoryEnergy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketFactoryEnergy extends PacketTileSync
{

    private int currentRate;
    private int energy;
    private Vector3 controllerPos;

    public PacketFactoryEnergy()
    {

    }

    public PacketFactoryEnergy(int x, int y, int z, int energy, int currentRate, Vector3 controllerPos)
    {
        super(x, y, z);
        this.energy = energy;
        this.controllerPos = controllerPos;
        this.currentRate = currentRate;
    }

    @Override
    public void write(ByteArrayDataOutput out)
    {
        super.write(out);
        out.writeInt(energy);
        out.writeInt(currentRate);
        out.writeBoolean(controllerPos != null);
        if (controllerPos != null)
        {
            out.writeInt((int) controllerPos.x);
            out.writeInt((int) controllerPos.y);
            out.writeInt((int) controllerPos.z);
        }
    }

    @Override
    public void read(ByteArrayDataInput in)
    {
        super.read(in);
        energy = in.readInt();
        currentRate = in.readInt();
        boolean available = in.readBoolean();
        if (available)
        {
            controllerPos = new Vector3(in.readInt(), in.readInt(), in.readInt());
        }
    }

    @Override
    public void sync(EntityPlayer player, TileBase tileEntity)
    {
        ((TileFactoryEnergy) tileEntity).setEnergy(energy);
        ((TileFactoryEnergy) tileEntity).setRate(currentRate);
        ((TileFactoryEnergy) tileEntity).setControllerPos(controllerPos);
        if (controllerPos != null)
        {
            R0b0ts.proxy.updateFactoryGui((TileFactoryController) tileEntity.worldObj.getBlockTileEntity((int) controllerPos.x, (int) controllerPos.y, (int) controllerPos.z));
        }
    }
}
