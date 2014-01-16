package de.mineformers.robots.client.gui.manual;

import de.mineformers.robots.client.gui.component.decorative.UIImage;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.util.ResourceLocation;
import org.w3c.dom.Element;

/**
 * R0b0ts
 * <p/>
 * PagePicture
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PagePicture extends PageText {

    private UIImage image;

    private void init(ResourceLocation texture) {
        int y = super.text.getHeight() + 2;
        image = new UIImage(texture);
        this.addComponent(image, (166 - image.getWidth()) / 2, y);
    }

    private void init(ResourceLocation texture, int width, int height) {
        int y = super.text.getHeight() + 2;
        image = new UIImage(texture, width, height);
        this.addComponent(image, (166 - image.getWidth()) / 2, y);
    }

    private void init(ResourceLocation texture, int width, int height, int uvWidth, int uvHeight) {
        int y = super.text.getHeight() + 2;
        image = new UIImage(texture, width, height, uvWidth, uvHeight);
        this.addComponent(image, (166 - image.getWidth()) / 2, y);
    }

    @Override
    public void loadFromXML(Element element) {
        super.loadFromXML(element);
        Element picture = (Element) element.getElementsByTagName("picture").item(0);
        ResourceLocation texture = ResourceHelper.getModResource(picture.getElementsByTagName("location").item(0).getTextContent());
        if (picture.getElementsByTagName("width") != null && picture.getElementsByTagName("height") != null) {
            int width = Integer.valueOf(picture.getElementsByTagName("width").item(0).getTextContent());
            int height = Integer.valueOf(picture.getElementsByTagName("height").item(0).getTextContent());
            if (picture.getElementsByTagName("uvWidth") != null && picture.getElementsByTagName("uvHeight") != null) {
                int uvWidth = Integer.valueOf(picture.getElementsByTagName("uvWidth").item(0).getTextContent());
                int uvHeight = Integer.valueOf(picture.getElementsByTagName("uvHeight").item(0).getTextContent());
                init(texture, width, height, uvWidth, uvHeight);
                return;
            }
            init(texture, width, height);
            return;
        }
        init(texture);
    }
}
