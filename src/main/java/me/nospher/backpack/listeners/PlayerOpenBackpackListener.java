package me.nospher.backpack.listeners;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.events.PlayerOpenBackpackEvent;
import me.nospher.backpack.utils.Helper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

/**
 * @author oNospher
 **/
public class PlayerOpenBackpackListener implements Listener {

    List<String> worlds_blacklist = NospherBackpack.getInstance().getConfig().getStringList(
            "settings." +
                    "general." +
                    "worlds_blacklist"
    );

    @EventHandler
    public void onOpen(PlayerOpenBackpackEvent event) {
        Player player = event.getPlayer();
        if(worlds_blacklist.contains(player.getWorld().getName())) {
            String invalid_world = NospherBackpack.getInstance().getConfig().getString(
                    "settings." +
                            "messages." +
                            "invalid_world");
            event.setCancelled(true);
            player.sendMessage(Helper.colorize(invalid_world));
        }
    }
}
