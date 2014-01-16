package de.mineformers.robots.module.ai;

import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.EntityLiving;
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

    private EntityLiving living;
    private Vector3 pos;

    public AICrafting(EntityLiving entity) {
        this.living = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if(pos == null)
            pos = ((EntityRobot) living).getBoundInventory();
        return true;
    }

    public boolean continueExecuting() {
        return !this.living.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.living.getNavigator().tryMoveToXYZ(pos.x, pos.y, pos.z, 0.5F);
        if (pos.distanceSquared(new Vector3(living.posX, living.posY, living.posZ)) <= 2) {
            EntityRobot robot = (EntityRobot) living;
            if (living.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z) instanceof IInventory) {
                IInventory inventory = (IInventory) living.worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
                for(int i = 0; i < inventory.getSizeInventory(); i++) {
                    ItemStack stack = inventory.getStackInSlot(i);
                    if(stack != null) {
                        ItemStack roboStack = robot.getStackInSlot(0);
                        if(roboStack == null) {
                            roboStack = inventory.getStackInSlot(i);

                        }
                    }
                }
            }
        }
    }
}
