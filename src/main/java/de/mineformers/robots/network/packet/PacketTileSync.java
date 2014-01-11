package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.robots.tileentity.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * R0b0ts
 * <p/>
 * PacketTileSync
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class PacketTileSync extends PacketBase {

    protected int x, y, z;

    public PacketTileSync() {

    }

    public PacketTileSync(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void write(ByteArrayDataOutput out) {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
    }

    @Override
    public void read(ByteArrayDataInput in) {
        this.x = in.readInt();
        this.y = in.readInt();
        this.z = in.readInt();
    }

    @Override
    public void execute(EntityPlayer player, Side side) {
        if (side.isClient()) {
            if (player.worldObj.getBlockTileEntity(x, y, z) instanceof TileBase) {
                sync(player, (TileBase) player.worldObj.getBlockTileEntity(x, y, z));
            }
        }
    }

    public abstract void sync(EntityPlayer player, TileBase tileEntity);
}
