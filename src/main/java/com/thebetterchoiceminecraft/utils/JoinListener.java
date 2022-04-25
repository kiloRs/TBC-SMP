package com.thebetterchoiceminecraft.utils;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements BaseListener {
    public JoinListener(){

    }
    private boolean isConnected(Player player){
        return player.isOnline();
    }
    @Override
    public String getListenerName() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean shouldUnregisterAfterFirst(boolean state) {
        return false;
    }

    @Override
    public boolean unregister() {
        return false;
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent e){
        Player player = e.getPlayer();

        String welcomePath = TBCPlugin.Constants.WELCOME.getPath();

        if (TBCPlugin.getPlugin().getConfig().isConfigurationSection(TBCPlugin.Constants.WELCOME.getPath())){
            TBCPlugin.log("Welcome Message Found for " + player.getName());
        }
        //TODO Process Messages Better
        if (!TBCPlugin.getMainConfig().exists(TBCPlugin.Constants.WELCOME.getPath())){
            TBCPlugin.getMainConfig().set( TBCPlugin.Constants.WELCOME.getFirst(), "&aThank you for joining &lTheBetterChoice!");
            TBCPlugin.getMainConfig().set(TBCPlugin.Constants.WELCOME.getPlayedBeforeMessage(), "&aWelcome back to TBC!");
            TBCPlugin.getMainConfig().save();
        }
        if (!player.hasPlayedBefore()){
            MessageUtil.send(TBCPlugin.Constants.WELCOME.getFirst(), player);
            TBCConfigFile data = TBCPlayer.get(player).getUserSavedData();

            if (!data.exists()) {
                data.save();
            }
            else {
                if (!data.getConfig().isConfigurationSection("Last")) {
                    data.getConfig().set("Last.Login",System.currentTimeMillis());
                    data.save();
                }
            }
        }
        else {
            MessageUtil.send(TBCPlugin.Constants.WELCOME.getPlayedBeforeMessage(), player);
        }

    }
}
