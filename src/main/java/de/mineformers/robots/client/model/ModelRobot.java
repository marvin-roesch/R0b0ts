package de.mineformers.robots.client.model;

import net.minecraft.client.model.ModelBiped;
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

    public void justRender() {
        this.bipedBody.render(0.0625F);
        this.bipedRightArm.render(0.0625F);
        this.bipedLeftArm.render(0.0625F);
        this.bipedRightLeg.render(0.0625F);
        this.bipedLeftLeg.render(0.0625F);
        this.bipedHeadwear.render(0.0625F);
        GL11.glTranslatef(0, 0, 0.1F);
        this.bipedHead.render(0.0625F);
    }

}
