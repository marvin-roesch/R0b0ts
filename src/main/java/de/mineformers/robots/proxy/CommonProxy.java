package de.mineformers.robots.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.client.gui.WindowCraftingRecipe;
import de.mineformers.robots.client.gui.WindowRobotFactory;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiContainer;
import de.mineformers.robots.inventory.ContainerCraftingRecipe;
import de.mineformers.robots.inventory.ContainerFactoryController;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

/**
 * R0b0ts
 * <p/>
 * CommonProxy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommonProxy implements IGuiHandler {

    public void updateFactoryGui(TileFactoryController tile) {

    }

    public void registerRenderers() {

    }

    public void loadManual() {

    }

    public void registerHandlers() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileFactoryController.class, TileFactoryController.class.getSimpleName());
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ContainerFactoryController(player.inventory, (IInventory) world.getBlockTileEntity(x, y, z));
            case 1:
                return new ContainerCraftingRecipe(player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IInventory inventory = (IInventory) world.getBlockTileEntity(x, y, z);
        switch (ID) {
            case 0:
                return new WidgetGuiContainer(176, 200, new WindowRobotFactory((TileFactoryController) inventory), new ContainerFactoryController(player.inventory, inventory), inventory, true);
            case 1:
                return new WidgetGuiContainer(176, 200, new WindowCraftingRecipe(), new ContainerCraftingRecipe(player.inventory), null, true);
        }
        return null;
    }
}
