package de.mineformers.robots.tileentity;

import cofh.api.energy.IEnergyContainerItem;
import de.mineformers.robots.R0b0ts;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.Robot;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.network.packet.PacketFactoryController;
import de.mineformers.robots.util.NetworkHelper;
import de.mineformers.robots.util.PrivateRobotHelper;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
public class TileFactoryController extends TileBase implements IInventory
{

    private ItemStack[] inventory;
    private ForgeDirection orientation;
    private Vector3 energyPos;
    private boolean validMultiblock;
    private String selectedModule;
    private String selectedChipset;
    private int armorId;
    private int progress;
    private boolean progressing;

    public TileFactoryController()
    {
        inventory = new ItemStack[5];
        selectedModule = "blank";
        selectedChipset = "blank";
    }

    public void setOrientation(int ordinal)
    {
        this.orientation = ForgeDirection.getOrientation(ordinal);
    }

    public ForgeDirection getOrientation()
    {
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public int getArmorId()
    {
        return armorId;
    }

    public void setArmorId(int armorId)
    {
        this.armorId = armorId;
    }

    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
    }

    public boolean isProgressing()
    {
        return progressing;
    }

    public void setProgressing(boolean progressing)
    {
        this.progressing = progressing;
    }

    public void setEnergyPos(Vector3 energyPos)
    {
        this.energyPos = energyPos;
    }

    public void validateMultiblock()
    {
        if (this.orientation != null)
        {
            validMultiblock = false;
            boolean direct = isFactory(0, 1, 0) && isFactoryFrame(0, -1, 0) && isFactoryFrame(0, 2, 0);
            if (direct)
            {
                boolean baseCorrect = false;
                boolean topCorrect = false;
                boolean cornersCorrect = false;
                boolean windowsCorrect = false;
                boolean energyCorrect = false;
                switch (orientation)
                {
                    case NORTH:
                        baseCorrect = isFactoryFrame(-1, -1, 0) && isFactoryFrame(1, -1, 0)
                                && isFactoryFrame(-1, -1, 1) && isFactoryFrame(1, -1, 1)
                                && isFactoryFrame(-1, -1, 2) && isFactoryFrame(0, -1, 2) && isFactoryFrame(1, -1, 2);
                        topCorrect = isFactoryFrame(-1, 2, 0) && isFactoryFrame(1, 2, 0)
                                && isFactoryFrame(-1, 2, 1) && isFactoryFrame(0, 2, 1) && isFactoryFrame(1, 2, 1)
                                && isFactoryFrame(-1, 2, 2) && isFactoryFrame(0, 2, 2) && isFactoryFrame(1, 2, 2);
                        cornersCorrect = isFactoryFrame(1, 0, 0) && isFactoryFrame(1, 1, 0)
                                && isFactoryFrame(-1, 0, 0) && isFactoryFrame(-1, 1, 0)
                                && isFactoryFrame(1, 0, 2) && isFactoryFrame(1, 1, 2)
                                && isFactoryFrame(-1, 0, 2) && isFactoryFrame(-1, 1, 2);
                        windowsCorrect = isFactory(1, 0, 1) && isFactory(1, 1, 1)
                                && isFactory(-1, 0, 1) && isFactory(-1, 1, 1)
                                && isFactory(0, 0, 2) && isFactory(0, 1, 2);
                        energyCorrect = isFactoryEnergy(0, -1, 1);
                        energyPos = new Vector3(xCoord, yCoord - 1, zCoord + 1);
                        break;
                    case SOUTH:
                        baseCorrect = isFactoryFrame(-1, -1, 0) && isFactoryFrame(1, -1, 0)
                                && isFactoryFrame(-1, -1, -1) && isFactoryFrame(1, -1, -1)
                                && isFactoryFrame(-1, -1, -2) && isFactoryFrame(0, -1, -2) && isFactoryFrame(1, -1, -2);
                        topCorrect = isFactoryFrame(-1, 2, 0) && isFactoryFrame(1, 2, 0)
                                && isFactoryFrame(-1, 2, -1) && isFactoryFrame(0, 2, -1) && isFactoryFrame(1, 2, -1)
                                && isFactoryFrame(-1, 2, -2) && isFactoryFrame(0, 2, -2) && isFactoryFrame(1, 2, -2);
                        cornersCorrect = isFactoryFrame(1, 0, 0) && isFactoryFrame(1, 1, 0)
                                && isFactoryFrame(-1, 0, 0) && isFactoryFrame(-1, 1, 0)
                                && isFactoryFrame(1, 0, -2) && isFactoryFrame(1, 1, -2)
                                && isFactoryFrame(-1, 0, -2) && isFactoryFrame(-1, 1, -2);
                        windowsCorrect = isFactory(1, 0, -1) && isFactory(1, 1, -1)
                                && isFactory(-1, 0, -1) && isFactory(-1, 1, -1)
                                && isFactory(0, 0, -2) && isFactory(0, 1, -2);
                        energyCorrect = isFactoryEnergy(0, -1, -1);
                        energyPos = new Vector3(xCoord, yCoord - 1, zCoord - 1);
                        break;
                    case WEST:
                        baseCorrect = isFactoryFrame(0, -1, -1) && isFactoryFrame(0, -1, 1)
                                && isFactoryFrame(1, -1, -1) && isFactoryFrame(1, -1, 1)
                                && isFactoryFrame(2, -1, -1) && isFactoryFrame(2, -1, 0) && isFactoryFrame(2, -1, 1);
                        topCorrect = isFactoryFrame(0, 2, -1) && isFactoryFrame(0, 2, 1)
                                && isFactoryFrame(1, 2, -1) && isFactoryFrame(1, 2, 0) && isFactoryFrame(1, 2, 1)
                                && isFactoryFrame(2, 2, 1) && isFactoryFrame(2, 2, 0) && isFactoryFrame(2, 2, -1);
                        cornersCorrect = isFactoryFrame(0, 0, 1) && isFactoryFrame(0, 1, 1)
                                && isFactoryFrame(0, 0, -1) && isFactoryFrame(0, 1, -1)
                                && isFactoryFrame(2, 0, 1) && isFactoryFrame(2, 1, 1)
                                && isFactoryFrame(2, 0, -1) && isFactoryFrame(2, 1, -1);
                        windowsCorrect = isFactory(1, 0, 1) && isFactory(1, 1, 1)
                                && isFactory(1, 0, -1) && isFactory(1, 1, -1)
                                && isFactory(2, 0, 0) && isFactory(2, 1, 0);
                        energyCorrect = isFactoryEnergy(2, -1, 0);
                        energyPos = new Vector3(xCoord + 2, yCoord - 1, zCoord);
                        break;
                    case EAST:
                        baseCorrect = isFactoryFrame(0, -1, -1) && isFactoryFrame(0, -1, 1)
                                && isFactoryFrame(-1, -1, -1) && isFactoryFrame(-1, -1, 1)
                                && isFactoryFrame(-2, -1, -1) && isFactoryFrame(-2, -1, 0) && isFactoryFrame(-2, -1, 1);
                        topCorrect = isFactoryFrame(0, 2, -1) && isFactoryFrame(0, 2, 1)
                                && isFactoryFrame(-1, 2, -1) && isFactoryFrame(-1, 2, 0) && isFactoryFrame(-1, 2, 1)
                                && isFactoryFrame(-2, 2, 1) && isFactoryFrame(-2, 2, 0) && isFactoryFrame(-2, 2, 1);
                        cornersCorrect = isFactoryFrame(0, 0, 1) && isFactoryFrame(0, 1, 1)
                                && isFactoryFrame(0, 0, -1) && isFactoryFrame(0, 1, -1)
                                && isFactoryFrame(-2, 0, 1) && isFactoryFrame(-2, 1, 1)
                                && isFactoryFrame(-2, 0, -1) && isFactoryFrame(-2, 1, -1);
                        windowsCorrect = isFactory(-1, 0, 1) && isFactory(-1, 1, 1)
                                && isFactory(-1, 0, -1) && isFactory(-1, 1, -1)
                                && isFactory(-2, 0, 0) && isFactory(-2, 1, 0);
                        energyCorrect = isFactoryEnergy(-2, -1, 0);
                        energyPos = new Vector3(xCoord - 2, yCoord - 1, zCoord);
                        break;
                }
                if (!energyCorrect)
                {
                    energyPos = null;
                }
                this.validMultiblock = baseCorrect && topCorrect && cornersCorrect && windowsCorrect && energyCorrect;
                if (validMultiblock)
                {
                    ((TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z)).setControllerPos(new Vector3(xCoord, yCoord, zCoord));
                } else if (energyCorrect)
                {
                    ((TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z)).setControllerPos(null);
                }
            }
            if (!worldObj.isRemote)
                NetworkHelper.sendTilePacket(this);
        }
    }

    private boolean isFactoryEnergy(int xOff, int yOff, int zOff)
    {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_ENERGY;
    }

    private boolean isFactory(int xOff, int yOff, int zOff)
    {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE;
    }

    private boolean isFactoryFrame(int xOff, int yOff, int zOff)
    {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE && worldObj.getBlockMetadata(xCoord + xOff, yCoord + yOff, zCoord + zOff) == 1;
    }

    private boolean isFactoryGlass(int xOff, int yOff, int zOff)
    {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE && worldObj.getBlockMetadata(xCoord + xOff, yCoord + yOff, zCoord + zOff) == 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        orientation = ForgeDirection.getOrientation(tagCompound.getInteger("Orientation"));
        validMultiblock = tagCompound.getBoolean("ValidMultiblock");
        progressing = tagCompound.getBoolean("IsProgressing");
        progress = tagCompound.getInteger("Progress");
        NBTTagList tagList = tagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound compound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = compound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(compound);
            }
        }
        if (tagCompound.hasKey("EnergyPosition"))
        {
            int[] coords = tagCompound.getIntArray("EnergyPosition");
            energyPos = new Vector3(coords[0], coords[1], coords[2]);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("Orientation", orientation.ordinal());
        tagCompound.setBoolean("ValidMultiblock", validMultiblock);
        tagCompound.setBoolean("IsProgressing", progressing);
        tagCompound.setInteger("Progress", progress);
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(compound);
                tagList.appendTag(compound);
            }
        }
        tagCompound.setTag("Items", tagList);
        if (energyPos != null)
            tagCompound.setIntArray("EnergyPosition", new int[]{(int) energyPos.x, (int) energyPos.y, (int) energyPos.z});
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (!worldObj.isRemote)
        {
            if (isValidMultiblock())
            {
                TileFactoryEnergy energy = (TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z);
                if (this.getStackInSlot(4) != null)
                {
                    if (this.getEnergyLevel() < 400000)
                    {
                        int request = 400000 - this.getEnergyLevel();
                        if (request > 500)
                            request = 500;
                        int received = ((IEnergyContainerItem) inventory[4].getItem()).extractEnergy(inventory[4], request, true);
                        if (received > 100)
                            energy.receiveEnergy(ForgeDirection.DOWN, ((IEnergyContainerItem) inventory[4].getItem()).extractEnergy(inventory[4], request, false), false);
                    }
                }
                if (this.getEnergyLevel() >= 400000)
                {
                    energy.setRate(0);
                }
            }
            if (progressing)
            {
                if (!selectedModule.equals("blank") && !selectedChipset.equals("blank") && armorId != -1 && ModuleRegistry.instance().getModule(selectedModule).validateStack(getStackInSlot(0)))
                {
                    if (isValidMultiblock())
                    {
                        TileFactoryEnergy energy = (TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z);
                        if (energy.extractEnergy(ForgeDirection.DOWN, 500, false) == 500)
                        {
                            if (progress < 400)
                            {
                                energy.setRate(500);
                                progress++;
                            }
                            if (progress >= 400)
                                if (getStackInSlot(3) == null)
                                {
                                    IModuleData data = ModuleRegistry.instance().getModule(selectedModule).getData();
                                    data.readFromNBT(getStackInSlot(0).getTagCompound().getCompoundTag("ModuleData"));
                                    setInventorySlotContents(3, PrivateRobotHelper.createItemStackFromRobot(getStackInSlot(1).hasDisplayName() ? getStackInSlot(1).getDisplayName() : null, new Robot(selectedModule, selectedChipset, armorId, data)));
                                    progress = 0;
                                    progressing = false;
                                    setInventorySlotContents(0, null);
                                    setInventorySlotContents(1, null);
                                    setInventorySlotContents(2, null);
                                }
                        }
                        NetworkHelper.sendTilePacket(this);
                    }
                } else
                {
                    progressing = false;
                    progress = 0;
                }
            }
        }
    }

    public boolean isValidMultiblock()
    {
        return validMultiblock;
    }

    public void setValidMultiblock(boolean validMultiblock)
    {
        this.validMultiblock = validMultiblock;
    }

    @Override
    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        if (this.getStackInSlot(0) != null)
        {
            selectedModule = RobotHelper.getModuleFromItemStack(this.getStackInSlot(0)).getIdentifier();
        } else
        {
            selectedModule = "blank";
        }
        if (this.getStackInSlot(1) != null)
        {
            selectedChipset = RobotHelper.getChipsetFromItemStack(this.getStackInSlot(1)).getIdentifier();
        } else
        {
            selectedChipset = "blank";
        }
        armorId = (getStackInSlot(2) == null ? -1 : getStackInSlot(2).itemID);

        NetworkHelper.sendTilePacket(this);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return new PacketFactoryController(xCoord, yCoord, zCoord, orientation, validMultiblock, selectedModule, selectedChipset, (getStackInSlot(2) == null ? -1 : getStackInSlot(2).itemID), progressing, progress, energyPos).makePacket();
    }

    public int getEnergyLevel()
    {
        if (energyPos != null)
        {
            return ((TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z)).getEnergyStored(ForgeDirection.DOWN);
        }
        return 0;
    }

    public int getCurrentRate()
    {
        if (energyPos != null)
        {
            return ((TileFactoryEnergy) worldObj.getBlockTileEntity((int) energyPos.x, (int) energyPos.y, (int) energyPos.z)).getCurrentRate();
        }
        return 0;
    }

    /* Inventory code */
    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            } else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public String getInvName()
    {
        return "inventory." + Reference.RESOURCE_PREFIX + Strings.FACTORY_CONTROLLER_NAME;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return true;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public void openChest()
    {

    }

    @Override
    public void closeChest()
    {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }

    public String getSelectedModule()
    {
        return selectedModule;
    }

    public void setSelectedModule(String selectedModule)
    {
        this.selectedModule = selectedModule;
        R0b0ts.proxy.updateFactoryGui(this);
    }

    public String getSelectedChipset()
    {
        return selectedChipset;
    }

    public void setSelectedChipset(String selectedChipset)
    {
        this.selectedChipset = selectedChipset;
        R0b0ts.proxy.updateFactoryGui(this);
    }
}
