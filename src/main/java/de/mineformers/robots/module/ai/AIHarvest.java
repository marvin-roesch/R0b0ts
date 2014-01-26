package de.mineformers.robots.module.ai;

import cpw.mods.fml.relauncher.ReflectionHelper;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.util.Vector3;
import de.mineformers.robots.util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

/**
 * R0b0ts
 * <p/>
 * AIHarvest
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AIHarvest extends EntityAIBase
{

    private EntityRobot robot;
    private Vector3 target;
    private Vector3 pos;

    public AIHarvest(EntityRobot entity)
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
        if (target == null)
        {
            int range = robot.getChipset().getRange();
            List<Vector3> result = WorldHelper.getBlocksWithinAABB(robot.worldObj, BlockCrops.class, AxisAlignedBB.getBoundingBox(pos.x - range / 2, pos.y - 1, pos.z - range / 2, pos.x + range / 2, pos.y + 1, pos.z + range / 2));
            if (result.size() > 0)
            {
                for (Vector3 vec : result)
                {
                    if (robot.worldObj.getBlockMetadata((int) vec.x, (int) vec.y, (int) vec.z) == 7)
                    {
                        target = vec;
                        break;
                    }
                }
            }
        }
        return true;
    }

    public boolean continueExecuting()
    {
        return !this.robot.getNavigator().noPath();
    }

    public void startExecuting()
    {
        if (!robot.worldObj.isRemote)
        {
            if (target != null && !robot.worldObj.isAirBlock((int) target.x, (int) target.y, (int) target.z)
                    && Block.blocksList[robot.worldObj.getBlockId((int) target.x, (int) target.y, (int) target.z)] instanceof BlockCrops
                    && robot.worldObj.getBlockMetadata((int) target.x, (int) target.y, (int) target.z) == 7)
            {
                robot.getNavigator().tryMoveToXYZ(target.x, target.y, target.z, 0.5F);
                if (robot.getDistanceSq(target.x, target.y, target.z) <= 2)
                {
                    robot.worldObj.destroyBlock((int) target.x, (int) target.y, (int) target.z, true);
                    List drops = robot.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(target.x - 1D, target.y - 1, target.z - 1D, target.x + 1D, target.y + 1, target.z + 1D));
                    for (Object o : drops)
                    {
                        if (o instanceof EntityItem)
                        {
                            EntityItem item = (EntityItem) o;
                            if (item.getEntityItem().getItem() instanceof ItemSeeds && !item.isDead)
                            {
                                if (robot.worldObj.isAirBlock((int) target.x, (int) target.y, (int) target.z) &&
                                        Block.blocksList[robot.worldObj.getBlockId((int) target.x, (int) target.y - 1, (int) target.z)].canSustainPlant(robot.worldObj, (int) target.x, (int) target.y - 1, (int) target.z, ForgeDirection.UP, ((ItemSeeds) item.getEntityItem().getItem())))
                                {
                                    Integer blockID = ReflectionHelper.getPrivateValue(ItemSeeds.class, (ItemSeeds) item.getEntityItem().getItem(), "blockType", "field_77839_a");
                                    robot.worldObj.setBlock((int) target.x, (int) target.y, (int) target.z, blockID, 0, 3);
                                    item.setDead();
                                    break;
                                }
                            }
                        }
                    }
                    target = null;
                }
            } else
            {
                robot.getNavigator().tryMoveToXYZ(pos.x, pos.y, pos.z, 0.5F);
                target = null;
            }
        }
    }
}
