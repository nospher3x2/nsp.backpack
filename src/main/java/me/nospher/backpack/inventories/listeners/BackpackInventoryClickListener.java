package me.nospher.backpack.inventories.listeners;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.manager.BackpackManager;
import me.nospher.backpack.utils.Helper;
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

import java.util.List;

/**
 * @author oNospher
 */
public class BackpackInventoryClickListener implements Listener {

    List<String> items_blacklist = NospherBackpack.getInstance().getConfig().getStringList(
            "settings." +
                    "general." +
                    "items_blacklist"
    );

    @EventHandler
    public void onClickInBlacklistedItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item_clicked = event.getCurrentItem();
        if(BackpackManager.isBackpackOpen(player)) {
            if (items_blacklist.contains(item_clicked.getType().name())) {
                event.setCancelled(true);
                String invalid_item = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "invalid_item");
                player.sendMessage(Helper.colorize(invalid_item));
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = player.getItemInHand();
        String inventory_name = NospherBackpack.getInstance().getConfig().getString(
                "settings." +
                        "general." +
                        "inventory_name");
        if(inventory != null && inventory.getName().equalsIgnoreCase(Helper.colorize(inventory_name))) {

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
