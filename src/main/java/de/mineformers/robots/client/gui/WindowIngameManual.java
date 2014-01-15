package de.mineformers.robots.client.gui;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.network.PacketDispatcher;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.interaction.UIButton;
import de.mineformers.robots.client.gui.component.interaction.UINavigationButton;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.manual.ManualPage;
import de.mineformers.robots.client.gui.manual.PageCrafting;
import de.mineformers.robots.client.gui.manual.PageWelcome;
import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.network.packet.PacketBuddyBotSit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.LinkedList;

/**
 * R0b0ts
 * <p/>
 * WindowIngameManual
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WindowIngameManual extends UIWindow {

    private LinkedList<ManualPage> pages;
    private UINavigationButton btnPrev, btnNext;
    private UIButton btnSit;
    private UIPanel canvas;
    private EntityBuddyBot bot;
    private int currentPage;

    public WindowIngameManual(EntityBuddyBot bot) {
        super();
        this.bot = bot;
        pages = new LinkedList<ManualPage>();
        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        btnSit = new UIButton(55, 20, bot.isSitting() ? "Stand" : "Sit");
        btnSit.setAction("sit");
        btnSit.addListener(this);
        btnPrev = new UINavigationButton(UINavigationButton.TYPE_PREV);
        btnPrev.setAction("prev");
        btnPrev.addListener(this);
        btnNext = new UINavigationButton(UINavigationButton.TYPE_NEXT);
        btnNext.setAction("next");
        btnNext.addListener(this);
        canvas = new UIPanel();

        layout.addComponent(canvas, 15, 12);
        layout.addComponent(btnSit, 68, 165);
        layout.addComponent(btnPrev, 0, 83);
        layout.addComponent(btnNext, 180, 83);

        initPages();
        btnPrev.setEnabled(false);
        btnNext.setEnabled(pages.size() > 1);
        canvas.setLayout(pages.get(0));
        this.setLayout(layout);
    }

    private void initPages() {
        pages.add(new PageWelcome());
        pages.add(new PageCrafting("How to craft x", "An item", new ItemStack(Block.cobblestone), new ItemStack[]{new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone),
                new ItemStack(Block.stone), null, new ItemStack(Block.stone),
                new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone)}));
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event) {
        if (event.getComponent().getAction().equals(btnSit.getAction())) {
            bot.setSitting(!bot.isSitting());
            PacketDispatcher.sendPacketToServer(new PacketBuddyBotSit(bot.entityId).makePacket());
            btnSit.setText(bot.isSitting() ? "Stand" : "Sit");
        }

        if (event.getComponent().getAction().equals(btnNext.getAction())) {
            if (currentPage < pages.size() - 1)
                setCurrentPage(currentPage + 1);
        }

        if (event.getComponent().getAction().equals(btnPrev.getAction())) {
            if (currentPage > 0)
                setCurrentPage(currentPage - 1);
        }
    }

    public void setCurrentPage(int newPage) {
        this.currentPage = newPage;
        canvas.setLayout(pages.get(newPage));
        btnPrev.setEnabled(newPage > 0);
        btnNext.setEnabled(newPage < (pages.size() - 1));
    }
}
