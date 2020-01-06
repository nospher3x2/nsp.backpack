package me.nospher.backpack.listeners;

import me.nospher.backpack.manager.BackpackManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author oNospher
 */
public class PlayerJoinBackpackListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BackpackManager.setBackpackOpen(player, false);
    }
}
