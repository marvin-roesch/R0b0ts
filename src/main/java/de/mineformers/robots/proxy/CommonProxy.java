package de.mineformers.robots.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.client.gui.WindowRobotFactory;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiScreen;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.entity.player.EntityPlayer;
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

    public void registerRenderers() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileFactoryController.class, TileFactoryController.class.getSimpleName());
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case 0:
                return new WidgetGuiScreen(176, 176, new WindowRobotFactory());
        }
        return null;
    }
}
