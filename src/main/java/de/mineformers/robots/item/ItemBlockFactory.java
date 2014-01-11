package de.mineformers.robots.item;

import de.mineformers.robots.lib.Strings;

/**
 * R0b0ts
 * <p/>
 * ItemBlockFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemBlockFactory extends ItemBlockSub {

    public ItemBlockFactory(int id) {
        super(id, Strings.FACTORY_BASE_NAME, new String[]{Strings.FACTORY_GLASS_NAME, Strings.FACTORY_FRAME_NAME});
    }

}
