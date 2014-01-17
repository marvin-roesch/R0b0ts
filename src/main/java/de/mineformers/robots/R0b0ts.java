package de.mineformers.robots;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.mineformers.robots.api.R0b0tsPlugin;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.block.ModBlocks;
import de.mineformers.robots.chipset.ChipsetAdvanced;
import de.mineformers.robots.chipset.ChipsetBasic;
import de.mineformers.robots.chipset.ChipsetBlank;
import de.mineformers.robots.chipset.ChipsetExpert;
import de.mineformers.robots.config.ConfigurationHandler;
import de.mineformers.robots.creativetab.CreativeTabR0b0ts;
import de.mineformers.robots.entity.ModEntities;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.module.ModuleBlank;
import de.mineformers.robots.module.ModuleCollect;
import de.mineformers.robots.module.ModuleCrafting;
import de.mineformers.robots.network.PacketHandler;
import de.mineformers.robots.plugin.PluginHandler;
import de.mineformers.robots.proxy.CommonProxy;
import de.mineformers.robots.util.PrivateRobotHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

/**
 * R0b0ts
 * <p/>
 * R0b0ts
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        dependencies = Reference.DEPENDENCIES,
        certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(
        channels = {Reference.CHANNEL_NAME},
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class)
public class R0b0ts {

    @Instance(Reference.MOD_ID)
    public static R0b0ts instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,
            serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabR0b0ts(CreativeTabs.getNextID());

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(PluginHandler.instance());
        PluginHandler.registerDefaults();
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory(), Reference.MOD_ID + ".cfg"));

        proxy.registerHandlers();

        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModuleRegistry.instance().registerModule(new ModuleBlank());
        ModuleRegistry.instance().registerModule(new ModuleCrafting());
        ModuleRegistry.instance().registerModule(new ModuleCollect());
        ChipsetRegistry.instance().registerChipset(new ChipsetBlank());
        ChipsetRegistry.instance().registerChipset(new ChipsetBasic());
        ChipsetRegistry.instance().registerChipset(new ChipsetAdvanced());
        ChipsetRegistry.instance().registerChipset(new ChipsetExpert());
        for (R0b0tsPlugin plugin : PluginHandler.plugins())
            plugin.preInit(ModuleRegistry.instance());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        proxy.registerTileEntities();
        proxy.registerRenderers();
        for (R0b0tsPlugin plugin : PluginHandler.plugins())
            plugin.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModBlocks.registerRecipes();
        for (R0b0tsPlugin plugin : PluginHandler.plugins())
            plugin.postInit();
        ModContainer container = FMLCommonHandler.instance().findContainerFor(this);
        container.getMetadata().description += "\n\n";
        if (PluginHandler.plugins().size() == 0)
            container.getMetadata().description += "\247cNo plugins loaded";
        else {
            container.getMetadata().description += "\247aLoaded plugins:\n";
            int i = 0;
            for (R0b0tsPlugin plugin : PluginHandler.plugins()) {
                if (i > 0)
                    container.getMetadata().description += ", ";
                container.getMetadata().description += plugin.getName() + " (" + plugin.getVersion() + ")";
                i++;
            }
        }

        for(RobotModule module : ModuleRegistry.instance().getModules())
            module.registerRecipe(PrivateRobotHelper.createModuleStack(module), PrivateRobotHelper.createDefaultModule());
        for(RobotChipset chipset : ChipsetRegistry.instance().getChipsets())
            chipset.registerRecipe(PrivateRobotHelper.createChipsetStack(chipset), PrivateRobotHelper.createDefaultChipset());
        proxy.loadManual();
    }
}
