package de.mineformers.robots.api.registry;

import de.mineformers.robots.api.data.RobotChipset;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * R0b0ts
 * <p/>
 * ChipsetRegistry
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChipsetRegistry
{

    private static ChipsetRegistry instance;
    private LinkedHashMap<String, RobotChipset> chipsets;

    public static ChipsetRegistry instance()
    {
        if (instance == null)
            instance = new ChipsetRegistry();
        return instance;
    }

    private ChipsetRegistry()
    {
        chipsets = new LinkedHashMap<String, RobotChipset>();
    }

    public void registerChipset(RobotChipset chipset)
    {
        chipsets.put(chipset.getIdentifier(), chipset);
    }

    public RobotChipset getChipset(String identifier)
    {
        return chipsets.get(identifier);
    }

    public Collection<RobotChipset> getChipsets()
    {
        return chipsets.values();
    }

}
