package de.mineformers.robots.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.mineformers.robots.client.renderer.TileFactoryControllerRenderer;
import de.mineformers.robots.client.renderer.entity.RenderRobot;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.lib.RenderIds;
import de.mineformers.robots.tileentity.TileFactoryController;

/**
 * R0b0ts
 * <p/>
 * ClientProxy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFactoryController.class, new TileFactoryControllerRenderer());
    }
}
