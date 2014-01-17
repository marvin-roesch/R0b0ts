package de.mineformers.robots.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import de.mineformers.robots.client.audio.SoundHandler;
import de.mineformers.robots.client.gui.WindowIngameManual;
import de.mineformers.robots.client.gui.minecraft.WidgetGuiScreen;
import de.mineformers.robots.item.ModItems;
import de.mineformers.robots.util.LangHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * R0b0ts
 * <p/>
 * EntityBuddyBot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityBuddyBot extends EntityTameable {

    public EntityBuddyBot(World world) {
        super(world);
        this.setSize(0.6F, 2F);
        this.setTamed(true);
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 5.0F, 5.0F));
        this.tasks.addTask(3, new EntityAIWander(this, 0.2D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean isChild() {
        return true;
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (!player.isSneaking()) {
            if (this.isTamed()) {
                if (player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && this.worldObj.isRemote) {
                    FMLCommonHandler.instance().showGuiScreen(new WidgetGuiScreen(200, 200, new WindowIngameManual(this)));
                }
            }
        } else {
            if (!this.worldObj.isRemote) {
                if (player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName())) {
                    EntityItem item = new EntityItem(worldObj);
                    item.setEntityItemStack(new ItemStack(ModItems.buddy));
                    item.setPosition(posX, posY, posZ);
                    worldObj.spawnEntityInWorld(item);
                    this.setDead();
                } else {
                    player.addChatMessage(LangHelper.translate("chat", "notMaster"));
                }
            }
        }
        return true;
    }

    @Override
    protected String getLivingSound() {
        return SoundHandler.ROBOT_BLIP;
    }

    @Override
    protected String getHurtSound() {
        return SoundHandler.ROBOT_BLIP;
    }

    @Override
    protected String getDeathSound() {
        return SoundHandler.ROBOT_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        return null;
    }

    @Override
    public Entity getOwner() {
        return this.func_130012_q();
    }
}
