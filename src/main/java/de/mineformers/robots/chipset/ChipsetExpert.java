package de.mineformers.robots.chipset;

import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;

/**
 * R0b0ts
 * <p/>
 * ChipsetExpert
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetExpert extends RobotChipset {

    public ChipsetExpert() {
        super("expert", "robots:expert", "robots:chipsets/expert", 32, ResourceHelper.getModResource("textures/entities/chipset_expert.png"));
        this.setDescription("robots:expert");
    }

}
