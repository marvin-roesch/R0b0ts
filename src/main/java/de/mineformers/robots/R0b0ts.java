package de.mineformers.robots;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.mineformers.robots.block.ModBlocks;
import de.mineformers.robots.config.ConfigurationHandler;
import de.mineformers.robots.creativetab.CreativeTabR0b0ts;
import de.mineformers.robots.entity.ModEntities;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.network.PacketHandler;
import de.mineformers.robots.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

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
        ConfigurationHandler.init(new File(event.getModConfigurationDirectory(), Reference.MOD_ID + ".cfg"));

        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        proxy.registerTileEntities();
        proxy.registerRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
