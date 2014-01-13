package de.mineformers.robots.client.gui;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.decorative.UICanvas;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.interaction.UIButton;
import de.mineformers.robots.client.gui.component.interaction.UIProgressBar;
import de.mineformers.robots.client.gui.component.inventory.UIInfoTab;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.component.layout.UIFlowLayout;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.render.ModelDrawingHelper;
import de.mineformers.robots.client.gui.util.render.ResourceDrawingHelper;
import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.LangHelper;
import de.mineformers.robots.util.ResourceHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

/**
 * R0b0ts
 * <p/>
 * WindowRobotFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WindowRobotFactory extends UIWindow {

    private TileFactoryController tile;
    private UIButton btnStart;
    private UIButton btnClose;
    private UILabel currentModule;

    public WindowRobotFactory(TileFactoryController tile) {
        super.initComponent();
        this.tile = tile;

        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        UICanvas canvas = new UICanvas(new ModelDrawingHelper(new ModelRobot(), tile, ResourceHelper.getModResource("textures/entities/robot.png"), 50, 72, 0.35F), 50, 72);
        UIProgressBar energy = new UIProgressBar(Orientation.VERTICAL_BOTTOM, 14, 42, 66, 134);
        energy.setValue(50);
        currentModule = new UILabel(ModuleRegistry.instance().getModule("blank").getLocalizedName());

        btnStart = new UIButton(78, 15, LangHelper.translate("gui", "button.start"));
        btnStart.addListener(this);
        btnStart.setEnabled(false);
        btnStart.setAction("start");
        btnClose = new UIButton(78, 15, LangHelper.translate("gui", "button.close"));
        btnClose.addListener(this);
        btnClose.setAction("close");

        layout.addComponent(new UILabel(EnumChatFormatting.UNDERLINE + LangHelper.translate("gui", "label.module") + ":"), 88, 12);
        layout.addComponent(currentModule, 88, 14 + mc.fontRenderer.FONT_HEIGHT);
        layout.addComponent(energy, 0, 12);
        layout.addComponent(canvas, 36, 12);
        layout.addComponent(btnStart, 3, 95);
        layout.addComponent(btnClose, 85, 95);
        UIFlowLayout infoLayout = new UIFlowLayout(100, 50);
        UILabel label = new UILabel("Produces robots");
        label.setColor(0xe0e0e0);
        infoLayout.addComponent(label);
        UIInfoTab info = new UIInfoTab(infoLayout, new ResourceDrawingHelper(ResourceHelper.getModResource("textures/gui/info.png"), 16, 16, 0, 0, 16, 16), Orientation.HORIZONTAL_LEFT, LangHelper.translate("gui.robots:label.information"));
        this.addInfoTab(info);
        this.setLayout(layout);
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event) {
        if (event.getComponent().getAction().equals("start")) {

        } else if (event.getComponent().getAction().equals("close")) {
            mc.currentScreen = null;
            mc.setIngameFocus();
        }
    }

    public void setCurrentModule(String label) {
        if (currentModule != null) {
            if (label.equals(ModuleRegistry.instance().getModule("blank").getLocalizedName()))
                this.setCurrentModule(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("module." + ModuleRegistry.instance().getModule("blank").getUnlocalizedName() + ".name"));
            else
                this.currentModule.setText(label);
        }
    }
}
