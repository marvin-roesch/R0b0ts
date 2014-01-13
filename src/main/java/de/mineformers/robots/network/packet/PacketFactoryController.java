package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
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
    private boolean validMultiblock;
    private String selectedModule;
    private int armorId;

    public PacketFactoryController() {
    }

    public PacketFactoryController(int x, int y, int z, ForgeDirection orientation, boolean validMultiblock, String selectedModule, int armorId) {
        super(x, y, z);
        this.orientation = orientation.ordinal();
        this.validMultiblock = validMultiblock;
        this.selectedModule = selectedModule;
        this.armorId = armorId;
    }

    @Override
    public void write(ByteArrayDataOutput out) {
        super.write(out);
        out.writeInt(orientation);
        out.writeBoolean(validMultiblock);
        out.writeUTF(selectedModule);
        out.writeInt(armorId);
    }

    @Override
    public void read(ByteArrayDataInput in) {
        super.read(in);
        orientation = in.readInt();
        validMultiblock = in.readBoolean();
        selectedModule = in.readUTF();
        armorId = in.readInt();
    }

    @Override
    public void sync(EntityPlayer player, TileBase tileEntity) {
        TileFactoryController tile = (TileFactoryController) tileEntity;
        tile.setOrientation(orientation);
        tile.setValidMultiblock(validMultiblock);
        tile.setSelectedModule(selectedModule);
        tile.setArmorId(armorId);
        player.worldObj.markBlockForRenderUpdate(x, y, z);
    }
}
