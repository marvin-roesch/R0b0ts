package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.robots.tileentity.TileBase;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * PacketFactoryController
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketFactoryController extends PacketTileSync {

    private int orientation;

    public PacketFactoryController() {
    }

    public PacketFactoryController(int x, int y, int z, ForgeDirection orientation) {
        super(x, y, z);
        this.orientation = orientation.ordinal();
    }

    @Override
    public void write(ByteArrayDataOutput out) {
        super.write(out);
        out.writeInt(orientation);
    }

    @Override
    public void read(ByteArrayDataInput in) {
        super.read(in);
        orientation = in.readInt();
    }

    @Override
    public void sync(EntityPlayer player, TileBase tileEntity) {
        TileFactoryController tile = (TileFactoryController) tileEntity;
        tile.setOrientation(orientation);
    }
}
