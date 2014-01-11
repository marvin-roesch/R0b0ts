package de.mineformers.robots.client.gui.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * IconSlot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class IconSlot extends Slot {

    private ResourceLocation texture;

    public IconSlot(IInventory inventory, int index, int x, int y, ResourceLocation texture) {
        super(inventory, index, x, y);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getBackgroundIconTexture() {
        return texture;
    }
}
