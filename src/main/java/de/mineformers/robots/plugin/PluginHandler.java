package de.mineformers.robots.plugin;

import de.mineformers.robots.api.R0b0tsPlugin;
import de.mineformers.robots.api.event.RegisterPluginEvent;
import net.minecraftforge.event.ForgeSubscribe;

import java.util.Collection;
import java.util.HashMap;

/**
 * R0b0ts
 * <p/>
 * PluginHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PluginHandler {

    private static PluginHandler instance;
    private HashMap<String, R0b0tsPlugin> plugins;

    public PluginHandler() {
        plugins = new HashMap<String, R0b0tsPlugin>();
    }

    public static PluginHandler instance() {
        if (instance == null)
            instance = new PluginHandler();
        return instance;
    }

    public static R0b0tsPlugin getPlugin(String id) {
        return instance().plugins.get(id);
    }

    @ForgeSubscribe
    public void onPluginRegistered(RegisterPluginEvent event) {
        this.plugins.put(event.plugin.getId(), event.plugin);
    }

    public static void registerDefaults() {

    }

    public Collection<R0b0tsPlugin> iterator() {
        return plugins.values();
    }

    public static Collection<R0b0tsPlugin> plugins() {
        return instance().iterator();
    }

}
