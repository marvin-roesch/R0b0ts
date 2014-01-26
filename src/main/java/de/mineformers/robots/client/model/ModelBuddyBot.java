package de.mineformers.robots.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * R0b0ts
 * <p/>
 * ModelBuddyBot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelBuddyBot extends ModelBiped
{

    ModelRenderer bipedEye;
    public boolean isSitting;

    public ModelBuddyBot()
    {
        super();
        this.bipedEye = new ModelRenderer(this, 0, 0);
        this.bipedEye.addBox(-1.0F, -6.0F, -4.5F, 2, 3, 1, 0);
        this.bipedEye.setRotationPoint(0.0F, 0, 0.0F);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            if (isSitting)
                GL11.glTranslatef(0, 0.325F, 0);
            GL11.glPushMatrix();
            GL11.glScalef(1.75F / f6, 1.75F / f6, 1.75F / f6);
            GL11.glTranslatef(0.0F, 13.75F * par7, 0.0F);
            this.bipedHead.render(par7);
            this.bipedEye.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.bipedBody.render(par7);
            this.bipedRightArm.render(par7);
            this.bipedLeftArm.render(par7);
            this.bipedRightLeg.render(par7);
            this.bipedLeftLeg.render(par7);
            this.bipedHeadwear.render(par7);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        } else
        {
            GL11.glPushMatrix();
            GL11.glScalef(2F, 2F, 2F);
            GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
            this.bipedHead.render(par7);
            GL11.glPopMatrix();
            this.bipedBody.render(par7);
            this.bipedRightArm.render(par7);
            this.bipedLeftArm.render(par7);
            this.bipedRightLeg.render(par7);
            this.bipedLeftLeg.render(par7);
            this.bipedHeadwear.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.bipedEye.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.bipedEye.rotateAngleX = par5 / (180F / (float) Math.PI);
        if (this.isSitting)
        {
            this.bipedRightArm.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float) Math.PI * 2.5F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2.5F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
        }
    }
}
