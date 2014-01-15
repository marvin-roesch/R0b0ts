package de.mineformers.robots.entity;

import de.mineformers.robots.api.data.IModuleData;
import de.mineformers.robots.api.data.Robot;
import de.mineformers.robots.api.data.RobotChipset;
import de.mineformers.robots.api.data.RobotModule;
import de.mineformers.robots.api.registry.ChipsetRegistry;
import de.mineformers.robots.api.registry.ModuleRegistry;
import de.mineformers.robots.client.audio.SoundHandler;
import de.mineformers.robots.lib.Reference;
import de.mineformers.robots.lib.Strings;
import de.mineformers.robots.util.PrivateRobotHelper;
import de.mineformers.robots.util.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityOwnable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * R0b0ts
 * <p/>
 * EntityRobot
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityRobot extends EntityLiving implements EntityOwnable, IInventory {

    private IModuleData moduleData;
    private ItemStack[] inventory;

    public EntityRobot(World world) {
        super(world);
        inventory = new ItemStack[10];
        dataWatcher.addObject(13, 0);
        dataWatcher.addObject(14, "blank");
        dataWatcher.addObject(15, "blank");
        dataWatcher.addObject(16, 0F);
        dataWatcher.addObject(17, 0F);
        dataWatcher.addObject(18, 0F);
        dataWatcher.addObject(19, "");
    }

    public EntityRobot(World world, Vector3 boundInventory) {
        this(world);
        dataWatcher.updateObject(16, (float) boundInventory.x);
        dataWatcher.updateObject(17, (float) boundInventory.y);
        dataWatcher.updateObject(18, (float) boundInventory.z);
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public void onInventoryChanged() {
    }

    @Override
    public String getInvName() {
        return "inventory." + Reference.RESOURCE_PREFIX + Strings.ROBOT_NAME;
    }

    @Override
    public boolean isInvNameLocalized() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75F;
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
    protected boolean interact(EntityPlayer player) {
        if (player.isSneaking()) {
            if (!this.worldObj.isRemote) {
                if (player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName())) {
                    EntityItem item = new EntityItem(worldObj);
                    item.setEntityItemStack(PrivateRobotHelper.createItemStackFromRobot(this.getCustomNameTag().isEmpty() ? null : this.getCustomNameTag(), new Robot(this.getModule(), this.getChipset(), this.getArmorId(), moduleData)));
                    item.setPosition(posX, posY, posZ);
                    worldObj.spawnEntityInWorld(item);
                    this.setDead();
                } else {
                    player.addChatMessage("You're not the owner of this robot");
                }
            }
        }
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setString("Module", this.getModule().getIdentifier());
        tagCompound.setString("Chipset", this.getChipset().getIdentifier());
        tagCompound.setInteger("ArmorID", this.getArmorId());
        Vector3 boundInventory = this.getBoundInventory();
        tagCompound.setIntArray("BoundInventory", new int[]{(int) boundInventory.x, (int) boundInventory.y, (int) boundInventory.z});
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(compound);
                tagList.appendTag(compound);
            }
        }
        tagCompound.setTag("Items", tagList);

        NBTTagCompound moduleTag = new NBTTagCompound("ModuleData");
        if (moduleData != null)
            moduleData.writeToNBT(moduleTag);
        tagCompound.setCompoundTag("ModuleData", moduleTag);
        tagCompound.setString("Owner", this.getOwnerName());
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.setModule(ModuleRegistry.instance().getModule(tagCompound.getString("Module")));
        this.setChipset(ChipsetRegistry.instance().getChipset(tagCompound.getString("Chipset")));
        this.setArmorId(tagCompound.getInteger("ArmorID"));
        int[] coords = tagCompound.getIntArray("BoundInventory");
        this.setBoundInventory(new Vector3(coords[0], coords[1], coords[2]));
        NBTTagList tagList = tagCompound.getTagList("Items");
        inventory = new ItemStack[10];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound compound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = compound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length) {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        NBTTagCompound moduleTag = new NBTTagCompound("ModuleData");
        if (moduleData != null)
            moduleData.readFromNBT(moduleTag);
        tagCompound.setCompoundTag("ModuleData", moduleTag);
        this.setOwner(tagCompound.getString("Owner"));
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    public void setBoundInventory(Vector3 boundInventory) {
        dataWatcher.updateObject(16, (float) boundInventory.x);
        dataWatcher.updateObject(17, (float) boundInventory.y);
        dataWatcher.updateObject(18, (float) boundInventory.z);
    }

    public Vector3 getBoundInventory() {
        return new Vector3(dataWatcher.getWatchableObjectFloat(16), dataWatcher.getWatchableObjectFloat(17), dataWatcher.getWatchableObjectFloat(18));
    }

    public int getArmorId() {
        return dataWatcher.getWatchableObjectInt(13);
    }

    public void setArmorId(int id) {
        dataWatcher.updateObject(13, id);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public int getTotalArmorValue() {
        int value = 0;
        if (getArmorId() > 0) {
            for (int i = 0; i < 4; i++) {
                value += ((ItemArmor) Item.itemsList[getArmorId()]).getArmorMaterial().getDamageReductionAmount(i);
            }
        }
        return value;
    }

    public RobotModule getModule() {
        this.moduleData = ModuleRegistry.instance().getModule(dataWatcher.getWatchableObjectString(14)).getData();
        return ModuleRegistry.instance().getModule(dataWatcher.getWatchableObjectString(14));
    }

    public void setModuleData(IModuleData moduleData) {
        this.moduleData = moduleData;
    }

    public void setModule(RobotModule module) {
        dataWatcher.updateObject(14, module.getIdentifier());
        this.tasks.addTask(1, this.getModule().getAI(this));
    }

    public RobotChipset getChipset() {
        return ChipsetRegistry.instance().getChipset(dataWatcher.getWatchableObjectString(15));
    }

    public void setChipset(RobotChipset chipset) {
        dataWatcher.updateObject(15, chipset.getIdentifier());
    }

    public void setOwner(String ownerName) {
        this.dataWatcher.updateObject(19, ownerName);
    }

    @Override
    public String getOwnerName() {
        return this.dataWatcher.getWatchableObjectString(19);
    }

    @Override
    public Entity getOwner() {
        return worldObj.getPlayerEntityByName(this.getOwnerName());
    }
}
