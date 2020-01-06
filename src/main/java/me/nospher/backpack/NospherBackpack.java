package me.nospher.backpack;

import me.nospher.backpack.manager.StartManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 */
public class NospherBackpack extends JavaPlugin {

    private static NospherBackpack instance;

    @Override
    public void onEnable() {
        instance = this;
        new StartManager(this);
        saveDefaultConfig();
    }

    public static NospherBackpack getInstance() {
        return instance;
    }
}
