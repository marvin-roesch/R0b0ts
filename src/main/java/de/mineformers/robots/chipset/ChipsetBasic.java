package de.mineformers.robots.chipset;

import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.util.ResourceHelper;

/**
 * R0b0ts
 * <p/>
 * ChipsetBasic
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetBasic extends RobotChipset {

    public ChipsetBasic() {
        super("basic", "robots:basic", "robots:chipsets/basic", 8, ResourceHelper.getModResource("textures/entities/chipset_basic.png"));
        this.setDescription("robots:basic");
    }

}
