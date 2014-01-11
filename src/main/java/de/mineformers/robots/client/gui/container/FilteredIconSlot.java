package de.mineformers.robots.client.gui.container;

import de.mineformers.robots.client.gui.util.StackFilter;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * GUISystem
 * <p/>
 * FilteredIconSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FilteredIconSlot extends FilteredSlot {

    private ResourceLocation texture;

    public FilteredIconSlot(IInventory inventory, int index, int x, int y, ResourceLocation texture, StackFilter... filters) {
        super(inventory, index, x, y, filters);
        this.texture = texture;
    }

    public FilteredIconSlot(IInventory inventory, int index, int x, int y, ResourceLocation texture, ArrayList<StackFilter> filters) {
        super(inventory, index, x, y, filters);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getBackgroundIconTexture() {
        return texture;
    }
}
