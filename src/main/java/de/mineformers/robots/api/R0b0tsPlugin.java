package de.mineformers.robots.api;

import de.mineformers.robots.api.registry.ModuleRegistry;

/**
 * R0b0ts
 * <p/>
 * R0b0tsPlugin
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class R0b0tsPlugin {

    public abstract String getId();

    public abstract String getName();

    public abstract String getVersion();

    public void preInit(ModuleRegistry moduleRegistry) {

    }

    public void init() {

    }

    public void postInit() {

    }

}
