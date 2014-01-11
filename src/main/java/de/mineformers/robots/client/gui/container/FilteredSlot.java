package de.mineformers.robots.client.gui.container;

import de.mineformers.robots.client.gui.util.StackFilter;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * GUISystem
 * <p/>
 * FilteredSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FilteredSlot extends Slot {

    private ArrayList<StackFilter> filters;

    public FilteredSlot(IInventory inventory, int index, int x, int y, StackFilter... filters) {
        super(inventory, index, x, y);
        this.filters = new ArrayList<StackFilter>();
        for (StackFilter filter : filters)
            this.filters.add(filter);
    }

    public FilteredSlot(IInventory inventory, int index, int x, int y, ArrayList<StackFilter> filters) {
        super(inventory, index, x, y);
        this.filters = filters;
    }

    public void addFilter(StackFilter filter) {
        this.filters.add(filter);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        for (StackFilter filter : filters) {
            if (filter.equals(stack))
                return true;
        }
        return false;
    }
}
