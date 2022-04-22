package fun.tbcraft.play.commands;

import fun.tbcraft.play.TBCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.PluginsCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public interface BaseCommand extends CommandExecutor, TabCompleter {
    String getCommand();

    default void register(){
        TBCPlugin.log("Registering Command: " + getCommand().toUpperCase(Locale.ROOT));
        PluginCommand c = Bukkit.getPluginCommand(getCommand());
        if (c ==null){
            throw new RuntimeException("Bad Command Exception");



        }
        if (!c.isRegistered() || c.getExecutor()!=this){
            c.register(Bukkit.getCommandMap());
            c.setExecutor(this);
            c.setTabCompleter(this);


            TBCPlugin.log("Registered Commands!");
        }
    }

    /**
     * Commands
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    @Override
    @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}
