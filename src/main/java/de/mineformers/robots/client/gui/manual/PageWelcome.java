package de.mineformers.robots.client.gui.manual;

import net.minecraft.util.EnumChatFormatting;

/**
 * R0b0ts
 * <p/>
 * PageWelcome
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageWelcome extends PageText {

    public PageWelcome() {
        super("Welcome", "I'm your personal BuddyBot.<br>" +
                "I'll give you advice on how to<br>" +
                "create your very own robots!<br>" +
                "Turn to the next page to get<br>" +
                "started.<br><br>" +
                EnumChatFormatting.UNDERLINE + "Content:<br>" +
                "1. Recipes<br>" +
                "2. Building the factory<br>" +
                "3. Creating your robot");
    }

}
