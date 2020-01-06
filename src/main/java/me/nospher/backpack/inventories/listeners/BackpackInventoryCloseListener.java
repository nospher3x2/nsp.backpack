package me.nospher.backpack.inventories.listeners;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.manager.BackpackManager;
import me.nospher.backpack.utils.Helper;
import me.nospher.backpack.utils.NBTTag;
import me.nospher.backpack.utils.serializer.InventorySerialize;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 */
public class BackpackInventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory closedInventory = event.getInventory();
        ItemStack item = player.getItemInHand();
        String inventory_name = NospherBackpack.getInstance().getConfig().getString(
                "settings." +
                        "backpack." +
                        "inventory_name");
        if(closedInventory.getName().equalsIgnoreCase(Helper.colorize(inventory_name))) {

            if (item == null || item.getType() == Material.AIR) return;

            NBTTagCompound compound = NBTTag.getNBTTag(item);
            if (!compound.hasKey("isBackpack")
                    || !compound.hasKey("inventory")
                    || !compound.hasKey("rows")
                    || !compound.hasKey("stacked")) return;

            compound.setString("inventory", InventorySerialize.toJson(closedInventory).toString());
            ItemStack itemStack = NBTTag.setNBTTag(item, compound);

            BackpackManager.setBackpackOpen(player, false);
            BackpackManager.getPrevention().remove(player);
            player.setItemInHand(itemStack);
        }
    }
}
