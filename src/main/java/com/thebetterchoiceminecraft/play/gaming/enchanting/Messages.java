package com.thebetterchoiceminecraft.play.gaming.enchanting;

import de.jeff_media.jefflib.TextUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Messages {
    public static void broadcast(String message) {
        if (message == null)
            return;
        //Updated this to latest Purpur Component Format
        Bukkit.getServer().broadcast(Component.text(coloredMessage(message)));
        //Bukkit.getServer().broadcastMessage(coloredMessage(message));
    }

    public static String translateString(String message) {
        return coloredMessage(message);
    }

    public static String coloredMessage(String msg) {

        //Added Hex SupportS
        if (msg.contains("&") || msg.contains("#") || msg.contains("-")){
            return TextUtils.format(msg);
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
