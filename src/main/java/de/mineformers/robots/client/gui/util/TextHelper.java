package de.mineformers.robots.client.gui.util;

/**
 * GUISystem
 * <p/>
 * TextHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TextHelper
{

    public static String getLongestString(String... strings)
    {
        String s = "";
        int longest = 0;

        for (String string : strings)
        {
            if (longest < string.length())
            {
                s = string;
                longest = string.length();
            }
        }

        return s;
    }

}
