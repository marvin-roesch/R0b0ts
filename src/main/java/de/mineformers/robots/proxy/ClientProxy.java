package de.mineformers.robots.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.mineformers.robots.api.RobotModule;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.gui.WindowRobotFactory;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiContainer;
import de.mineformers.robots.client.renderer.entity.RenderRobot;
import de.mineformers.robots.client.renderer.tileentity.TileFactoryControllerRenderer;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

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
    public void updateFactoryGui(TileFactoryController tile) {
        super.updateFactoryGui(tile);
        GuiScreen screen = Minecraft.getMinecraft().currentScreen;
        if (screen instanceof WidgetGuiContainer) {
            UIPanel panel = ((WidgetGuiContainer) screen).getPanel();
            if (panel instanceof WindowRobotFactory) {
                RobotModule module = ModuleRegistry.instance().getModule(tile.getSelectedModule());
                String localized = module.getLocalizedName();
                ((WindowRobotFactory) panel).setCurrentModule(localized);
            }
        }
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFactoryController.class, new TileFactoryControllerRenderer());
    }
}
