package psikuvit.tbcenchant.cms;

import java.util.List;
import java.util.Objects;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;
import org.bukkit.command.CommandSender;

public abstract class Command {
    public final List<String> aliases;

    public final String description;

    public final String permission;

    public final boolean onlyForPlayers;

    public List<String> getAliases() {
        return this.aliases;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean isOnlyForPlayers() {
        return this.onlyForPlayers;
    }

    public final boolean enabled = true;

    public final String usage;

    public boolean isEnabled() {
        Objects.requireNonNull(this);
        return true;
    }

    public String getUsage() {
        return this.usage;
    }

    public Command(List<String> aliases, String description, String permission, boolean onlyForPlayers, String usage) {
        this.aliases = aliases;
        this.description = description;
        this.permission = permission;
        this.onlyForPlayers = onlyForPlayers;
        this.usage = usage;
    }

    public abstract void execute(CommandSender paramCommandSender, String[] paramArrayOfString);

    public abstract List<String> onTabComplete(CommandSender paramCommandSender, org.bukkit.command.Command paramCommand, String paramString, String[] paramArrayOfString);
}
