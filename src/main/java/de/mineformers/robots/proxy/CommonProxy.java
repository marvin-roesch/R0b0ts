package de.mineformers.robots.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.tileentity.TileFactoryController;

/**
 * R0b0ts
 * <p/>
 * CommonProxy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommonProxy {

    public void registerRenderers() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileFactoryController.class, TileFactoryController.class.getSimpleName());
    }

}
