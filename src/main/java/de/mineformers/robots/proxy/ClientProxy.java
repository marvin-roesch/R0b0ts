package de.mineformers.robots.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.audio.SoundHandler;
import de.mineformers.robots.client.gui.WindowIngameManual;
import de.mineformers.robots.client.gui.WindowRobotFactory;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiContainer;
import de.mineformers.robots.client.gui.util.SmallFontRenderer;
import de.mineformers.robots.client.renderer.entity.RenderBuddyBot;
import de.mineformers.robots.client.renderer.entity.RenderRobot;
import de.mineformers.robots.client.renderer.tileentity.TileFactoryControllerRenderer;
import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.PrivateRobotHelper;
import de.mineformers.robots.util.RecipeHelper;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * R0b0ts
 * <p/>
 * ClientProxy
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ClientProxy extends CommonProxy {

    public static SmallFontRenderer smallFontRenderer;
    private static final ResourceLocation robotDiamondTexture = ResourceHelper.getModResource("textures/entities/robot_diamond.png");
    private static final ResourceLocation robotGoldTexture = ResourceHelper.getModResource("textures/entities/robot_gold.png");
    private static final ResourceLocation robotDefaultTexture = ResourceHelper.getModResource("textures/entities/robot.png");

    public static ResourceLocation getRobotTexture(int itemID) {
        if (itemID == Item.plateDiamond.itemID)
            return robotDiamondTexture;
        if (itemID == Item.plateGold.itemID)
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
    public void loadManual() {
        super.loadManual();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(Minecraft.getMinecraft().getResourceManager().getResource(ResourceHelper.getModResource("xml/manual.xml")).getInputStream());
            doc.getDocumentElement().normalize();
            WindowIngameManual.document = doc;
            addManualRecipes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addManualRecipes() {
        ItemStack redstone = new ItemStack(Item.redstone);
        ItemStack gold = new ItemStack(Item.ingotGold);
        ItemStack greenDye = new ItemStack(Item.dyePowder, 1, 2);
        WindowIngameManual.recipes.put("blankChipset", RecipeHelper.createShaped(PrivateRobotHelper.createDefaultChipset(),
                redstone, gold, redstone,
                gold, greenDye, gold,
                redstone, gold, redstone));
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void registerRenderers() {
        smallFontRenderer = new SmallFontRenderer(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().renderEngine, false);
        RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot());
        RenderingRegistry.registerEntityRenderingHandler(EntityBuddyBot.class, new RenderBuddyBot());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFactoryController.class, new TileFactoryControllerRenderer());
    }
}
