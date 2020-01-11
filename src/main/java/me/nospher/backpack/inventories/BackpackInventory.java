package me.nospher.backpack.inventories;

import me.nospher.backpack.events.BackpackOpenEvent;
import me.nospher.backpack.manager.BackpackManager;
import me.nospher.backpack.utils.NBTTag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 */
public class BackpackInventory {

    public static void open(Player player) {
        ItemStack item = player.getItemInHand();
        Inventory inventory = BackpackManager.getInventory(player);
        BackpackOpenEvent customEvent = new BackpackOpenEvent(player, inventory, player.getLocation());
        customEvent.run();
        if(customEvent.isCancelled()) return;
        player.openInventory(inventory);
        BackpackManager.setBackpackOpen(player, true);
        String stacked = NBTTag.getNBTTag(item).getString("stacked");
        BackpackManager.getPrevention().put(player, stacked);
    }
}
