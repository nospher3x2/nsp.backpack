package me.nospher.backpack.item;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.utils.Helper;
import me.nospher.backpack.utils.NBTTag;
import me.nospher.backpack.utils.RandomString;
import me.nospher.backpack.utils.serializer.InventorySerialize;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author oNospher
 */
public class Backpack {

    private ItemStack item;

    public Backpack(Material material) {
        ItemStack item1 = new ItemStack(material);
        ItemMeta itemMeta = item1.getItemMeta();

        List<String> backpackLore = NospherBackpack.getInstance().getConfig().getStringList(
                "" +
                        "settings." +
                        "backpack." +
                        "lore");

        backpackLore.replaceAll(Helper::colorize);

        String backpackName = Helper.colorize(NospherBackpack.getInstance().getConfig().getString(
                "" +
                        "settings." +
                        "backpack." +
                        "name"));

        itemMeta.setDisplayName(backpackName);
        itemMeta.setLore(backpackLore);

        item1.setItemMeta(itemMeta);

        NBTTagCompound compound = NBTTag.getNBTTag(item1);
        compound.setBoolean("isBackpack", true);
        compound.setString("stacked", RandomString.randomString());
        item1 = NBTTag.setNBTTag(item1, compound);
        item = item1;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setRows(Integer row) {
        NBTTagCompound compound = NBTTag.getNBTTag(item);
        compound.setInt("rows", row);
        item = NBTTag.setNBTTag(item, compound);
    }

    public void setInventory(Inventory inventory) {
        NBTTagCompound compound = NBTTag.getNBTTag(item);
        compound.setString("inventory", InventorySerialize.toJson(inventory).toString());
        item = NBTTag.setNBTTag(item, compound);
    }

}
