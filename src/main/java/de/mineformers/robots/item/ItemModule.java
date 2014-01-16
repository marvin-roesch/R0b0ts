package de.mineformers.robots.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.api.util.RobotHelper;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

import java.util.HashMap;
import java.util.List;

/**
 * R0b0ts
 * <p/>
 * ItemModule
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemModule extends ItemBase {

    private HashMap<String, Icon> icons;

    public ItemModule(int id) {
        super(id, Strings.MODULE_BASE_NAME);
        icons = new HashMap<String, Icon>();
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        if (!GuiScreen.isShiftKeyDown())
            list.add("Press shift for more information");
        else {
            RobotModule module = RobotHelper.getModuleFromItemStack(stack);
            list.add("\247c" + LangHelper.translate("tooltip", "moduleType") + ": \247r" + module.getLocalizedName());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list) {
        for (RobotModule module : ModuleRegistry.instance().getModules()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("ModuleName", module.getIdentifier());
            NBTTagCompound moduleDataTag = new NBTTagCompound();
            IModuleData data = module.getData();
            if (data != null)
                data.writeToNBT(moduleDataTag);
            tag.setCompoundTag("ModuleData", moduleDataTag);
            ItemStack is = new ItemStack(this, 1, 0);
            is.setTagCompound(tag);
            list.add(is);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(ItemStack stack, int renderPass) {
        RobotModule module = RobotHelper.getModuleFromItemStack(stack);
        return icons.get(module.getIdentifier());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 1;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "modules/blank");
        for (RobotModule module : ModuleRegistry.instance().getModules()) {
            icons.put(module.getIdentifier(), iconRegister.registerIcon(module.getIconPath()));
        }
    }
}
