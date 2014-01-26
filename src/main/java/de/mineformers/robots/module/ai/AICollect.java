package de.mineformers.robots.module.ai;

import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * R0b0ts
 * <p/>
 * AICollect
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AICollect extends EntityAIBase
{

    private EntityItem target;
    private EntityRobot robot;
    private Vector3 pos;

    public AICollect(EntityRobot entity)
    {
        this.robot = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (pos == null)
            pos = robot.getBoundInventory();
        if (robot.worldObj.isRemote)
            return true;
        if (!robot.worldObj.isRemote)
        {
            if (robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory)
            {
                if (target != null && target.isDead)
                    target = null;
                if (target == null && robot.getStackInSlot(0) == null)
                {
                    int range = robot.getChipset().getRange();
                    List<?> entities = robot.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(pos.x - range / 2, pos.y - 1, pos.z - range / 2, pos.x + range / 2, pos.y + 1, pos.z + range / 2));
                    if (entities.size() > 0 && robot.getStackInSlot(0) == null)
                        target = (EntityItem) entities.get(0);
                }
            }
        }
        return robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory;
    }

    public boolean continueExecuting()
    {
        return !this.robot.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        if (!robot.worldObj.isRemote)
        {
            if (target != null && target.isDead)
                target = null;
            if (target != null && !target.isDead)
            {
                this.robot.getNavigator().tryMoveToXYZ(target.posX, target.posY, target.posZ, 0.5F);
                if (new Vector3(robot.posX, robot.posY, robot.posZ).distanceSquared(new Vector3(target.posX, target.posY, target.posZ)) <= 2)
                {
                    target.setDead();
                    robot.setInventorySlotContents(0, target.getEntityItem());
                    target = null;
                }
            } else
            {
                this.robot.getNavigator().tryMoveToXYZ(pos.x, pos.y, pos.z, 0.5F);
                if (pos.distanceSquared(new Vector3(robot.posX, robot.posY, robot.posZ)) <= 3)
                {
                    IInventory inventory = (IInventory) robot.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
                    if (robot.getStackInSlot(0) != null)
                    {
                        if (hasSpaceFor(inventory, robot.getStackInSlot(0)))
                            if (this.putStack(inventory, robot.getStackInSlot(0)))
                            {
                                robot.setInventorySlotContents(0, null);
                            }
                    }
                }
            }
        }
    }

    private boolean putStack(IInventory inventory, ItemStack item)
    {
        return putStack(inventory, item, 0);
    }

    private boolean putStack(IInventory inventory, ItemStack item, int startSlot)
    {
        for (int i = startSlot; i < inventory.getSizeInventory(); i++)
        {
            ItemStack item1 = inventory.getStackInSlot(i);
            if (item1 == null)
            {
                inventory.setInventorySlotContents(i, item);
                return true;
            }
            if (item.itemID != item1.itemID || item.getItemDamage() != item1.getItemDamage() || !ItemStack.areItemStackTagsEqual(item, item1))
            {
                continue;
            } else
            {
                if (item.isStackable())
                {
                    int newSize = item.stackSize + item1.stackSize;
                    if (newSize <= item.getMaxStackSize())
                    {
                        item1.stackSize = newSize;
                        return true;
                    } else if (newSize > item.getMaxStackSize())
                    {
                        item1.stackSize = item.getMaxStackSize();
                        item.stackSize = newSize - item.getMaxStackSize();
                        return putStack(inventory, item, i + 1);
                    }
                }
            }
        }

        return false;
    }

    private boolean hasSpaceFor(IInventory inventory, ItemStack result)
    {
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
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
