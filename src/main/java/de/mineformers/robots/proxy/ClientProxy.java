package de.mineformers.robots.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.audio.SoundHandler;
import de.mineformers.robots.client.gui.WindowRobotFactory;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiContainer;
import de.mineformers.robots.client.renderer.entity.RenderBuddyBot;
import de.mineformers.robots.client.renderer.entity.RenderRobot;
import de.mineformers.robots.client.renderer.tileentity.TileFactoryControllerRenderer;
import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

/**
 * R0b0ts
 * <p/>
 * ClientProxy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    private static final ResourceLocation robotDiamondTexture = ResourceHelper.getModResource("textures/entities/robot_diamond.png");
    private static final ResourceLocation robotGoldTexture = ResourceHelper.getModResource("textures/entities/robot_gold.png");
    private static final ResourceLocation robotDefaultTexture = ResourceHelper.getModResource("textures/entities/robot.png");

    public static ResourceLocation getRobotTexture(int itemID) {
        if(itemID == Item.plateDiamond.itemID)
            return robotDiamondTexture;
        if(itemID == Item.plateGold.itemID)
            return robotGoldTexture;
        return robotDefaultTexture;
    }

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
                ((WindowRobotFactory) panel).setCurrentChipset(ChipsetRegistry.instance().getChipset(tile.getSelectedChipset()).getLocalizedName());
                ((WindowRobotFactory) panel).setCurrentArmor(tile.getArmorId());
                ((WindowRobotFactory) panel).setProgress(tile.getProgress());
                ((WindowRobotFactory) panel).setButtonState(tile.getArmorId() != -1 && !tile.getSelectedModule().equals("blank") && !tile.getSelectedChipset().equals("blank") && !tile.isProgressing());
            }
        }
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
        RenderingRegistry.registerEntityRenderingHandler(EntityBuddyBot.class, new RenderBuddyBot());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFactoryController.class, new TileFactoryControllerRenderer());
    }
}
