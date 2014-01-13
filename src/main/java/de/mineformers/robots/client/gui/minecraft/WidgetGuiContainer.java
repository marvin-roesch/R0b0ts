package de.mineformers.robots.client.gui.minecraft;

import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.component.inventory.UISlot;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiContainerGUISystem
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WidgetGuiContainer extends GuiContainer {

    private UIPanel panel;
    protected String name;
    protected boolean autoDrawSlots;

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container) {
        this(width, height, panel, container, null);
    }

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container, IInventory inventory) {
        this(width, height, panel, container, inventory, false);
    }

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container, IInventory inventory, boolean autoDrawSlots) {
        super(container);
        this.panel = panel;
        this.panel.setSize(width, height);
        this.xSize = width;
        this.ySize = height;
        if (inventory != null)
            this.name = LangHelper.translate(inventory.getInvName());
        this.autoDrawSlots = autoDrawSlots;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.panel.initComponent();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        panel.mouseClick(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);
        panel.keyTyped(keyChar, keyCode);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        panel.setScreenPos(0, 0);
        //panel.drawForeground(mouseX, mouseY);
        if (name != null)
            de.mineformers.robots.client.gui.util.RenderHelper.drawString(name, 0 + 5, 0 + 5, 0x404040, false, 1);
        GL11.glColor4f(1, 1, 1, 1);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX,
                                                   int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

        panel.setScreenPos(xStart, yStart);

        panel.drawBackground(mouseX, mouseY);
        panel.draw(mouseX, mouseY);
        if (autoDrawSlots) {
            UISlot widget = new UISlot(18, 18);

            for (Object o : inventorySlots.inventorySlots) {
                if (o instanceof Slot) {
                    Slot slot = (Slot) o;
                    widget.setScreenPos(xStart + slot.xDisplayPosition - 1, yStart
                            + slot.yDisplayPosition - 1);
                    GL11.glColor4f(1, 1, 1, 1);
                    widget.draw(mouseX, mouseY);
                    if (!slot.getHasStack())
                        if (slot.getBackgroundIconTexture() != null && !slot.getBackgroundIconTexture().getResourcePath().contains("textures/atlas/items.png")) {
                            de.mineformers.robots.client.gui.util.RenderHelper.drawRectangleStretched(slot.getBackgroundIconTexture(), xStart + slot.xDisplayPosition, yStart
                                    + slot.yDisplayPosition, 0, 0, 16, 16, 1F, 1F, 1);
                        }
                    GL11.glColor4f(1, 1, 1, 1);
                }
            }
        }
        GL11.glColor4f(1, 1, 1, 1);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        int k = Mouse.getX() * i / this.mc.displayWidth;
        int l = j - Mouse.getY() * j / this.mc.displayHeight - 1;
        this.panel.update(k, l);
        int dWheel = Mouse.getDWheel() / 120;

        if (dWheel != 0) {
            panel.mouseScroll(-dWheel, k, l);
        }
    }

    public UIPanel getPanel() {
        return panel;
    }


}
