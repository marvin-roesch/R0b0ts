package de.mineformers.robots.client.gui.component.list;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.component.UIComponent;
import de.mineformers.robots.client.gui.component.interaction.UIScrollBar;
import de.mineformers.robots.client.gui.event.KeyTypedEvent;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import de.mineformers.robots.client.gui.event.MouseScrollEvent;
import de.mineformers.robots.client.gui.system.Global;
import de.mineformers.robots.client.gui.util.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class UIList<T> extends UIComponent {
    protected List<T> items;

    protected int mouseX;
    protected int mouseY;

    public boolean drawBackground;

    protected UIScrollBar scrollBar;

    public UIList(int width, int height) {
        super(Global.getTexture());

        this.width = width;
        this.height = height;

        this.drawBackground = true;

        items = new ArrayList<T>();
        this.addListener(this);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        scrollBar = new UIScrollBar(screenX + width - 6 - 1, screenY + 1, 6, height - 2);
    }

    protected String getStringFromObject(Object obj) {
        return obj.toString();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void populate(List<T> list) {
        for (T item : list) {
            this.addItem(item);
        }
    }

    public int getItemHeight() {
        return 16;
    }

    public int getSpacing() {
        return 4;
    }

    public int getScrollY() {
        int scrollScale = ((items.size() - 1) * getItemHeight()) + ((items.size() - 1) * getSpacing());
        return (int) scrollBar.getScrollScaled(scrollScale);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return true;
    }

    @Subscribe
    public void onClick(MouseClickEvent event) {
        if (isInsideRegion(event.mouseX, event.mouseY, screenX, screenY + scrollBar.scrollY, screenX + width, screenY + scrollBar.scrollY + scrollBar.getBarHeight())) {
            scrollBar.postEvent(event);
        }
    }

    @Subscribe
    public void onMouseScroll(MouseScrollEvent event) {
        scrollBar.postEvent(event);
    }

    @Subscribe
    public void onKeyTyped(KeyTypedEvent event) {

    }

    @Override
    public void update(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        scrollBar.update(mouseX, mouseY);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {
        super.drawBackground(mouseX, mouseY);

        if (this.drawBackground) {
            GL11.glDisable(GL11.GL_TEXTURE_2D);

            Tessellator tessellator = Tessellator.instance;

            // Background
            GL11.glColor4f(0.35f, 0.35f, 0.35f, 1);
            this.drawRectangle(screenX, screenY, 0, 0, width, height);

            // Background Lighting Left
            GL11.glColor4f(0.1f, 0.1f, 0.1f, 1);
            this.drawRectangle(screenX, screenY, 0, 0, 1, height - 1);

            // Background Lighting Top
            GL11.glColor4f(0.1f, 0.1f, 0.1f, 1);
            this.drawRectangle(screenX, screenY, 0, 0, width - 1, 1);

            // Background Lighting Right
            GL11.glColor4f(1f, 1f, 1f, 1);
            this.drawRectangle(screenX + width - 1, screenY + 1, 0, 0, 1, height - 1);

            // Background Lighting Bottom
            GL11.glColor4f(1f, 1f, 1f, 1);
            this.drawRectangle(screenX + 1, screenY + height - 1, 0, 0, width - 1, 1);

            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        for (int i = 0; i < items.size(); ++i) {
            T item = items.get(i);

            int yOffset = 1 + (i * getItemHeight()) + (i * getSpacing()) - getScrollY();

            drawItemBackground(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());
        }

        scrollBar.drawBackground(mouseX, mouseY);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        super.drawForeground(mouseX, mouseY);

        for (int i = 0; i < items.size(); ++i) {
            T item = items.get(i);

            int yOffset = 1 + (i * getItemHeight()) + (i * getSpacing()) - getScrollY();

            drawItemForeground(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());
        }

        scrollBar.drawForeground(mouseX, mouseY);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        scrollBar.setScreenPos(screenX + width - 6 - 1, screenY);

        GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        int scale = RenderHelper.computeGuiScale();
        GL11.glScissor((screenX + 1) * scale, mc.displayHeight - (screenY + height - 1) * scale, (width - 2) * scale, (height - 2) * scale);


        drawItems(mouseX, mouseY);


        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopAttrib();

        scrollBar.draw(mouseX, mouseY);

        //this.scrollBar.draw(mouseX, mouseY);
    }

    protected void drawItems(int mouseX, int mouseY) {
        //GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.7f, 0.7f, 0.7f, 1);
        //this.drawRectangle(screenX, screenY, 0, 0, 2100, 3330);

        int spacing = getSpacing();
        int count = items.size();

        int scrollY = getScrollY();

        for (int i = 0; i < count; ++i) {
            T item = items.get(i);

            int yOffset = 1 + (i * getItemHeight()) + (i * spacing) - scrollY;

            drawItem(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());


        }

        //GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    protected void drawItemBackground(T item, int x, int y, int width, int height) {

    }

    protected void drawItemForeground(T item, int x, int y, int width, int height) {

    }

    protected void drawItem(T item, int x, int y, int width, int height) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
        this.drawRectangle(x, y, 0, 0, width, height);

        GL11.glColor4f(0.4f, 0.4f, 0.4f, 1);
        this.drawRectangle(x, y, 0, 0, width, 1);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        String str = getStringFromObject(item);
        this.drawString(str, x, y, 0, false);


    }
}
