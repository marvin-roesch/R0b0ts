package de.mineformers.robots.client.gui.minecraft;

import de.mineformers.robots.client.gui.component.container.UIPanel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiScreenTT
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WidgetGuiScreen extends GuiScreen {

    private UIPanel panel;

    public WidgetGuiScreen(int width, int height, UIPanel panel) {
        this.mc = Minecraft.getMinecraft();
        this.width = width;
        this.height = height;
        this.panel = panel;
        panel.setSize(width, height);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.panel.initComponent();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        panel.mouseClick(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);
        panel.keyTyped(keyChar, keyCode);
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

    @Override
    public void drawScreen(int mouseX, int mouseY, float opacity) {
        this.drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - panel.getWidth()) / 2;
        int yStart = (height - panel.getHeight()) / 2;

        panel.setScreenPos(xStart, yStart);
        panel.drawBackground(mouseX, mouseY);
        panel.draw(mouseX, mouseY);
        panel.drawForeground(mouseX, mouseY);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

}
