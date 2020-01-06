package me.nospher.backpack.listeners;

import me.nospher.backpack.manager.BackpackManager;
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
                player.sendMessage("§cVocê recebeu dano, por isso a sua mochila foi fechada.");
                BackpackManager.setBackpackOpen(player, false);
            }
        }
    }
}
