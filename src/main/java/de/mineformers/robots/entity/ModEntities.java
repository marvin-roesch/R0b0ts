package de.mineformers.robots.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import de.mineformers.robots.R0b0ts;

/**
 * R0b0ts
 * <p/>
 * ModEntities
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModEntities {

    public static void init() {
        EntityRegistry.registerModEntity(EntityRobot.class, "EntityRobot", 0,
                R0b0ts.instance, 80, 3, true);
    }

}
