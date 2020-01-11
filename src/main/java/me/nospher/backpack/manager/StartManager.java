package me.nospher.backpack.manager;

import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.command.BackpackCommand;
import me.nospher.backpack.inventories.listeners.BackpackInventoryCloseListener;
import me.nospher.backpack.inventories.listeners.BackpackInventoryClickListener;
import me.nospher.backpack.listeners.PlayerDamageCloseBackpackListener;
import me.nospher.backpack.listeners.PlayerInteractAtBackpackListener;
import me.nospher.backpack.listeners.PlayerJoinBackpackListener;
import me.nospher.backpack.listeners.customevent.PlayerCloseBackpackListener;
import me.nospher.backpack.listeners.customevent.PlayerDropBackpackOnDeathListener;
import me.nospher.backpack.listeners.PlayerOpenBackpackListener;
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
            registerListener(new PlayerCloseBackpackListener());
            registerListener(new PlayerOpenBackpackListener());
            registerListener(new PlayerDropBackpackOnDeathListener());
        }

        protected void registerListener(Listener listener) {
            NospherBackpack.getInstance().getServer().getPluginManager().registerEvents(listener, NospherBackpack.getInstance());
        }

    }

    static class CommandManager {

        CommandManager() {
            this.registerCommand("backpack", new BackpackCommand());
        }

        protected void registerCommand(String command, CommandExecutor commandExecutor) {
            NospherBackpack.getInstance().getCommand(command).setExecutor(commandExecutor);
        }
    }
}