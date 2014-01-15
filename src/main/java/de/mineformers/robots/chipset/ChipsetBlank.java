package de.mineformers.robots.chipset;

import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;

/**
 * R0b0ts
 * <p/>
 * ChipsetBlank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetBlank extends RobotChipset {

    public ChipsetBlank() {
        super("blank", "robots:blank", "robots:chipsets/blank", -1, ResourceHelper.getModResource("textures/entities/robot.png"));
    }

    @Override
    public String getLocalizedName() {
        return "\2477\247o" + super.getLocalizedName() + "\247r";
    }

}
