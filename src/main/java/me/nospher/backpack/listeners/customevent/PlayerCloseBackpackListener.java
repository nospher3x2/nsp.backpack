package me.nospher.backpack.listeners.customevent;

import lombok.var;
import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.events.BackpackCloseEvent;
import me.nospher.backpack.utils.Helper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author oNospher
 **/
public class PlayerCloseBackpackListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        var closedInventory = event.getInventory();
        var player = event.getPlayer();
        String inventory_name = NospherBackpack.getInstance().getConfig().getString(
                "settings." +
                        "general." +
                        "inventory_name");
        if(closedInventory.getName().equalsIgnoreCase(Helper.colorize(inventory_name))) {
            var customEvent = new BackpackCloseEvent((Player) player, closedInventory, player.getLocation());
            customEvent.run();
        }
    }
}
