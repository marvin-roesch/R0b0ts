package de.mineformers.robots.chipset;

import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;

/**
 * R0b0ts
 * <p/>
 * ChipsetAdvanced
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetAdvanced extends RobotChipset {

    public ChipsetAdvanced() {
        super("advanced", "robots:advanced", "robots:chipsets/advanced", 16, ResourceHelper.getModResource("textures/entities/chipset_advanced.png"));
        this.setDescription("robots:advanced");
    }

}
