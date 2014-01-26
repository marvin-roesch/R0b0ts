package de.mineformers.robots.inventory;

import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.client.gui.container.IconSlot;
import de.mineformers.robots.item.ItemModule;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * RobotModuleSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RobotModuleSlot extends IconSlot
{

    public RobotModuleSlot(IInventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y, ResourceHelper.getModResource("textures/gui/slotModule.png"));
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (stack.getItem() instanceof ItemModule)
            return !RobotHelper.getModuleFromItemStack(stack).getIdentifier().equals("blank");
        return false;
    }
}
