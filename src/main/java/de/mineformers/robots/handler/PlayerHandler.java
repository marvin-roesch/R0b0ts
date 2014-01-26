package de.mineformers.robots.handler;

import cpw.mods.fml.common.IPlayerTracker;
import de.mineformers.robots.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * R0b0ts
 * <p/>
 * PlayerHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PlayerHandler implements IPlayerTracker
{

    @ForgeSubscribe
    public void onPlayerJoinWorld(EntityEvent.EntityConstructing event)
    {
        if (event.entity != null)
            if (event.entity.worldObj != null)
                if (!event.entity.worldObj.isRemote)
                    if (event.entity instanceof EntityPlayer)
                    {
                        EntityPlayer player = (EntityPlayer) event.entity;
                        if (player
                                .getExtendedProperties(R0b0tsPlayerProperties.IDENTIFIER) == null)
                            player.registerExtendedProperties(
                                    R0b0tsPlayerProperties.IDENTIFIER,
                                    new R0b0tsPlayerProperties());
                    }
    }

    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        R0b0tsPlayerProperties properties = R0b0tsPlayerProperties.getByEntity(player);
        if (!properties.hasReceivedBuddy())
        {
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.buddy));
            properties.setReceivedBuddy(true);
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {

    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {

    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {

    }
}
