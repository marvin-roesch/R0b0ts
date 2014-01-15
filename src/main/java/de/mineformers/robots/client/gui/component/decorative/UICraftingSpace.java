package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.RenderHelper;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * R0b0ts
 * <p/>
 * UICraftingSpace
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UICraftingSpace extends UIComponent {

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/crafting_table.png");
    private ShapedRecipes recipe;

    public UICraftingSpace() {
        super(texture);
        this.width = 126;
        this.height = 64;
    }

    public void setRecipe(ShapedRecipes recipe) {
        this.recipe = recipe;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        this.drawRectangle(screenX, screenY, 24, 11, 126, 64);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) 240 / 1.0F, (float) 240 / 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        if (recipe != null) {
            net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
            for (int x = 0; x < recipe.recipeWidth; x++) {
                for (int y = 0; y < recipe.recipeHeight; y++) {
                    if (recipe.recipeItems[x + y] != null) {
                        GL11.glColor4f(1, 1, 1, 1);
                        RenderHelper.renderItemIntoGUI(recipe.recipeItems[x + y], screenX + 6 + 18 * x, screenY + 6 + 18 * y);
                    }
                }
            }
            RenderHelper.renderItemIntoGUI(recipe.getRecipeOutput(), screenX + 100, screenY + 24);
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        }
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }
}
