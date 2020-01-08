package me.nospher.backpack;

import me.nospher.backpack.autoupdater.AutoUpdater;
import me.nospher.backpack.manager.StartManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 */
public class NospherBackpack extends JavaPlugin {

    private static NospherBackpack instance;
    private static AutoUpdater updater = new AutoUpdater("https://www.dropbox.com/s/9dure2m8efmzq0r/NospherBackpack.jar?dl=1");

    @Override
    public void onEnable() {
        instance = this;
        new StartManager(this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        if(updater.fetch()) {
            updater.update("NospherBackpack");
        }
    }

    public static NospherBackpack getInstance() {
        return instance;
    }
}
