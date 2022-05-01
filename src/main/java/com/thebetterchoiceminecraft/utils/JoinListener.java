package com.thebetterchoiceminecraft.utils;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements BaseListener {
    private HandlerList handlers = null;


    @Override
    public boolean unregister() {
        if (handlers == null){
            handlers = new HandlerList();
        }
        try {
            handlers.unregister(this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, TBCPlugin.getPlugin());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        PlayerQuitEvent.QuitReason leaveResaon = e.getReason();

        if (leaveResaon == PlayerQuitEvent.QuitReason.KICKED){
            TBCPlugin.log("Kicked Player Found: " + player.getName());
        }
        else {
            if (leaveResaon == PlayerQuitEvent.QuitReason.ERRONEOUS_STATE){
                TBCPlugin.log("Error Disconnect for " + player.getName());
            }
        }
    }
    @EventHandler
    public void onJ(PlayerJoinEvent e){
        Player player = e.getPlayer();
        handlers = e.getHandlers();

        TBCPlayer i = TBCPlayer.get(player);
        TBCConfigFile z = i.getUserSavedData();

        if (!z.exists()){
            z.create();
        }

        String rank = z.getConfig().contains("Rank") ? z.getConfig().getString("Rank") : "";
        String clazz = z.getConfig().contains("Class") ? z.getConfig().getString("Class") : "";


    }
}
