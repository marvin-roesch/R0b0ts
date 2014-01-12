package de.mineformers.robots.util;

import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.lib.Reference;
import net.minecraft.util.StatCollector;

/**
 * GUISystem
 * <p/>
 * LangHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LangHelper {

    public static String translate(String key) {
        return StatCollector.translateToLocal(key);
    }

    public static String translate(String type, String key) {
        return translate(Reference.RESOURCE_PATH, type, key);
    }

    public static String translate(String modid, String type, String key) {
        return translate(getString(modid, type, key));
    }

    public static String getString(String modid, String type, String key) {
        return type + "." + modid + ":" + key;
    }

}
