package de.mineformers.robots.client.gui.manual;

import com.google.common.eventbus.Subscribe;
import de.mineformers.robots.client.gui.WindowIngameManual;
import de.mineformers.robots.client.gui.component.decorative.UILabel;
import de.mineformers.robots.client.gui.event.MouseClickEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedHashMap;

/**
 * R0b0ts
 * <p/>
 * PageWelcome
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PageWelcome extends PageText
{
    private LinkedHashMap<String, UILabel> linkLabels;
    private LinkedHashMap<String, Integer> links;

    public PageWelcome()
    {
        super();
        linkLabels = new LinkedHashMap<String, UILabel>();
        links = new LinkedHashMap<String, Integer>();
    }

    private void init(NodeList links)
    {
        for (int i = 0; i < links.getLength(); i++)
        {
            if (links.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element link = (Element) links.item(i);
                this.addLink(link.getElementsByTagName("action").item(0).getTextContent(),
                        link.getElementsByTagName("label").item(0).getTextContent(),
                        link.getElementsByTagName("jump").item(0).getTextContent());
            }
        }
    }

    private void addLink(String action, String label, String jump)
    {
        this.links.put(action, Integer.valueOf(jump));
        UILabel uiLabel = new UILabel(links.size() + ". " + label, true);
        uiLabel.setAction(action);
        uiLabel.addListener(this);
        this.addComponent(uiLabel, 0, text.getHeight() + linkLabels.size() * (this.mc.fontRenderer.FONT_HEIGHT + 1));
        this.linkLabels.put(action, uiLabel);
    }

    @Override
    public void loadFromXML(Element element)
    {
        super.loadFromXML(element);
        if (element.getElementsByTagName("links") != null)
            this.init(((Element) element.getElementsByTagName("links").item(0)).getElementsByTagName("link"));
    }

    @Subscribe
    public void onMouseClick(MouseClickEvent event)
    {
        WindowIngameManual manual = (WindowIngameManual) this.getParent().getParent().getParent();
        if (links.containsKey(event.getComponent().getAction()))
            manual.setCurrentPage(links.get(event.getComponent().getAction()));
    }

}
