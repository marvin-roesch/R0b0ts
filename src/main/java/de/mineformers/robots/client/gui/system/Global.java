package de.mineformers.robots.client.gui.system;

import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * Global
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Global {

    public static String MOD_ID = Reference.MOD_ID;

    public static String TEXTURE_PATH = "textures/guiWidgets.png";

    public static ResourceLocation getTexture() {
        return ResourceHelper.getModResource(TEXTURE_PATH);
    }
}
