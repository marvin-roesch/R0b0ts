package de.mineformers.robots.client.renderer.entity;

import de.mineformers.robots.client.model.ModelBuddyBot;
import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.lib.Reference;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * R0b0ts
 * <p/>
 * RenderBuddyBot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderBuddyBot extends RenderBiped
{
    private static final ResourceLocation texture = new ResourceLocation(Reference.RESOURCE_PATH, "textures/entities/buddy.png");

    public RenderBuddyBot()
    {
        super(new ModelBuddyBot(), 0.5F, 1.0F);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        ((ModelBuddyBot) this.mainModel).isSitting = ((EntityBuddyBot) par1EntityLiving).isSitting();
        super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3)
    {
        ((ModelBuddyBot) this.mainModel).isSitting = ((EntityBuddyBot) par1EntityLiving).isSitting();
        return super.func_130006_a(par1EntityLiving, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
