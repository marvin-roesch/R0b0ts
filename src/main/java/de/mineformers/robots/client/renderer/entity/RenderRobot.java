package de.mineformers.robots.client.renderer.entity;

import de.mineformers.robots.client.gui.util.RenderHelper;
import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.entity.EntityRobot;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.proxy.ClientProxy;
import de.mineformers.robots.util.PrivateRobotHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * R0b0ts
 * <p/>
 * RenderRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderRobot extends RenderLiving
{

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private final RenderItem customRenderItem;

    public RenderRobot()
    {
        super(new ModelRobot(), 0.5F);
        customRenderItem = new RenderItem()
        {
            @Override
            public boolean shouldBob()
            {
                return false;
            }
        };

        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.renderModel(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
        EntityRobot robot = ((EntityRobot) par1EntityLivingBase);
        RenderHelper.bindTexture(robot.getChipset().getHeadTexture());
        ((ModelRobot) mainModel).renderEye(false);
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();

        GL11.glTranslatef(0, 0.35F, -0.1F);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glScalef(0.5F, 0.5F, 1F);
        ItemStack itemstack = PrivateRobotHelper.createModuleStack(robot.getModule());
        Icon icon = ModItems.module.getIcon(itemstack, 0);
        float f4 = icon.getMinU();
        float f5 = icon.getMaxU();
        float f6 = icon.getMinV();
        float f7 = icon.getMaxV();
        float f9 = 0.5F;
        float f10 = 0.25F;
        float f12 = 0.0625F;
        float f11 = 0.021875F;

        int j = itemstack.stackSize;
        byte b0 = customRenderItem.getMiniItemCount(itemstack);

        GL11.glTranslatef(-f9, -f10, -((f12 + f11) * (float) b0 / 2.0F));

        for (int k = 0; k < b0; ++k)
        {
            // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
            GL11.glTranslatef(0f, 0f, f12 + f11);

            if (itemstack.getItemSpriteNumber() == 0)
            {
                RenderHelper.bindTexture(TextureMap.locationBlocksTexture);
            } else
            {
                RenderHelper.bindTexture(TextureMap.locationItemsTexture);
            }

            GL11.glColor4f(1, 1, 1, 1.0F);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef(-1, 0, 0.05F);
            ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, icon.getIconWidth(), icon.getIconHeight(), f12);

            if (itemstack.hasEffect(0))
            {
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

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return ClientProxy.getRobotTexture(((EntityRobot) entity).getArmorId());
    }
}
