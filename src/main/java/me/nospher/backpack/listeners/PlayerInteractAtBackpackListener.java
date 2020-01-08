package me.nospher.backpack.listeners;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.inventories.BackpackInventory;
import me.nospher.backpack.utils.Helper;
import me.nospher.backpack.utils.NBTTag;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author oNospher
 */
public class PlayerInteractAtBackpackListener implements Listener {

    final Action[] actions = new Action[]{
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK
    };

    List<String> worlds_blacklist = NospherBackpack.getInstance().getConfig().getStringList(
            "settings." +
                    "general." +
                    "worlds_blacklist"
    );

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(Arrays.asList(actions).contains(action)) {
            if(item == null || item.getType() == Material.AIR) return;
            NBTTagCompound compound = NBTTag.getNBTTag(item);
            if(!compound.hasKey("isBackpack") || !compound.hasKey("rows")) return;
            if(worlds_blacklist.contains(player.getWorld().getName())) {
                String invalid_world = NospherBackpack.getInstance().getConfig().getString(
                        "settings." +
                                "messages." +
                                "invalid_world");
                event.setCancelled(true);
                player.sendMessage(Helper.colorize(invalid_world));
                return;
            }
            event.setCancelled(true);
            BackpackInventory.open(player);
        }
    }
}
