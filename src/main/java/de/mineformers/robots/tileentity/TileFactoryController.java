package de.mineformers.robots.tileentity;

import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.network.packet.PacketFactoryController;
import de.mineformers.robots.util.NetworkHelper;
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
public class TileFactoryController extends TileBase implements IInventory {

    private ItemStack[] inventory;
    private ForgeDirection orientation;
    private boolean validMultiblock;

    public TileFactoryController() {
        inventory = new ItemStack[3];
    }

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
        if (this.orientation != null) {
            validMultiblock = false;
            boolean direct = isFactory(0, 1, 0) && isFactoryFrame(0, -1, 0) && isFactoryFrame(0, 2, 0);
            if (direct) {
                boolean baseCorrect = false;
                boolean topCorrect = false;
                boolean cornersCorrect = false;
                boolean windowsCorrect = false;
                switch (orientation) {
                    case NORTH:
                        baseCorrect = isFactoryFrame(-1, -1, 0) && isFactoryFrame(1, -1, 0)
                                && isFactoryFrame(-1, -1, 1) && isFactoryFrame(0, -1, 1) && isFactoryFrame(1, -1, 1)
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
                        break;
                    case SOUTH:
                        baseCorrect = isFactoryFrame(-1, -1, 0) && isFactoryFrame(1, -1, 0)
                                && isFactoryFrame(-1, -1, -1) && isFactoryFrame(0, -1, -1) && isFactoryFrame(1, -1, -1)
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
                        break;
                    case WEST:
                        baseCorrect = isFactoryFrame(0, -1, -1) && isFactoryFrame(0, -1, 1)
                                && isFactoryFrame(1, -1, -1) && isFactoryFrame(2, -1, 0) && isFactoryFrame(1, -1, 1)
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
                        break;
                    case EAST:
                        baseCorrect = isFactoryFrame(0, -1, -1) && isFactoryFrame(0, -1, 1)
                                && isFactoryFrame(-1, -1, -1) && isFactoryFrame(-2, -1, 0) && isFactoryFrame(-1, -1, 1)
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
                        break;
                }
                this.validMultiblock = baseCorrect && topCorrect && cornersCorrect && windowsCorrect;
            }
            if (!worldObj.isRemote)
                NetworkHelper.sendTilePacket(this);
        }
    }

    public boolean isValidMultiblock() {
        return validMultiblock;
    }

    private boolean isFactory(int xOff, int yOff, int zOff) {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE;
    }

    private boolean isFactoryGlass(int xOff, int yOff, int zOff) {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE && worldObj.getBlockMetadata(xCoord + xOff, yCoord + yOff, zCoord + zOff) == 0;
    }

    private boolean isFactoryFrame(int xOff, int yOff, int zOff) {
        return worldObj.getBlockId(xCoord + xOff, yCoord + yOff, zCoord + zOff) == BlockIds.FACTORY_BASE && worldObj.getBlockMetadata(xCoord + xOff, yCoord + yOff, zCoord + zOff) == 1;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("Orientation", orientation.ordinal());
        tagCompound.setBoolean("ValidMultiblock", validMultiblock);
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(compound);
                tagList.appendTag(compound);
            }
        }
        tagCompound.setTag("Items", tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        orientation = ForgeDirection.getOrientation(tagCompound.getInteger("Orientation"));
        validMultiblock = tagCompound.getBoolean("ValidMultiblock");
        NBTTagList tagList = tagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound compound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = compound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length) {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

    }

    public void setValidMultiblock(boolean validMultiblock) {
        this.validMultiblock = validMultiblock;
    }

    @Override
    public Packet getDescriptionPacket() {
        return new PacketFactoryController(xCoord, yCoord, zCoord, orientation, validMultiblock).makePacket();
    }

    /* Inventory code */
    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "inventory." + Reference.RESOURCE_PREFIX + Strings.FACTORY_CONTROLLER_NAME;
    }

    @Override
    public boolean isInvNameLocalized() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }
}
