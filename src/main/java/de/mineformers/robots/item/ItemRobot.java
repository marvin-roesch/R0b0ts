package de.mineformers.robots.item;

import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.Robot;
import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.util.LangHelper;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

/**
 * R0b0ts
 * <p/>
 * ItemRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemRobot extends ItemBase
{

    public ItemRobot(int id)
    {
        super(id, Strings.ROBOT_NAME);
        this.setCreativeTab(null);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced)
    {
        super.addInformation(stack, player, list, advanced);
        Robot robot = RobotHelper.getRobotFromItemStack(stack);
        list.add("\247c" + LangHelper.translate("gui", "label.module") + ": \247r" + robot.getModule().getLocalizedName());
        list.add("\247c" + LangHelper.translate("gui", "label.chipset") + ": \247r" + robot.getChipset().getLocalizedName());
        list.add("\247c" + LangHelper.translate("gui", "label.armor") + ": \247r" + Item.itemsList[robot.getArmorId()].getStatName());
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        if (!world.isRemote)
        {
            Robot robot = RobotHelper.getRobotFromItemStack(stack);
            EntityRobot entity = new EntityRobot(world, new Vector3(x, y, z));
            entity.setModule(robot.getModule());
            IModuleData data = entity.getModuleData();
            data.readFromNBT(stack.getTagCompound().getCompoundTag("ModuleData"));
            entity.setModuleData(data);
            entity.setChipset(robot.getChipset());
            entity.setArmorId(robot.getArmorId());
            entity.setPosition(x + dir.offsetX + 0.5F, y + dir.offsetY + 0.5F, z + dir.offsetZ + 0.5F);
            entity.setCustomNameTag(stack.hasDisplayName() ? stack.getDisplayName() : String.format(LangHelper.translate("misc", "robotFormat"), player.getDisplayName()));
            entity.setOwner(player.getCommandSenderName());
            world.spawnEntityInWorld(entity);
            if (!player.capabilities.isCreativeMode)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        }
        return true;
    }

}
