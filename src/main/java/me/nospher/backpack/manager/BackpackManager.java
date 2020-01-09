package me.nospher.backpack.manager;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.item.Backpack;
import me.nospher.backpack.utils.Helper;
import me.nospher.backpack.utils.NBTTag;
import me.nospher.backpack.utils.serializer.InventorySerialize;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * @author oNospher
 */
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
            String inventory_name = NospherBackpack.getInstance().getConfig().getString(
                    "settings." +
                            "general." +
                            "inventory_name");
            Inventory inventory = Bukkit.createInventory(player, rows*9, Helper.colorize(inventory_name));
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
