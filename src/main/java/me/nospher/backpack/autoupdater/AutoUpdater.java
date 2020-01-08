package me.nospher.backpack.autoupdater;


import me.nospher.backpack.NospherBackpack;
import me.nospher.backpack.utils.HttpRequest;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author oNospher
 **/
public class AutoUpdater {

    private String dropboxLink;
    private byte[] buffer;

    public AutoUpdater(String dropboxLink) {
        this.dropboxLink = dropboxLink;
    }

    public boolean fetch() {
        if(NospherBackpack.getInstance().getConfig().getBoolean("settings.general.autoupdate")) {
            if (dropboxLink != null) {
                HttpRequest hr = HttpRequest.get(dropboxLink);
                if (hr.code() == 200 && hr.contentType().equals("application/binary")) {
                    this.buffer = hr.bytes();
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("resource")
    public void update(String pluginFileName) {
        System.out.println("§eAtualizando: " + pluginFileName);

        File file = new File("plugins/" + pluginFileName + ".jar");
        file.getParentFile().mkdirs();
        try {
            new FileOutputStream(file).write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§aAtualizado: §f" + pluginFileName);
    }
}