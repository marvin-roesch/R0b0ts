package de.mineformers.robots.client.gui.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GUISystem
 * <p/>
 * StackFilter
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class StackFilter {

    private boolean filterId, filterDamage, filterNBT;
    private int id, damage;
    private NBTTagCompound nbt;

    public StackFilter(int id) {
        this(id, -1, null);
    }

    public StackFilter(int id, int damage) {
        this(id, damage, null);
    }

    public StackFilter(int id, int damage, NBTTagCompound nbt) {
        this.id = id;
        this.damage = damage;
        this.nbt = nbt;
        this.filterId = id != -1;
        this.filterDamage = damage != -1;
        this.filterNBT = nbt != null;
    }

    public int getId() {
        return (filterId) ? id : -1;
    }

    public int getDamage() {
        return (filterDamage) ? damage : -1;
    }

    public NBTTagCompound getNBT() {
        return (filterNBT) ? nbt : null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof ItemStack) {
                ItemStack stack = (ItemStack) obj;
                return (stack.itemID == id || this.filterId == false) &&
                        (stack.getItemDamage() == damage || this.filterDamage == false) &&
                        ((stack.hasTagCompound() && stack.getTagCompound().equals(nbt)) || this.filterNBT == false);
            }

            if (obj instanceof StackFilter) {
                StackFilter filter = (StackFilter) obj;
                return (filter.getId() == id || this.filterId == false) &&
                        (filter.getDamage() == damage || this.filterDamage == false) &&
                        (filter.getNBT().equals(nbt) || this.filterNBT == false);
            }
        }

        return false;
    }
}
