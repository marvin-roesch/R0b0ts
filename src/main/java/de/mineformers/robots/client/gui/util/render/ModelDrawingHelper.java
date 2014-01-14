package de.mineformers.robots.client.gui.util.render;

import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.client.gui.util.RenderHelper;
import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.proxy.ClientProxy;
import de.mineformers.robots.tileentity.TileFactoryController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * GUISystem
 * <p/>
 * ModelDrawingHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelDrawingHelper implements IDrawingHelper {

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private TileFactoryController tile;
    private final RenderItem customRenderItem;
    private float rotation;
    private ModelRobot model;
    private ResourceLocation texture;
    private int canvasWidth, canvasHeight;
    private float scale;

    public ModelDrawingHelper(ModelRobot model, TileFactoryController tile, ResourceLocation texture, int canvasWidth, int canvasHeight, float scale) {
        this.model = model;
        this.texture = texture;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.scale = scale;
        this.tile = tile;
        customRenderItem = new RenderItem() {
            @Override
            public boolean shouldBob() {
                return false;
            }
        };

        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public void draw(int x, int y) {
        // Corners clockwise
        RenderHelper.drawRectangle(x, y, 39 + 46, 67, 5, 5, 1);
        RenderHelper.drawRectangle(x + canvasWidth - 5, y, 39 + 54, 67, 5, 5, 1);
        RenderHelper.drawRectangle(x + canvasWidth - 5, y + canvasHeight - 5, 39 + 54, 66 + 9, 5, 5, 1);
        RenderHelper.drawRectangle(x, y + canvasHeight - 5, 39 + 46, 66 + 9, 5, 5, 1);

        // Sides clockwise
        RenderHelper.drawRectangleStretched(x + 5, y, 39 + 52, 66 + 1, canvasWidth - 10, 5, 1, 5, 1);
        RenderHelper.drawRectangleStretched(x + canvasWidth - 5, y + 5, 39 + 54, 66 + 7, 5,
                canvasHeight - 10, 5, 1, 1);
        RenderHelper.drawRectangleStretched(x + 5, y + canvasHeight - 5, 39 + 52, 66 + 9, canvasWidth - 10,
                5, 1, 5, 1);
        RenderHelper.drawRectangleStretched(x, y + 5, 39 + 46, 66 + 7, 5, canvasHeight - 10, 5, 1, 1);

        // Canvas
        RenderHelper.drawRectangleStretched(x + 5, y + 5, 39 + 52, 66 + 7, canvasWidth - 10,
                canvasHeight - 10, 1, 1, 1);
        render(x + 25, y + 21, 30);
    }

    public void render(int par0, int par1, int par2) {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par0, (float) par1, 50.0F);
        GL11.glScalef((float) (-par2), (float) par2, (float) par2);
        GL11.glRotatef(180F, 0, 1, 0);
        rotation += 0.3F;
        GL11.glRotatef(rotation, 0, 1, 0);

        RenderHelper.bindTexture(ClientProxy.getRobotTexture(tile.getArmorId()));
        model.justRender(false);
        RenderHelper.bindTexture(ChipsetRegistry.instance().getChipset(tile.getSelectedChipset()).getHeadTexture());
        model.renderEye(false);
        if (tile.getStackInSlot(0) != null) {
            Tessellator tessellator = Tessellator.instance;
            GL11.glPushMatrix();

            GL11.glTranslatef(0, 0.35F, -0.1F);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glScalef(0.5F, 0.5F, 1F);
            Icon icon = ModItems.module.getIcon(tile.getStackInSlot(0), 0);
            float f4 = icon.getMinU();
            float f5 = icon.getMaxU();
            float f6 = icon.getMinV();
            float f7 = icon.getMaxV();
            float f9 = 0.5F;
            float f10 = 0.25F;
            float f12 = 0.0625F;
            float f11 = 0.021875F;
            ItemStack itemstack = tile.getStackInSlot(0);
            int j = itemstack.stackSize;
            byte b0 = customRenderItem.getMiniItemCount(itemstack);

            GL11.glTranslatef(-f9, -f10, -((f12 + f11) * (float) b0 / 2.0F));

            for (int k = 0; k < b0; ++k) {
                // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
                GL11.glTranslatef(0f, 0f, f12 + f11);

                if (itemstack.getItemSpriteNumber() == 0) {
                    RenderHelper.bindTexture(TextureMap.locationBlocksTexture);
                } else {
                    RenderHelper.bindTexture(TextureMap.locationItemsTexture);
                }

                GL11.glColor4f(1, 1, 1, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, icon.getIconWidth(), icon.getIconHeight(), f12);

                if (itemstack.hasEffect(0)) {
                    GL11.glDepthFunc(GL11.GL_EQUAL);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    RenderManager.instance.renderEngine.bindTexture(RES_ITEM_GLINT);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                    float f13 = 0.76F;
                    GL11.glColor4f(0.5F * f13, 0.25F * f13, 0.8F * f13, 1.0F);
                    GL11.glMatrixMode(GL11.GL_TEXTURE);
                    GL11.glPushMatrix();
                    float f14 = 0.125F;
                    GL11.glScalef(f14, f14, f14);
                    float f15 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                    GL11.glTranslatef(f15, 0.0F, 0.0F);
                    GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glScalef(f14, f14, f14);
                    f15 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                    GL11.glTranslatef(-f15, 0.0F, 0.0F);
                    GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
                    GL11.glPopMatrix();
                    GL11.glMatrixMode(GL11.GL_MODELVIEW);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDepthFunc(GL11.GL_LEQUAL);
                }
            }

            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
