package com.thebetterchoiceminecraft.play.enchanting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Messages {
    public static void broadcast(String message) {
        if (message == null)
            return;
        Bukkit.getServer().broadcastMessage(coloredMessage(message));
    }

    public static String translateString(String message) {
        return coloredMessage(message);
    }

    public static String coloredMessage(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
