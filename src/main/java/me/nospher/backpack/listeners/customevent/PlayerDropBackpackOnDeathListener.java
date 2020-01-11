package me.nospher.backpack.listeners.customevent;

import lombok.val;
import lombok.var;
import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.events.PlayerDropBackpackOnDeathEvent;
import me.nospher.backpack.utils.NBTTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * @author oNospher
 **/
public class PlayerDropBackpackOnDeathListener implements Listener {

    private static HashMap<Player, ItemStack> savedBackpack = new HashMap<>();

    @EventHandler
    public void onDrop(PlayerDeathEvent event) {
        val customEvent = new PlayerDropBackpackOnDeathEvent(event.getEntity(), event.getEntity().getLocation());
        customEvent.run();
        if(customEvent.isCancelled()) {
            event.getDrops().removeIf(itemStack -> {
                if (NBTTag.getNBTTag(itemStack).hasKey("isBackpack")) {
                    savedBackpack.put(event.getEntity(), itemStack);
                    return true;
                }
                return false;
            });
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTask(NospherBackpack.getInstance(), ()-> {
            ItemStack item = savedBackpack.remove(player);
            if (item != null) player.getInventory().addItem(item);
        });
    }

}
