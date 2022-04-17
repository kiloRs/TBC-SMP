package psikuvit.tbcenchant.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import psikuvit.tbcenchant.Object.ItemHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemManager implements Listener {
    private final Map<UUID, ItemHandler> players = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (this.players.containsKey(player.getUniqueId()))
            return;
        ItemHandler itemHandler = new ItemHandler();
        this.players.put(player.getUniqueId(), itemHandler);
        itemHandler.start(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!this.players.containsKey(player.getUniqueId()))
            return;
        ((ItemHandler)this.players.get(player.getUniqueId())).stop();
    }

    @EventHandler
    public void onQuit(PlayerKickEvent event) {
        Player player = event.getPlayer();
        if (!this.players.containsKey(player.getUniqueId()))
            return;
        ((ItemHandler)this.players.get(player.getUniqueId())).stop();
    }
}
