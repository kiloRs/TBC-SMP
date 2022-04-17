package psikuvit.tbcenchant.cms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.cms.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandManager implements CommandExecutor, TabCompleter {
    public final List<Command> commands = new ArrayList<>();

    public CommandManager(String command) {
        TBCEnchant.getInstance().getCommand(command).setExecutor(this);
        TBCEnchant.getInstance().getCommand(command).setTabCompleter(this);
        registerCommands();
    }

    public void registerCommands() {
        registerCommand(new OpenEnchantmentTable());
        registerCommand(new HelpCommand());
        registerCommand(new ReloadCommand());
        registerCommand(new EnchantListCommand());
        registerCommand(new EnchantItemCommand());
        this.commands.sort(Comparator.comparing(command -> (String)command.aliases.get(0)));
    }

    public void registerCommand(Command command) {
        this.commands.add(command);
    }

    public void unregisterCommand(org.bukkit.command.Command command) {
        this.commands.remove(command);
    }

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (args.length == 0 &&
                commandSender instanceof org.bukkit.entity.Player)
            return true;
        for (Command command : this.commands) {
            if (command.aliases.contains(args[0])) {
                Objects.requireNonNull(command);
                if (command.onlyForPlayers && !(commandSender instanceof org.bukkit.entity.Player)) {
                    commandSender.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("mustBeAPlayer")
                            .replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
                    return false;
                }
                if (commandSender.hasPermission(command.permission) || command.permission
                        .equalsIgnoreCase("") || command.permission
                        .equalsIgnoreCase("tbcenchant.")) {
                    Objects.requireNonNull(command);
                } else {
                    commandSender.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("noPermission")
                            .replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
                    return false;
                }
                command.execute(commandSender, args);
                return true;
            }
        }
        commandSender.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("unknownCommand")
                .replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
        return false;
    }

    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> result = new ArrayList<>();
            for (Command command : this.commands) {
                for (String alias : command.aliases) {
                    if (alias.toLowerCase().startsWith(args[0].toLowerCase())) {
                        Objects.requireNonNull(command);
                        if (commandSender.hasPermission(command.permission) || command.permission
                                .equalsIgnoreCase("") || command.permission
                                .equalsIgnoreCase("tbcenchant."))
                            result.add(alias);
                    }
                }
            }
            return result;
        }
        for (Command command : this.commands) {
            if (command.aliases.contains(args[0])) {
                Objects.requireNonNull(command);
                if (commandSender
                        .hasPermission(command.permission) || command.permission.equalsIgnoreCase("") || command.permission
                        .equalsIgnoreCase("tbcenchant."))
                    return command.onTabComplete(commandSender, cmd, label, args);
            }
        }
        return null;
    }
}
