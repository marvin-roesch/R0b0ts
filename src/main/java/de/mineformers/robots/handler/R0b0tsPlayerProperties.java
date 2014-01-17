package de.mineformers.robots.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * R0b0ts
 * <p/>
 * R0b0tsPlayerProperties
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class R0b0tsPlayerProperties implements IExtendedEntityProperties {

    public static final String IDENTIFIER = "R0b0tsProperties";

    private boolean receivedBuddy;

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("ReceivedBuddy", receivedBuddy);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        receivedBuddy = compound.getBoolean("ReceivedBuddy");
    }

    @Override
    public void init(Entity entity, World world) {
    }

    public void setReceivedBuddy(boolean hasReceivedBuddy) {
        this.receivedBuddy = hasReceivedBuddy;
    }

    public boolean hasReceivedBuddy() {
        return receivedBuddy;
    }

    public static R0b0tsPlayerProperties getByEntity(EntityPlayer player) {
        if (player.getExtendedProperties(IDENTIFIER) == null)
            player.registerExtendedProperties(IDENTIFIER,
                    new R0b0tsPlayerProperties());

        return (R0b0tsPlayerProperties) player.getExtendedProperties(IDENTIFIER);
    }
}
