package de.mineformers.robots.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.robots.entity.EntityBuddyBot;
import net.minecraft.entity.player.EntityPlayer;

/**
 * R0b0ts
 * <p/>
 * PacketBuddyBotSit
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PacketBuddyBotSit extends PacketBase {

    private int id;

    public PacketBuddyBotSit() {

    }

    public PacketBuddyBotSit(int id) {
        this.id = id;
    }

    @Override
    public void write(ByteArrayDataOutput out) {
        out.writeInt(id);
    }

    @Override
    public void read(ByteArrayDataInput in) {
        id = in.readInt();
    }

    @Override
    public void execute(EntityPlayer player, Side side) {
        if (player.getEntityWorld().getEntityByID(id) instanceof EntityBuddyBot) {
            ((EntityBuddyBot) player.getEntityWorld().getEntityByID(id)).setSitting(!((EntityBuddyBot) player.getEntityWorld().getEntityByID(id)).isSitting());
        }
    }
}
