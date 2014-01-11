package de.mineformers.robots.config;

import cpw.mods.fml.common.FMLLog;
import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import net.minecraftforge.common.Configuration;

import java.io.File;
import java.util.logging.Level;

/**
 * R0b0ts
 * <p/>
 * ConfigurationHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile) {
        configuration = new Configuration(configFile);

        try {
            BlockIds.FACTORY_CONTROLLER = configuration.getBlock(Strings.FACTORY_CONTROLLER_NAME, BlockIds.FACTORY_CONTROLLER_DEFAULT).getInt(BlockIds.FACTORY_CONTROLLER_DEFAULT);
            BlockIds.FACTORY_GLASS = configuration.getBlock(Strings.FACTORY_BASE_NAME, BlockIds.FACTORY_GLASS_DEFAULT).getInt(BlockIds.FACTORY_GLASS_DEFAULT);
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME
                    + " has had a problem loading its configuration");
        } finally {
            configuration.save();
        }
    }

}
