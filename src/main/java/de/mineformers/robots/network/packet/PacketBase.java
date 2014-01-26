package de.mineformers.robots.network.packet;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.robots.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

/**
 * R0b0ts
 * <p/>
 * PacketBase
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class PacketBase
{

    private static ImmutableBiMap<Integer, Class<? extends PacketBase>> idMap;

    static
    {
        ImmutableBiMap.Builder<Integer, Class<? extends PacketBase>> builder = ImmutableBiMap.builder();
        builder.put(0, PacketFactoryController.class);
        builder.put(1, PacketStartFactory.class);
        builder.put(2, PacketBuddyBotSit.class);
        builder.put(3, PacketSetRecipe.class);
        builder.put(4, PacketFactoryEnergy.class);
        idMap = builder.build();
    }

    public static PacketBase constructPacket(int packetId)
            throws ProtocolException, InstantiationException,
            IllegalAccessException
    {
        Class<? extends PacketBase> clazz = idMap
                .get(Integer.valueOf(packetId));
        if (clazz == null)
        {
            throw new ProtocolException("Unknown Packet Id!");
        } else
        {
            return clazz.newInstance();
        }
    }

    @SuppressWarnings("serial")
    public static class ProtocolException extends Exception
    {

        public ProtocolException()
        {
        }

        public ProtocolException(String message, Throwable cause)
        {
            super(message, cause);
        }

        public ProtocolException(String message)
        {
            super(message);
        }

        public ProtocolException(Throwable cause)
        {
            super(cause);
        }
    }

    public final int getPacketId()
    {
        if (idMap.inverse().containsKey(getClass()))
        {
            return idMap.inverse().get(getClass()).intValue();
        } else
        {
            throw new RuntimeException("Packet " + getClass().getSimpleName()
                    + " is missing a mapping!");
        }
    }

    public final Packet makePacket()
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(getPacketId());
        write(out);
        return PacketDispatcher.getPacket(Reference.CHANNEL_NAME,
                out.toByteArray());
    }

    public abstract void write(ByteArrayDataOutput out);

    public abstract void read(ByteArrayDataInput in);

    public abstract void execute(EntityPlayer player, Side side);

}
