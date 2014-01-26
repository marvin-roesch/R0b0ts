package de.mineformers.robots.api.event;

import de.mineformers.robots.api.R0b0tsPlugin;
import net.minecraftforge.event.Event;

/**
 * R0b0ts
 * <p/>
 * RegisterPluginEvent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RegisterPluginEvent extends Event
{

    public R0b0tsPlugin plugin;

    public RegisterPluginEvent(R0b0tsPlugin plugin)
    {
        this.plugin = plugin;
    }

}
