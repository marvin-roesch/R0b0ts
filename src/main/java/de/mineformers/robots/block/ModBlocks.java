package de.mineformers.robots.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.mineformers.robots.item.ItemBlockFactory;
import de.mineformers.robots.lib.BlockIds;
import de.mineformers.robots.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
        factory = new BlockFactory(BlockIds.FACTORY_BASE);

        GameRegistry.registerBlock(factoryController, Strings.FACTORY_CONTROLLER_NAME);
        GameRegistry.registerBlock(factory, ItemBlockFactory.class, Strings.FACTORY_BASE_NAME);
    }

    public static void registerRecipes() {
        ItemStack redstone = new ItemStack(Item.redstone);
        ItemStack stone = new ItemStack(Block.stone);
        ItemStack iron = new ItemStack(Item.ingotIron);
        ItemStack glassPane = new ItemStack(Block.thinGlass);
        ItemStack glass = new ItemStack(Block.glass);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(factoryController), "iri", "dgd", "iri", 'i', iron, 'r', redstone, 'g', glassPane, 'd', "dyeGreen"));
        GameRegistry.addShapedRecipe(new ItemStack(factory, 1, 0), "isi", "s s", "isi", 'i', iron, 's', stone);
        GameRegistry.addShapedRecipe(new ItemStack(factory, 1, 1), "isi", "sgs", "isi", 'i', iron, 'g', glass, 's', stone);
    }

}
