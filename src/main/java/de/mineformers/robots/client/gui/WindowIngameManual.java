package de.mineformers.robots.client.gui;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.network.PacketDispatcher;
import de.mineformers.robots.client.gui.component.container.UIPanel;
import de.mineformers.robots.client.gui.component.container.UIWindow;
import de.mineformers.robots.client.gui.component.interaction.UIButton;
import de.mineformers.robots.client.gui.component.interaction.UINavigationButton;
import de.mineformers.robots.client.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.manual.*;
import de.mineformers.robots.entity.EntityBuddyBot;
import de.mineformers.robots.network.packet.PacketBuddyBotSit;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.item.crafting.ShapedRecipes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
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

    public static Document document;
    private static final HashMap<String, Class<? extends ManualPage>> pageClasses = new HashMap<String, Class<? extends ManualPage>>();
    public static final HashMap<String, ShapedRecipes> recipes = new HashMap<String, ShapedRecipes>();
    private LinkedList<ManualPage> pages;
    private UINavigationButton btnPrev, btnNext;
    private UIButton btnSit;
    private UIPanel canvas;
    private EntityBuddyBot bot;
    private int currentPage;

    static {
        pageClasses.put("welcome", PageWelcome.class);
        pageClasses.put("crafting", PageCrafting.class);
        pageClasses.put("text", PageText.class);
        pageClasses.put("picture", PagePicture.class);
    }

    public WindowIngameManual(EntityBuddyBot bot) {
        super();
        this.bot = bot;
        pages = new LinkedList<ManualPage>();
        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        btnSit = new UIButton(55, 20, bot.isSitting() ? LangHelper.translate("gui", "button.stand") : LangHelper.translate("gui", "button.sit"));
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
        try {
            NodeList nodes = document.getDocumentElement().getElementsByTagName("page");
            if (nodes != null)
                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodes.item(i);
                        Class<? extends ManualPage> clazz = pageClasses.get(element.getAttribute("type"));
                        ManualPage page = clazz.newInstance();
                        page.loadFromXML(element);
                        pages.add(page);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event) {
        if (event.getComponent().getAction().equals(btnSit.getAction())) {
            bot.setSitting(!bot.isSitting());
            PacketDispatcher.sendPacketToServer(new PacketBuddyBotSit(bot.entityId).makePacket());
            btnSit.setText(bot.isSitting() ? LangHelper.translate("gui", "button.stand") : LangHelper.translate("gui", "button.sit"));
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
