package de.mineformers.robots.inventory;

import de.mineformers.robots.client.gui.container.FilteredIconSlot;
import de.mineformers.robots.client.gui.util.StackFilter;
import de.mineformers.robots.item.ItemModule;
import de.mineformers.robots.lib.ItemIds;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * R0b0ts
 * <p/>
 * ContainerFactoryController
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ContainerFactoryController extends Container {

    TileFactoryController tile;
    private final int PLAYER_INVENTORY_ROWS = 3;
    private final int PLAYER_INVENTORY_COLUMNS = 9;

    public ContainerFactoryController(InventoryPlayer inventoryPlayer, IInventory inventory) {
        tile = (TileFactoryController) inventory;
        this.addSlotToContainer(new RobotModuleSlot(inventory, 0, 22, 18));
        this.addSlotToContainer(new FilteredIconSlot(inventory, 1, 22, 36, ResourceHelper.getModResource("textures/gui/slotModule.png"), new StackFilter(ItemIds.MODULE)));
        this.addSlotToContainer(new FilteredIconSlot(inventory, 2, 22, 54, ResourceHelper.getModResource("textures/gui/slotModule.png"), new StackFilter(ItemIds.MODULE)));
        this.addSlotToContainer(new FilteredIconSlot(inventory, 3, 22, 72, ResourceHelper.getModResource("textures/gui/slotModule.png"), new StackFilter(ItemIds.MODULE)));

        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 118 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 176));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex < 4) {
                if (!this.mergeItemStack(slotItemStack, 3, inventorySlots.size(), false)) {
                    return null;
                }
            } else {
                if (slotItemStack.getItem() instanceof ItemModule) {
                    if (!this.mergeItemStack(slotItemStack, 0, 1, false)) {
                        return null;
                    }
                }
            }

            if (slotItemStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
