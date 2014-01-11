package de.mineformers.robots.client.render.entity;

import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.lib.Reference;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * R0b0ts
 * <p/>
 * RenderRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderRobot extends RendererLivingEntity {

    private static final ResourceLocation texture = new ResourceLocation(Reference.RESOURCE_PATH, "textures/entities/robot.png");

    public RenderRobot() {
        super(new ModelRobot(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
