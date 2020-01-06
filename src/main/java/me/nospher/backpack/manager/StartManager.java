package me.nospher.backpack.manager;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.command.BackpackCommand;
import me.nospher.backpack.inventories.listeners.BackpackInventoryCloseListener;
import me.nospher.backpack.inventories.listeners.BackpackInventoryClickListener;
import me.nospher.backpack.listeners.PlayerDamageCloseBackpackListener;
import me.nospher.backpack.listeners.PlayerInteractAtBackpackListener;
import me.nospher.backpack.listeners.PlayerJoinBackpackListener;
import me.nospher.backpack.utils.reflection.Reflection;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

/**
 * @author oNospher
 */
public class StartManager {

    public StartManager(NospherBackpack m) {
        new Reflection(m);
        new CommandManager();
        new ListenerManager();
        new BackpackManager();
    }

    static class ListenerManager {

        ListenerManager() {
            registerListener(new BackpackInventoryCloseListener());
            registerListener(new PlayerInteractAtBackpackListener());
            registerListener(new BackpackInventoryClickListener());
            registerListener(new PlayerDamageCloseBackpackListener());
            registerListener(new PlayerJoinBackpackListener());
        }

        protected void registerListener(Listener listener) {
            NospherBackpack.getInstance().getServer().getPluginManager().registerEvents(listener, NospherBackpack.getInstance());
        }

    }

    static class CommandManager {

        CommandManager() {
            this.registerCommand("backpack", new BackpackCommand());
        }

        protected void registerCommand(String name, CommandExecutor commandExecutor) {
            NospherBackpack.getInstance().getCommand(name).setExecutor(commandExecutor);
        }
    }
}