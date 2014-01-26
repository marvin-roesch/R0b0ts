package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import de.mineformers.robots.tileentity.TileBase;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * PacketFactoryController
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketFactoryController extends PacketTileSync
{

    private int orientation;
    private boolean validMultiblock;
    private String selectedModule;
    private String selectedChipset;
    private int armorId;
    private boolean progressing;
    private int progress;
    private Vector3 energyPos;

    public PacketFactoryController()
    {
    }

    public PacketFactoryController(int x, int y, int z, ForgeDirection orientation, boolean validMultiblock, String selectedModule, String selectedChipset, int armorId, boolean progressing, int progress, Vector3 energyPos)
    {
        super(x, y, z);
        this.orientation = orientation.ordinal();
        this.validMultiblock = validMultiblock;
        this.selectedModule = selectedModule;
        this.selectedChipset = selectedChipset;
        this.armorId = armorId;
        this.progressing = progressing;
        this.progress = progress;
        this.energyPos = energyPos;
    }

    @Override
    public void write(ByteArrayDataOutput out)
    {
        super.write(out);
        out.writeInt(orientation);
        out.writeBoolean(validMultiblock);
        out.writeUTF(selectedModule);
        out.writeUTF(selectedChipset);
        out.writeInt(armorId);
        out.writeBoolean(progressing);
        out.writeInt(progress);
        out.writeBoolean(energyPos != null);
        if (energyPos != null)
        {
            out.writeInt((int) energyPos.x);
            out.writeInt((int) energyPos.y);
            out.writeInt((int) energyPos.z);
        }
    }

    @Override
    public void read(ByteArrayDataInput in)
    {
        super.read(in);
        orientation = in.readInt();
        validMultiblock = in.readBoolean();
        selectedModule = in.readUTF();
        selectedChipset = in.readUTF();
        armorId = in.readInt();
        progressing = in.readBoolean();
        progress = in.readInt();
        if (in.readBoolean())
        {
            energyPos = new Vector3(in.readInt(), in.readInt(), in.readInt());
        }
    }

    @Override
    public void sync(EntityPlayer player, TileBase tileEntity)
    {
        TileFactoryController tile = (TileFactoryController) tileEntity;
        tile.setOrientation(orientation);
        tile.setEnergyPos(energyPos);
        tile.setValidMultiblock(validMultiblock);
        tile.setSelectedModule(selectedModule);
        tile.setSelectedChipset(selectedChipset);
        tile.setArmorId(armorId);
        tile.setProgressing(progressing);
        tile.setProgress(progress);
        player.worldObj.markBlockForRenderUpdate(x, y, z);
        player.worldObj.updateLightByType(EnumSkyBlock.Block, x, y, z);
    }
}
