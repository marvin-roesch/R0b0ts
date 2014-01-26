package de.mineformers.robots.client.gui.component.decorative;

import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.util.RenderHelper;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
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
public class UICraftingSpace extends UIComponent
{

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/crafting_table.png");
    private ShapedRecipes recipe;
    private final UITooltip tooltip;

    public UICraftingSpace()
    {
        super(texture);
        this.tooltip = new UITooltip();
        this.width = 126;
        this.height = 64;
    }

    public void setRecipe(ShapedRecipes recipe)
    {
        this.recipe = recipe;
    }

    public ShapedRecipes getRecipe()
    {
        return recipe;
    }

    public void setStackAt(int x, int y, ItemStack stack)
    {
        int slot = x + y * 3;
        recipe.recipeItems[slot] = stack;
    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        this.drawRectangle(screenX, screenY, 24, 11, 126, 64);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) 240 / 1.0F, (float) 240 / 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        if (recipe != null)
        {
            net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
            for (int y = 0; y < recipe.recipeHeight; y++)
            {
                for (int x = 0; x < recipe.recipeWidth; x++)
                {
                    if (recipe.recipeItems[x + y * 3] != null)
                    {
                        GL11.glColor4f(1, 1, 1, 1);
                        int posX = screenX + 6 + 18 * x;
                        int posY = screenY + 6 + 18 * y;
                        if (this.isInsideRegion(mouseX, mouseY, posX, posY, posX + 18, posY + 18))
                        {
                            tooltip.reset();
                            tooltip.addLine(recipe.recipeItems[x + y * 3].getDisplayName());
                            tooltip.draw(mouseX, mouseY);
                        }
                        RenderHelper.renderItemIntoGUI(recipe.recipeItems[x + y * 3], posX, posY);
                    }
                }
            }
            if (recipe.getRecipeOutput() != null && recipe.getRecipeOutput().itemID != 0)
            {
                if (this.isInsideRegion(mouseX, mouseY, screenX + 100, screenY + 24, screenX + 100 + 18, screenY + 24 + 18))
                {
                    tooltip.reset();
                    tooltip.addLine(recipe.getRecipeOutput().getDisplayName());
                    tooltip.draw(mouseX, mouseY);
                }
                RenderHelper.renderItemIntoGUI(recipe.getRecipeOutput(), screenX + 100, screenY + 24);
                net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            }
        }
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY)
    {
        return isInsideRegion(mouseX, mouseY, screenX + 5, screenY + 5, screenX + 131, screenY + 59);
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }
}
