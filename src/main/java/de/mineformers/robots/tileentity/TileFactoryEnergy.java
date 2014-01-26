package de.mineformers.robots.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.network.packet.PacketFactoryEnergy;
import de.mineformers.robots.util.NetworkHelper;
import de.mineformers.robots.util.Vector3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * TileFactoryEnergy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileFactoryEnergy extends TileBase implements IEnergyHandler
{

    private int currentRate;
    protected EnergyStorage storage = new EnergyStorage(400000);
    private Vector3 controllerPos;

    public void checkController()
    {
        if (controllerPos != null)
        {
            if (worldObj.getBlockId((int) controllerPos.x, (int) controllerPos.y, (int) controllerPos.z) != BlockIds.FACTORY_CONTROLLER)
                controllerPos = null;
            else
                ((TileFactoryController) worldObj.getBlockTileEntity((int) controllerPos.x, (int) controllerPos.y, (int) controllerPos.z)).validateMultiblock();
        }
    }

    public void setRate(int rate)
    {
        this.currentRate = rate;
        if (!worldObj.isRemote)
            NetworkHelper.sendTilePacket(this);
    }

    public int getCurrentRate()
    {
        return currentRate;
    }

    public Vector3 getControllerPos()
    {
        return controllerPos;
    }

    public void setControllerPos(Vector3 controllerPos)
    {
        this.controllerPos = controllerPos;
    }

    public void setEnergy(int amount)
    {
        storage.setEnergyStored(amount);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
        if (nbt.hasKey("ControllerPosition"))
        {
            int[] coords = nbt.getIntArray("ControllerPosition");
            controllerPos = new Vector3(coords[0], coords[1], coords[2]);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        if (controllerPos != null)
            nbt.setIntArray("ControllerPosition", new int[]{(int) controllerPos.x, (int) controllerPos.y, (int) controllerPos.z});
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return new PacketFactoryEnergy(xCoord, yCoord, zCoord, storage.getEnergyStored(), currentRate, controllerPos).makePacket();
    }

    /* IEnergyHandler */
    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        if (storage.getEnergyStored() >= 400000)
            return 0;
        if (maxReceive < 100)
            return 0;
        currentRate = maxReceive;
        if (maxReceive > 500)
            currentRate = 500;
        int result = storage.receiveEnergy(currentRate, simulate);
        NetworkHelper.sendTilePacket(this);
        return result;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        int result = storage.extractEnergy(maxExtract, simulate);
        NetworkHelper.sendTilePacket(this);
        return result;
    }

    @Override
    public boolean canInterface(ForgeDirection from)
    {
        return from == ForgeDirection.DOWN;
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return storage.getMaxEnergyStored();
    }
}
