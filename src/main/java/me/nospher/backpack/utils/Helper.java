package me.nospher.backpack.utils;

import org.bukkit.ChatColor;

/**
 * @author oNospher
 */
public class Helper {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String replace(String message, String old, String neW) {
        return message.replaceAll(old, neW);
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ignored) {}
        return false;
    }
}
