package com.thebetterchoiceminecraft.play.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public interface CoreCommand extends CommandExecutor, TabCompleter {

    public String getID();

    public default org.bukkit.command.Command getThisCommand() {
        return Bukkit.getCommandMap().getCommand(getID());
    }

    public default boolean allowsHex(){
        return true;
    }
    public default boolean allowsPlaceholders(){
        return true;
    }


}