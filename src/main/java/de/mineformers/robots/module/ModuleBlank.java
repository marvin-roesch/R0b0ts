package de.mineformers.robots.module;

import de.mineformers.robots.api.RobotModule;

/**
 * R0b0ts
 * <p/>
 * ModuleBlank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModuleBlank extends RobotModule {

    public ModuleBlank() {
        super("blank", "robots:blank", "robots:modules/blank", null);
    }

    @Override
    public String getLocalizedName() {
        return "\2477\247o" + super.getLocalizedName() + "\247r";
    }
}
