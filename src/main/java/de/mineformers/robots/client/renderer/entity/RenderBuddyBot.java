package de.mineformers.robots.client.renderer.entity;

import de.mineformers.robots.client.model.ModelBuddyBot;
import de.mineformers.robots.lib.Reference;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * R0b0ts
 * <p/>
 * RenderBuddyBot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderBuddyBot extends RenderBiped {
    private static final ResourceLocation texture = new ResourceLocation(Reference.RESOURCE_PATH, "textures/entities/robot.png");

    public RenderBuddyBot() {
        super(new ModelBuddyBot(), 0.5F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
