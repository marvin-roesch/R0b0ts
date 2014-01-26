package de.mineformers.robots.client.gui;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.ReflectionHelper;
import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.decorative.UICraftingSpace;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.interaction.UIButton;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.util.MouseButton;
import de.mineformers.robots.module.data.CraftingData;
import de.mineformers.robots.network.packet.PacketSetRecipe;
import de.mineformers.robots.util.LangHelper;
import de.mineformers.robots.util.RecipeHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 * R0b0ts
 * <p/>
 * WindowCraftingRecipe
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WindowCraftingRecipe extends UIWindow
{

    private UIButton btnSet;
    private UICraftingSpace craftingSpace;

    public WindowCraftingRecipe()
    {
        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        craftingSpace = new UICraftingSpace();
        craftingSpace.addListener(this);
        craftingSpace.setAction("crafting");
        CraftingData data = (CraftingData) RobotHelper.getModuleFromItemStack(mc.thePlayer.getHeldItem()).getData();
        if (mc.thePlayer.getHeldItem().getTagCompound().getCompoundTag("ModuleData").hasKey("Result"))
            data.readFromNBT(mc.thePlayer.getHeldItem().getTagCompound().getCompoundTag("ModuleData"));
        btnSet = new UIButton(55, 20, LangHelper.translate("gui", "button.set"));
        btnSet.setAction("set");
        btnSet.addListener(this);
        btnSet.setEnabled(data.getResult() != null);
        if (data.getResult() == null)
            data.setResult(new ItemStack(0, 0, 0));
        craftingSpace.setRecipe(new ShapedRecipes(3, 3, data.getInput(), data.getResult()));
        layout.addComponent(craftingSpace, 20, mc.fontRenderer.FONT_HEIGHT + 2);
        layout.addComponent(new UILabel(LangHelper.translate("gui", "label.craftingModule")), 25, 5);
        layout.addComponent(btnSet, 56, 80);
        this.setLayout(layout);
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event)
    {
        if (event.getComponent().getAction().equals("set"))
        {
            if (btnSet.isEnabled())
            {
                PacketDispatcher.sendPacketToServer(new PacketSetRecipe(craftingSpace.getRecipe()).makePacket());
            }
        }
        if (event.getComponent().getAction().equals("crafting"))
        {
            if (event.mouseButton == MouseButton.LEFT)
            {
                GuiContainer container = (GuiContainer) FMLClientHandler.instance().getClient().currentScreen;
                ItemStack dragged = ReflectionHelper.getPrivateValue(GuiContainer.class, container, "draggedStack", "field_85050_q");
                ItemStack slot = dragged != null ? dragged : this.mc.thePlayer.inventory.getItemStack();
                if (slot != null)
                {
                    int localX = event.mouseX - craftingSpace.getScreenX() - 5;
                    int localY = event.mouseY - craftingSpace.getScreenY() - 5;
                    if (isInsideRegion(localX, localY, 0, 0, 54, 54))
                    {
                        int x = (int) Math.ceil(localX / 18);
                        int y = (int) Math.ceil(localY / 18);
                        ItemStack stack = slot.copy();
                        stack.stackSize = 1;
                        craftingSpace.setStackAt(x, y, stack);
                        ItemStack result = RecipeHelper.findMatches(mc.theWorld, craftingSpace.getRecipe().recipeItems);
                        btnSet.setEnabled(result != null);
                        if (result == null)
                            result = new ItemStack(0, 0, 0);
                        craftingSpace.setRecipe(new ShapedRecipes(3, 3, craftingSpace.getRecipe().recipeItems, result));
                    }
                }
            } else if (event.mouseButton == MouseButton.RIGHT)
            {
                int localX = event.mouseX - craftingSpace.getScreenX() - 5;
                int localY = event.mouseY - craftingSpace.getScreenY() - 5;
                if (isInsideRegion(localX, localY, 0, 0, 54, 54))
                {
                    int x = (int) Math.ceil(localX / 18);
                    int y = (int) Math.ceil(localY / 18);
                    craftingSpace.setStackAt(x, y, null);
                    ItemStack result = RecipeHelper.findMatches(mc.theWorld, craftingSpace.getRecipe().recipeItems);
                    btnSet.setEnabled(result != null);
                    if (result == null)
                        result = new ItemStack(0, 0, 0);
                    craftingSpace.setRecipe(new ShapedRecipes(3, 3, craftingSpace.getRecipe().recipeItems, result));
                }
            }
        }
    }

}
