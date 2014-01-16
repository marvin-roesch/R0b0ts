package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GUISystem
 * <p/>
 * UIImage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIImage extends UIComponent {

    private int scale;
    private int uvWidth, uvHeight;

    public UIImage(ResourceLocation image, int width, int height) {
        super(image);
        this.width = width;
        this.height = height;
        try {
            BufferedImage temp = ImageIO.read(mc.getResourceManager().getResource(image).getInputStream());
            this.uvWidth = temp.getWidth();
            this.uvHeight = temp.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scale = 1;
    }

    public UIImage(ResourceLocation image, int width, int height, int uvWidth, int uvHeight) {
        super(image);
        this.width = width;
        this.height = height;
        this.uvWidth = uvWidth;
        this.uvHeight = uvHeight;
        scale = 1;
    }


    public UIImage(ResourceLocation image) {
        this(image, 1);
    }

    public UIImage(ResourceLocation image, int scale) {
        super(image);
        try {
            BufferedImage temp = ImageIO.read(mc.getResourceManager().getResource(image).getInputStream());
            this.width = temp.getWidth() * scale;
            this.height = temp.getHeight() * scale;
            this.uvWidth = temp.getWidth();
            this.uvHeight = temp.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scale = scale;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (!texture.getResourcePath().toLowerCase().startsWith("textures/items") && !texture.getResourcePath().toLowerCase().startsWith("textures/blocks"))
            this.drawRectangleStretched(screenX, screenY, 0, 0, width, height, uvWidth, uvHeight);
        else
            this.drawRectangleStretched(screenX, screenY, 0, 0, 16 * scale, 16 * scale, 256, 256);
    }
}
