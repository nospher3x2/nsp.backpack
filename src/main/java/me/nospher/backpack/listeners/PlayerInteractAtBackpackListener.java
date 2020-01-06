package me.nospher.backpack.listeners;

import me.nospher.backpack.inventories.BackpackInventory;
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

/**
 * @author oNospher
 */
public class PlayerInteractAtBackpackListener implements Listener {

    final Action[] actions = new Action[]{
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK
    };

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(Arrays.asList(actions).contains(action)) {
            if(item == null || item.getType() == Material.AIR) return;
            NBTTagCompound compound = NBTTag.getNBTTag(item);
            if(!compound.hasKey("isBackpack") || !compound.hasKey("rows")) return;
            event.setCancelled(true);
            BackpackInventory.open(player);
        }
    }
}
