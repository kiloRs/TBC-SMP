package com.thebetterchoiceminecraft.play.commands;

import com.thebetterchoiceminecraft.play.gaming.enchanting.GUI.Page;
import com.thebetterchoiceminecraft.play.gaming.enchanting.GUI.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Command implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if (!p.hasPermission("tbcenchant.menu")) {
            p.sendMessage(ChatColor.RED + "You do not have sufficient permissions to use this plugin.");
            return true;
        }
        (new Page(new PlayerMenuUtility(p))).open();
        return false;
    }
}
