package de.mineformers.robots.client.renderer;

import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * R0b0ts
 * <p/>
 * FactoryControllerRenderer
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TileFactoryControllerRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation robotTexture = ResourceHelper.getModResource("textures/entities/robot.png");
    private ModelRobot robot;

    public TileFactoryControllerRenderer() {
        robot = new ModelRobot();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks) {
        GL11.glPushMatrix();
        int i = 15728880;

        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
        GL11.glTranslated(x + 0.5F, y + 0.65F, z + 0.5f);
        switch(((TileFactoryController) tileentity).getOrientation()) {
            case WEST:
                GL11.glRotatef(90, 0, 1, 0);
                break;
            case EAST:
                GL11.glRotatef(-90, 0, 1, 0);
                break;
            case SOUTH:
                GL11.glRotatef(180, 0, 1, 0);
                break;
        }
        GL11.glTranslatef(0, 0, -0.47F);
        GL11.glScalef(0.3F, 0.3F, 0.3F);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(180, 0, 1, 0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor4f(255F, 255F, 255F, 255F);
        this.bindTexture(robotTexture);
        robot.justRender();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
