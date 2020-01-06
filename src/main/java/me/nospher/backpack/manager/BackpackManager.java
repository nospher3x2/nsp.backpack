package me.nospher.backpack.manager;

import me.nospher.backpack.item.Backpack;
import me.nospher.backpack.utils.NBTTag;
import me.nospher.backpack.utils.serializer.InventorySerialize;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class BackpackManager {

    private static HashMap<Player, Boolean> backpack_open = new HashMap<>();
    private static HashMap<Player, String> prevention = new HashMap<>();

    public static Inventory getInventory(Player player) {
        ItemStack item = player.getItemInHand();
        NBTTagCompound compound = NBTTag.getNBTTag(item);
        if(compound.hasKey("isBackpack") && compound.hasKey("inventory")) {
            String object1 = compound.getString("inventory");
            return InventorySerialize.toInventory(object1);
        } else {
            int rows = compound.getInt("rows");
            String stacked = compound.getString("stacked");
            Inventory inventory = Bukkit.createInventory(player, rows*9, "Mochila");
            Backpack backpack = new Backpack(item.getType());
            backpack.setRows(rows);
            backpack.setInventory(inventory);
            player.setItemInHand(backpack.getItem());
            return inventory;
        }
    }

    public static HashMap<Player, String> getPrevention() {
        return prevention;
    }

    public static Boolean isBackpackOpen(Player player) {
        return backpack_open.getOrDefault(player, false);
    }

    public static void setBackpackOpen(Player player, Boolean value) {
        backpack_open.put(player, value);
    }
}
