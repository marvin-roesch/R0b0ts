package de.mineformers.robots.client.gui.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * R0b0ts
 * <p/>
 * ClassStackFilter
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClassStackFilter extends StackFilter {

    private boolean filterType;
    private Class<? extends Item> type;

    public ClassStackFilter(Class<? extends Item> type) {
        this(type, -1, null);
    }

    public ClassStackFilter(Class<? extends Item> type, int damage) {
        this(type, damage, null);
    }

    public ClassStackFilter(Class<? extends Item> type, int damage, NBTTagCompound nbt) {
        super(-1, damage, nbt);
        this.type = type;
        filterType = type != null;
    }

    public Class<? extends Item> getType() {
        return type;
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
                return (type.isAssignableFrom(stack.getItem().getClass()) || this.filterType == false) &&
                        (stack.getItemDamage() == damage || this.filterDamage == false) &&
                        ((stack.hasTagCompound() && stack.getTagCompound().equals(nbt)) || this.filterNBT == false);
            }

            if (obj instanceof ClassStackFilter) {
                ClassStackFilter filter = (ClassStackFilter) obj;
                return (type.isAssignableFrom(filter.getType()) || this.filterType == false) &&
                        (filter.getDamage() == damage || this.filterDamage == false) &&
                        (filter.getNBT().equals(nbt) || this.filterNBT == false);
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
