package de.mineformers.robots.entity;

import de.mineformers.robots.api.RobotChipset;
import de.mineformers.robots.api.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

/**
 * R0b0ts
 * <p/>
 * EntityRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityRobot extends EntityLiving {

    public EntityRobot(World world) {
        super(world);
        dataWatcher.addObject(13, 0);
        dataWatcher.addObject(14, "blank");
        dataWatcher.addObject(15, "blank");
        dataWatcher.addObject(16, 0);
        dataWatcher.addObject(17, 0);
        dataWatcher.addObject(18, 0);
    }

    public EntityRobot(World world, Vector3 boundInventory) {
        super(world);
        dataWatcher.addObject(13, 0);
        dataWatcher.addObject(14, "blank");
        dataWatcher.addObject(15, "blank");
        dataWatcher.addObject(16, (float) boundInventory.x);
        dataWatcher.addObject(17, (float) boundInventory.y);
        dataWatcher.addObject(18, (float) boundInventory.z);
    }

    public void setBoundInventory(Vector3 boundInventory) {
        dataWatcher.updateObject(16, (float) boundInventory.x);
        dataWatcher.updateObject(17, (float) boundInventory.y);
        dataWatcher.updateObject(18, (float) boundInventory.z);
    }

    public Vector3 getBoundInventory(Vector3 boundInventory) {
        return new Vector3(dataWatcher.getWatchableObjectFloat(16), dataWatcher.getWatchableObjectFloat(17), dataWatcher.getWatchableObjectFloat(18));
    }

    public int getArmorId() {
        return dataWatcher.getWatchableObjectInt(13);
    }

    public void setArmorId(int id) {
        dataWatcher.updateObject(13, id);
    }

    public RobotModule getModule() {
        return ModuleRegistry.instance().getModule(dataWatcher.getWatchableObjectString(14));
    }

    public void setModule(RobotModule module) {
        dataWatcher.updateObject(14, module.getIdentifier());
    }

    public RobotChipset getChipset() {
        return ChipsetRegistry.instance().getChipset(dataWatcher.getWatchableObjectString(15));
    }

    public void setChipset(RobotChipset chipset) {
        dataWatcher.updateObject(15, chipset.getIdentifier());
    }

}
