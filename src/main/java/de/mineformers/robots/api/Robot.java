package de.mineformers.robots.api;

import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;

/**
 * R0b0ts
 * <p/>
 * Robot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Robot {

    private RobotModule module;
    private RobotChipset chipset;
    private int armorId;

    public Robot(String moduleId, String chipsetId, int armorId) {
        this(ModuleRegistry.instance().getModule(moduleId), ChipsetRegistry.instance().getChipset(chipsetId), armorId);
    }

    public Robot(RobotModule module, RobotChipset chipset, int armorId) {
        this.module = module;
        this.chipset = chipset;
        this.armorId = armorId;
    }

    public RobotModule getModule() {
        return module;
    }

    public RobotChipset getChipset() {
        return chipset;
    }

    public int getArmorId() {
        return armorId;
    }
}
