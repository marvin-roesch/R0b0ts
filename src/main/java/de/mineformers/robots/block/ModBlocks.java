package de.mineformers.robots.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.lib.Strings;
import net.minecraft.block.Block;

/**
 * R0b0ts
 * <p/>
 * ModBlocks
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModBlocks {

    public static Block factoryController;
    public static Block factory;

    public static void init() {
        factoryController = new BlockFactoryController(BlockIds.FACTORY_CONTROLLER);
        factory = new BlockFactoryGlass(BlockIds.FACTORY);

        GameRegistry.registerBlock(factoryController, Strings.FACTORY_CONTROLLER_NAME);
        GameRegistry.registerBlock(factory, Strings.FACTORY_GLASS_NAME);
    }

}
