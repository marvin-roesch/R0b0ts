package de.mineformers.robots.item;

import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * R0b0ts
 * <p/>
 * ItemBuddy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBuddy extends ItemBase
{

    public ItemBuddy(int id)
    {
        super(id, Strings.BUDDY_NAME);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        if (!world.isRemote)
        {
            EntityBuddyBot buddy = new EntityBuddyBot(world);
            buddy.setPosition(x + dir.offsetX + 0.5F, y + dir.offsetY + 0.5F, z + dir.offsetZ + 0.5F);
            buddy.setOwner(player.getCommandSenderName());
            buddy.setCustomNameTag(stack.hasDisplayName() ? stack.getDisplayName() : String.format(LangHelper.translate("misc", "buddyFormat"), player.getDisplayName()));
            world.spawnEntityInWorld(buddy);
            if (!player.capabilities.isCreativeMode)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        }
        return true;
    }
}
