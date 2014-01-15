package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UIImage;
import net.minecraft.util.ResourceLocation;

/**
 * R0b0ts
 * <p/>
 * PagePicture
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PagePicture extends ManualPage {

    private UIImage image;
    private final ResourceLocation texture;

    public PagePicture(String heading, ResourceLocation texture) {
        super(heading);
        this.texture = texture;
        image = new UIImage(texture);
        this.addComponent(image, (166 - image.getWidth()) / 2, mc.fontRenderer.FONT_HEIGHT);
    }

}
