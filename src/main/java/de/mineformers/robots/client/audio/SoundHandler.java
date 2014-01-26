package de.mineformers.robots.client.audio;

import cpw.mods.fml.common.FMLLog;
import de.mineformers.robots.lib.Reference;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * R0b0ts
 * <p/>
 * SoundHandler
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SoundHandler
{

    private static final String SOUND_RESOURCE_LOCATION = Reference.RESOURCE_PATH.toLowerCase() + ":";
    private static final String SOUND_PREFIX = Reference.RESOURCE_PATH.toLowerCase() + ":";

    public static String[] soundFiles = {SOUND_RESOURCE_LOCATION + "blip1.ogg", SOUND_RESOURCE_LOCATION + "blip2.ogg", SOUND_RESOURCE_LOCATION + "death.ogg"};

    public static String ROBOT_DEATH = SOUND_PREFIX + "death";
    public static String ROBOT_BLIP = SOUND_PREFIX + "blip";

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event)
    {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : soundFiles)
        {
            // Try to add the custom sound file to the pool of sounds
            try
            {
                event.manager.addSound(soundFile);
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e)
            {
                FMLLog.warning("Failed loading sound file: " + soundFile);
            }
        }
    }

}
