package de.mineformers.robots.client.gui;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.network.PacketDispatcher;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.decorative.UICanvas;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.component.interaction.UIButton;
import de.mineformers.robots.client.gui.component.interaction.UIProgressBar;
import de.mineformers.robots.client.gui.component.inventory.UIInfoTab;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.util.Orientation;
import de.mineformers.robots.client.gui.util.render.ModelDrawingHelper;
import de.mineformers.robots.client.gui.util.render.ResourceDrawingHelper;
import de.mineformers.robots.client.model.ModelRobot;
import de.mineformers.robots.network.packet.PacketStartFactory;
import de.mineformers.robots.tileentity.TileFactoryController;
import de.mineformers.robots.util.LangHelper;
import de.mineformers.robots.util.ResourceHelper;
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
public class WindowRobotFactory extends UIWindow
{

    private TileFactoryController tile;
    private UIButton btnStart;
    private UILabel currentModule;
    private UILabel currentChipset;
    private UILabel currentArmor;
    private UIProgressBar progress;
    private UIProgressBar energy;

    private UILabel currentRate;
    private UILabel energyStored;

    public WindowRobotFactory(TileFactoryController tile)
    {
        super();
        super.initComponent();
        this.tile = tile;

        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        UICanvas canvas = new UICanvas(new ModelDrawingHelper(new ModelRobot(), tile, ResourceHelper.getModResource("textures/entities/robot.png"), 50, 72, 0.35F), 50, 72);
        energy = new UIProgressBar(Orientation.VERTICAL_BOTTOM, 14, 42, 66, 134);
        energy.setMaxValue(400000);
        energy.setValue(tile.getEnergyLevel());
        currentModule = new UILabel(ModuleRegistry.instance().getModule("blank").getLocalizedName());
        currentChipset = new UILabel(ChipsetRegistry.instance().getChipset("blank").getLocalizedName());
        currentArmor = new UILabel(ModuleRegistry.instance().getModule("blank").getLocalizedName());

        btnStart = new UIButton(68, 18, LangHelper.translate("gui", "button.start"));
        btnStart.addListener(this);
        btnStart.setEnabled(false);

        this.progress = new UIProgressBar(Orientation.HORIZONTAL_LEFT, 56, 16, 85, 81);
        progress.setMaxValue(400);

        layout.addComponent(new UILabel(EnumChatFormatting.UNDERLINE + LangHelper.translate("gui", "label.module") + ":"), 72 + 21, 12);
        layout.addComponent(currentModule, 72 + 21, 14 + mc.fontRenderer.FONT_HEIGHT);
        layout.addComponent(new UILabel(EnumChatFormatting.UNDERLINE + LangHelper.translate("gui", "label.chipset") + ":"), 72 + 21, 16 + mc.fontRenderer.FONT_HEIGHT * 2);
        layout.addComponent(currentChipset, 72 + 21, 18 + mc.fontRenderer.FONT_HEIGHT * 3);
        layout.addComponent(new UILabel(EnumChatFormatting.UNDERLINE + LangHelper.translate("gui", "label.armor") + ":"), 72 + 21, 20 + mc.fontRenderer.FONT_HEIGHT * 4);
        layout.addComponent(currentArmor, 72 + 21, 22 + mc.fontRenderer.FONT_HEIGHT * 5);
        layout.addComponent(energy, 1, 12);
        layout.addComponent(canvas, 20 + 21, 12);
        layout.addComponent(btnStart, 21, 89);
        layout.addComponent(progress, 99, 90);
        UIAbsoluteLayout infoLayout = new UIAbsoluteLayout();
        UILabel label = new UILabel(LangHelper.translate("info.robots:robotFactory"));
        label.setColor(0xe0e0e0);
        infoLayout.addComponent(label);
        UIInfoTab info = new UIInfoTab(infoLayout, new ResourceDrawingHelper(ResourceHelper.getModResource("textures/gui/info.png"), 16, 16, 0, 0, 16, 16), Orientation.HORIZONTAL_LEFT, LangHelper.translate("gui.robots:label.information"));
        this.addInfoTab(info);

        UIAbsoluteLayout energyLayout = new UIAbsoluteLayout();
        UILabel lblPowerUsage = new UILabel(LangHelper.translate("info.cofh.energyConsume") + ":");
        lblPowerUsage.setDrawShadow(true);
        lblPowerUsage.setColor(0xAAAFB8);
        currentRate = new UILabel(tile.getCurrentRate() + " RF/t");
        currentRate.setColor(0x000000);

        UILabel lblMaxEnergy = new UILabel(LangHelper.translate("info.cofh.maxEnergyPerTick") + ":");
        lblMaxEnergy.setDrawShadow(true);
        lblMaxEnergy.setColor(0xAAAFB8);
        UILabel maxEnergy = new UILabel("500 RF/t");
        maxEnergy.setColor(0x000000);

        UILabel lblEnergyStored = new UILabel(LangHelper.translate("info.cofh.energyStored") + ":");
        lblEnergyStored.setDrawShadow(true);
        lblEnergyStored.setColor(0xAAAFB8);
        energyStored = new UILabel(tile.getEnergyLevel() + " RF");
        energyStored.setColor(0x000000);

        energyLayout.addComponent(lblPowerUsage);
        energyLayout.addComponent(currentRate, 5, mc.fontRenderer.FONT_HEIGHT + 2);
        energyLayout.addComponent(lblMaxEnergy, 0, (mc.fontRenderer.FONT_HEIGHT + 2) * 2);
        energyLayout.addComponent(maxEnergy, 5, (mc.fontRenderer.FONT_HEIGHT + 2) * 3);
        energyLayout.addComponent(lblEnergyStored, 0, (mc.fontRenderer.FONT_HEIGHT + 2) * 4);
        energyLayout.addComponent(energyStored, 5, (mc.fontRenderer.FONT_HEIGHT + 2) * 5);
        UIInfoTab tab = new UIInfoTab(energyLayout, new ResourceDrawingHelper(ResourceHelper.getModResource("textures/gui/info.png"), 16, 16, 16, 0, 16, 16), Orientation.HORIZONTAL_RIGHT, LangHelper.translate("info.cofh.energy"));
        tab.setColor(0x085CA1);
        this.addInfoTab(tab);
        this.setLayout(layout);
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event)
    {
        if (btnStart.isEnabled())
            PacketDispatcher.sendPacketToServer(new PacketStartFactory(tile.xCoord, tile.yCoord, tile.zCoord).makePacket());
    }

    public void setCurrentArmor(int id)
    {
        if (currentArmor != null)
        {
            if (id == -1)
                this.currentArmor.setText(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("module." + ModuleRegistry.instance().getModule("blank").getUnlocalizedName() + ".name"));
            else
            {
                if (tile.getStackInSlot(2) != null)
                    this.currentArmor.setText(tile.getStackInSlot(2).getItem().getStatName());
                else
                    this.currentArmor.setText(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("module." + ModuleRegistry.instance().getModule("blank").getUnlocalizedName() + ".name"));
            }
        }
    }

    public void setButtonState(boolean enabled)
    {
        btnStart.setEnabled(enabled);
    }

    public void setProgress(int progress)
    {
        this.progress.setValue(progress);
    }

    public void setCurrentChipset(String label)
    {
        if (currentChipset != null)
        {
            if (label.equals(ChipsetRegistry.instance().getChipset("blank").getLocalizedName()))
                this.currentChipset.setText(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("chipset." + ChipsetRegistry.instance().getChipset("blank").getUnlocalizedName() + ".name"));
            else
                this.currentChipset.setText(label);
        }
    }

    public void setCurrentModule(String label)
    {
        if (currentModule != null)
        {
            if (label.equals(ModuleRegistry.instance().getModule("blank").getLocalizedName()))
                this.currentModule.setText(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("module." + ModuleRegistry.instance().getModule("blank").getUnlocalizedName() + ".name"));
            else
                this.currentModule.setText(label);
        }
    }

    public void setEnergyLevel(int amount)
    {
        this.energy.setValue(amount);
        this.energy.setTooltip(amount + " / 400000 RF");
        energyStored.setText(amount + " RF");
    }

    public void setCurrentRate(int rate)
    {
        this.currentRate.setText(rate + " RF/t");
    }
}
