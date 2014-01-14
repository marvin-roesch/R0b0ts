package de.mineformers.robots.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * R0b0ts
 * <p/>
 * ModelRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ModelRobot extends ModelBiped {

    ModelRenderer bipedEye;

    public ModelRobot() {
        this.bipedEye = new ModelRenderer(this, 0, 0);
        this.bipedEye.addBox(-1.0F, -6.0F, -4.5F, 2, 3, 1, 0);
        this.bipedEye.setRotationPoint(0.0F, 0, 0.0F);
    }

    public void justRender() {
        justRender(true);
    }

    public void justRender(boolean offset) {
        this.bipedBody.render(0.0625F);
        this.bipedRightArm.render(0.0625F);
        this.bipedLeftArm.render(0.0625F);
        this.bipedRightLeg.render(0.0625F);
        this.bipedLeftLeg.render(0.0625F);
        this.bipedHeadwear.render(0.0625F);
        if (offset) GL11.glTranslatef(0, 0, 0.1F);
        this.bipedHead.render(0.0625F);
    }

    public void renderEye(boolean offset) {
        if (offset) GL11.glTranslatef(0, 0, 0.1F);
        this.bipedEye.render(0.0625F);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.bipedEye.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.bipedEye.rotateAngleX = par5 / (180F / (float)Math.PI);
    }
}
