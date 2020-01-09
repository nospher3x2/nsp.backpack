package me.nospher.backpack.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
public class PlayerOpenBackpackEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    private final Player player;
    private final Inventory inventory;
    private final Location location;
    private Boolean cancelled = false;

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    public void run(){
        Bukkit.getPluginManager().callEvent(this);
    }

}
