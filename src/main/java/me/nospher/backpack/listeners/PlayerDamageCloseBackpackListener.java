package me.nospher.backpack.listeners;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.manager.BackpackManager;
import me.nospher.backpack.utils.Helper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author oNospher
 */
public class PlayerDamageCloseBackpackListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (BackpackManager.isBackpackOpen(player)) {
                player.closeInventory();
                String player_damaged = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "player_damaged");
                player.sendMessage(Helper.colorize(player_damaged));
                BackpackManager.setBackpackOpen(player, false);
            }
        }
    }
}
