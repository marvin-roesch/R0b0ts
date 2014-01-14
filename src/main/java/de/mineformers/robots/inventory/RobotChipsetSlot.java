package de.mineformers.robots.inventory;

import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.client.gui.container.IconSlot;
import de.mineformers.robots.item.ItemChipset;
import de.mineformers.robots.item.ItemModule;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * RobotChipsetSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RobotChipsetSlot extends IconSlot {

    public RobotChipsetSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y, ResourceHelper.getModResource("textures/gui/slotChipset.png"));
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack.getItem() instanceof ItemChipset)
            return !RobotHelper.getChipsetFromItemStack(stack).getIdentifier().equals("blank");
        return false;
    }

}
