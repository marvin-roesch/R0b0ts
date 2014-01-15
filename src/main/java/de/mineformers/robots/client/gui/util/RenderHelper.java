package de.mineformers.robots.client.gui.util;

import cpw.mods.fml.client.FMLClientHandler;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.Color;

/**
 * GUISystem
 * <p/>
 * RenderHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderHelper {

    private final static RenderItem renderItem = new RenderItem();

    public static Minecraft getMC() {
        return FMLClientHandler.instance().getClient();
    }

    public static void bindTexture(ResourceLocation path) {
        FMLClientHandler.instance().getClient().getTextureManager()
                .bindTexture(path);
    }

    public static void bindTexture(String path) {
        bindTexture(ResourceHelper.getModResource(path));
    }

    public static int computeGuiScale() {
        Minecraft mc = Minecraft.getMinecraft();
        int scaleFactor = 1;

        int k = mc.gameSettings.guiScale;

        if (k == 0) {
            k = 1000;
        }

        while (scaleFactor < k && mc.displayWidth / (scaleFactor + 1) >= 320 && mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        return scaleFactor;
    }

    public static int getColorFromRGB(int r, int g, int b) {
        return (0xFF0000 & (r << 16)) | (0x00FF00 & (g << 8)) | (0x0000FF & b);
    }

    public static int getColorFromRGB(Color color) {
        return (0xFF0000 & (color.getRed() << 16)) | (0x00FF00 & (color.getGreen() << 8)) | (0x0000FF & color.getBlue());
    }

    public static Color getRGBFromColor(int color) {
        return new Color((0xFF0000 & color) >> 16, (0x00FF00 & color) >> 8, (0x0000FF & color));
    }

    public static int getStringWidth(String text) {
        return getMC().fontRenderer.getStringWidth(text);
    }

    public static void drawString(String text, int x, int y, int color,
                                  boolean drawShadow, int zLevel) {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glTranslatef(0, 0, zLevel);
        getMC().fontRenderer.drawString(text, x, y, color, drawShadow);
        GL11.glTranslatef(0, 0, -zLevel);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public static void drawSplitString(String text, int x, int y, int color,
                                       boolean drawShadow) {
        String[] splits = text.split("<br>");
        for (int i = 0; i < splits.length; i++) {
            getMC().fontRenderer.drawString(splits[i], x, y + i * (getMC().fontRenderer.FONT_HEIGHT + 1), color,
                    drawShadow);
        }
    }

    public static void drawSplitStringCentered(String text, int x, int y, int color,
                                               boolean drawShadow, int canvasWidth) {
        String[] splits = text.split("<br>");
        int longest = getMC().fontRenderer.getStringWidth(TextHelper
                .getLongestString(splits));
        for (int i = 0; i < splits.length; i++) {
            getMC().fontRenderer.drawString(
                    splits[i],
                    x
                            + ((canvasWidth - longest) / 2)
                            + ((longest - getMC().fontRenderer
                            .getStringWidth(splits[i])) / 2), y + i
                    * 10, color, drawShadow);
        }
    }

    public static void drawRectangle(Color color, int x, int y, int width, int height, int zLevel) {
        float colorMod = 1F / 255F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(colorMod * color.getRed(), colorMod * color.getGreen(), colorMod * color.getBlue(), colorMod * color.getAlpha());
        drawRectangle(x, y, 0, 0, width, height, zLevel);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void drawRectangle(int color, int x, int y, int width, int height, int zLevel) {
        drawRectangle(color, 1F, x, y, width, height, zLevel);
    }

    public static void drawRectangle(int color, float alpha, int x, int y, int width, int height, int zLevel) {
        Color rgb = RenderHelper.getRGBFromColor(color);
        rgb.setAlpha((int) (alpha * 255));
        drawRectangle(rgb, x, y, width, height, zLevel);
    }

    public static void drawRectangle(int x, int y, int u, int v, int width, int height, int zLevel) {
        drawRectangle(Global.getTexture(), x, y, u, v, width, height, zLevel);
    }

    public static void drawRectangle(ResourceLocation texture, int x, int y, float u,
                                     float v, int width, int height, int zLevel) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if ((u != 0 && u % 1 != 0))
            f = 1;
        if ((v != 0 && v % 1 != 0))
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double) (x), (double) (y + height),
                (double) zLevel, (double) ((float) (u) * f),
                (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width),
                (double) (y + height), (double) zLevel,
                (double) ((float) (u + width) * f),
                (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width), (double) (y),
                (double) zLevel, (double) ((float) (u + width) * f),
                (double) ((float) (v) * f1));
        tessellator.addVertexWithUV((double) (x), (double) (y),
                (double) zLevel, (double) ((float) (u) * f),
                (double) ((float) (v) * f1));
        tessellator.draw();
    }

    public static void drawRectangleStretched(int x, int y, float u, float v, int width,
                                              int height, float uOff, float vOff, int zLevel) {
        drawRectangleStretched(Global.getTexture(), x, y, u, v, width, height, uOff, vOff, zLevel);
    }

    public static void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                              int height, float uOff, float vOff, int zLevel) {
        drawRectangleStretched(texture, x, y, u, v, width,
                height, u + uOff, v + vOff, true, zLevel);
    }

    public static void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                              int height, float uMax, float vMax, boolean max, int zLevel) {
        if (max) {
            RenderHelper.bindTexture(texture);
            float f = 0.00390625F;
            float f1 = 0.00390625F;
            if ((u > 0 && u % 1 != 0) || uMax == 1 || uMax % 1 != 0)
                f = 1;
            if ((v > 0 && v % 1 != 0) || vMax == 1 || vMax % 1 != 0)
                f1 = 1;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x), (double) (y + height),
                    (double) zLevel, (double) u * f,
                    (double) vMax * f1);
            tessellator.addVertexWithUV((double) (x + width),
                    (double) (y + height), (double) zLevel,
                    (double) uMax * f,
                    (double) vMax * f1);
            tessellator.addVertexWithUV((double) (x + width), (double) (y),
                    (double) zLevel, (double) uMax * f,
                    (double) v * f1);
            tessellator.addVertexWithUV((double) (x), (double) (y),
                    (double) zLevel, (double) (u) * f,
                    (double) (v) * f1);
            tessellator.draw();
            GL11.glDisable(GL11.GL_BLEND);
        } else {
            drawRectangleStretched(texture, x, y, u, v, width, height, uMax, vMax, zLevel);
        }
    }

    public static void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int zLevel) {
        drawRectangleRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, (int) uvWidth, (int) uvHeight, zLevel);
    }

    public static void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int tileWidth, int tileHeight, int zLevel) {
        int numX = (int) Math.ceil((float) width / tileWidth);
        int numY = (int) Math.ceil((float) height / tileHeight);

        int scale = RenderHelper.computeGuiScale();

        /*GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, mc.displayHeight - (y + height) * scale, width * scale, height * scale);
*/
        for (int y2 = 0; y2 < numY; ++y2)
            for (int x2 = 0; x2 < numX; ++x2) {

                int w = (int) Math.ceil((width / (float) numX));
                int xOffset = w * x2;
                //int yOffset = (y2 + 1) * tileHeight;

                int h = (int) Math.ceil((height / (float) numY));
                int yOffset = h * y2;

                int newWidth = w;
                int newHeight = h;

                if ((y + height) - yOffset - h < y) {
                    int hOffset = y - ((y + height) - yOffset - h);

                    yOffset -= hOffset;
                    newHeight -= hOffset;
                }

                if (x + xOffset + w > x + width) {
                    int wOffset = x - ((x + width) - xOffset - w);

                    newWidth -= wOffset;
                }

                drawRectangleStretched(texture, x + xOffset, (y + height) - yOffset - h,
                        u, v, newWidth, newHeight,
                        uvWidth, uvHeight, zLevel);
            }
        //GL11.glDisable(GL11.GL_SCISSOR_TEST);
        //GL11.glPopAttrib();
    }

    public static void drawRectangleXRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int zLevel) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if ((u != 0 && u % 1 != 0))
            f = 1;
        if ((v != 0 && v % 1 != 0))
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;

        boolean flipX = width < 0;
        if (flipX) width *= -1;

        int numX = (int) Math.ceil((float) width / uvWidth);

        int scale = RenderHelper.computeGuiScale();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, getMC().displayHeight - (y + height) * scale, width * scale, height * scale);

        for (int x2 = 0; x2 < numX; ++x2) {
            float xOffset = x2 * uvWidth;
            if (flipX)
                xOffset = width - (x2 + 1) * uvWidth;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x + xOffset), (double) (y + height),
                    (double) zLevel, (double) ((float) (u) * f),
                    (double) (v + uvHeight) * f1);
            tessellator.addVertexWithUV((double) (x + uvWidth + xOffset),
                    (double) (y + height), (double) zLevel,
                    (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + uvWidth + xOffset), (double) (y),
                    (double) zLevel, (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v) * f1));
            tessellator.addVertexWithUV((double) (x + xOffset), (double) (y),
                    (double) zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v) * f1));
            tessellator.draw();
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void drawRectangleYRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int zLevel) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if ((u != 0 && u % 1 != 0))
            f = 1;
        if ((v != 0 && v % 1 != 0))
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;

        boolean flipY = height < 0;
        if (flipY) height *= -1;

        int numY = (int) Math.ceil((float) height / uvHeight);

        int scale = RenderHelper.computeGuiScale();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, getMC().displayHeight - (y + height) * scale, width * scale, height * scale);

        for (int y2 = 0; y2 < numY; ++y2) {
            float yOffset = y2 * uvHeight;
            if (flipY)
                yOffset = height - (y2 + 1) * uvHeight;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x), (double) (y + uvHeight + yOffset),
                    (double) zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + width),
                    (double) (y + uvHeight + yOffset), (double) zLevel,
                    (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + width), (double) (y + yOffset),
                    (double) zLevel, (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v) * f1));
            tessellator.addVertexWithUV((double) (x), (double) (y + yOffset),
                    (double) zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v) * f1));
            tessellator.draw();
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void renderItemIntoGUI(ItemStack stack, int x, int y) {
        renderItem.renderItemIntoGUI(getMC().fontRenderer, getMC().getTextureManager(), stack, x, y);
    }

}
