package de.mineformers.robots.util;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * R0b0ts
 * <p/>
 * WorldHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldHelper
{

    public static <B> List<Vector3> getBlocksWithinAABB(World world, Class<? extends B> clazz, AxisAlignedBB bounds)
    {
        ArrayList<Vector3> list = new ArrayList<Vector3>();

        if (bounds.minX > bounds.maxX)
        {
            double oldMin = bounds.minX;
            bounds.minX = bounds.maxX;
            bounds.maxX = oldMin;
        }
        if (bounds.minY > bounds.minY)
        {
            double oldMin = bounds.minY;
            bounds.minY = bounds.maxY;
            bounds.maxY = oldMin;
        }
        if (bounds.minZ > bounds.minZ)
        {
            double oldMin = bounds.minZ;
            bounds.minZ = bounds.maxZ;
            bounds.maxZ = oldMin;
        }

        for (int x = (int) bounds.minX; x <= bounds.maxX; x++)
        {
            for (int y = (int) bounds.minY; y <= bounds.maxY; y++)
            {
                for (int z = (int) bounds.minZ; z <= bounds.maxZ; z++)
                {
                    if (!world.isAirBlock(x, y, z))
                        if (Block.blocksList[world.getBlockId(x, y, z)].getClass().isAssignableFrom(clazz))
                            list.add(new Vector3(x, y, z));
                }
            }
        }
        return list;
    }

}
