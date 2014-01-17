package de.mineformers.robots.module.ai;

import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.module.data.CraftingData;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * AICrafting
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AICrafting extends EntityAIBase {

    private EntityRobot robot;
    private Vector3 pos;

    public AICrafting(EntityRobot entity) {
        this.robot = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (pos == null)
            pos = robot.getBoundInventory();
        return robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory;
    }

    public boolean continueExecuting() {
        return !this.robot.getNavigator().noPath() && robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.robot.getNavigator().tryMoveToXYZ(pos.x, pos.y, pos.z, 0.5F);
        if (pos.distanceSquared(new Vector3(robot.posX, robot.posY, robot.posZ)) <= 2) {
            if (!robot.worldObj.isRemote && robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory) {
                CraftingData data = (CraftingData) robot.getModuleData();
                int[] slots = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
                IInventory inventory = (IInventory) robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
                if (hasSpaceFor(inventory, data.getResult())) {
                    int[] slotSpace = new int[inventory.getSizeInventory()];
                    for (int i = 0; i < slotSpace.length; i++) {
                        ItemStack stack = inventory.getStackInSlot(i);
                        if (stack != null) {
                            slotSpace[i] = stack.stackSize;
                        }
                    }
                    for (int i = 0; i < slots.length; i++) {
                        if (data.getInput()[i] != null) {
                            for (int j = 0; j < inventory.getSizeInventory(); j++) {
                                ItemStack stack = inventory.getStackInSlot(j);
                                if (stack != null) {
                                    if (stack.isItemEqual(data.getInput()[i]) && slotSpace[j] > 0) {
                                        slots[i] = j;
                                        slotSpace[j]--;
                                    }
                                }
                            }
                        }
                    }
                    boolean allItems = true;
                    for (int i = 0; i < slots.length; i++) {
                        if (data.getInput()[i] != null && slots[i] == -1) {
                            allItems = false;
                        }
                    }

                    if (allItems) {
                        for (int i = 0; i < slots.length; i++) {
                            if (slots[i] != -1)
                                inventory.decrStackSize(slots[i], data.getInput()[i].stackSize);
                        }
                        putStack(inventory, data.getResult().copy());
                    }
                }
            }
        }
    }

    private boolean putStack(IInventory inventory, ItemStack item) {
        return putStack(inventory, item, 0);
    }

    private boolean putStack(IInventory inventory, ItemStack item, int startSlot) {
        for (int i = startSlot; i < inventory.getSizeInventory(); i++) {
            ItemStack item1 = inventory.getStackInSlot(i);
            if (item1 == null) {
                inventory.setInventorySlotContents(i, item);
                return true;
            }
            if (item.itemID != item1.itemID || item.getItemDamage() != item1.getItemDamage() || !ItemStack.areItemStackTagsEqual(item, item1)) {
                continue;
            } else {
                if (item.isStackable()) {
                    int newSize = item.stackSize + item1.stackSize;
                    if (newSize <= item.getMaxStackSize()) {
                        item1.stackSize = newSize;
                        return true;
                    } else if (newSize > item.getMaxStackSize()) {
                        item1.stackSize = item.getMaxStackSize();
                        item.stackSize = newSize - item.getMaxStackSize();
                        return putStack(inventory, item, i + 1);
                    }
                }
            }
        }

        return false;
    }

    private boolean hasSpaceFor(IInventory inventory, ItemStack result) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack == null)
                return true;
            if (stack.isItemEqual(result))
                if (stack.stackSize < stack.getMaxStackSize())
                    return true;
        }
        return false;
    }

}
