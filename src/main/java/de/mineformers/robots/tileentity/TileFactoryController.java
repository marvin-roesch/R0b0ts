package de.mineformers.robots.tileentity;

import de.mineformers.robots.network.packet.PacketFactoryController;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * TileFactoryController
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileFactoryController extends TileBase {

    private ForgeDirection orientation;

    public void setOrientation(int ordinal) {
        this.orientation = ForgeDirection.getOrientation(ordinal);
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public ForgeDirection getOrientation() {
        return orientation;
    }

    public void validateMultiblock() {
        switch(orientation) {

        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("Orientation", orientation.ordinal());
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        orientation = ForgeDirection.getOrientation(tagCompound.getInteger("Orientation"));
    }

    @Override
    public Packet getDescriptionPacket() {
        return new PacketFactoryController(xCoord, yCoord, zCoord, orientation).makePacket();
    }
}
