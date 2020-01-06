package me.nospher.backpack.inventories.listeners;

import me.nospher.backpack.manager.BackpackManager;
import me.nospher.backpack.utils.NBTTag;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 */
public class BackpackInventoryClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = player.getItemInHand();
        if(inventory != null && inventory.getName().startsWith("Mochila ")) {

            if (event.getClick() == ClickType.NUMBER_KEY || event.isShiftClick()) {
                event.setCancelled(true);
                return;
            }

            if (item.getType() == null
                    || item.getType() == Material.AIR
                    || !NBTTag.getNBTTag(item).hasKey("stacked")
                    || !NBTTag.getNBTTag(item).getString("stacked").equals(BackpackManager.getPrevention().get(player))) {
                event.setCancelled(true);
                player.setItemInHand(null);
                player.closeInventory();
            }
        }
    }
}
