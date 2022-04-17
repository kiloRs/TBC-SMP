package psikuvit.tbcenchant.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import psikuvit.tbcenchant.API.Events.Enchant;
import psikuvit.tbcenchant.TBCEnchant;

public class CommandsListener implements Listener {
    private final TBCEnchant plugin;

    public CommandsListener(TBCEnchant plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBrew(Enchant event) {
        Player player = event.getPlayer();
        if (player == null)
            return;
        (this.plugin.getCommands()).commands.forEach(command -> Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("%player%", player.getName())));
    }
}
