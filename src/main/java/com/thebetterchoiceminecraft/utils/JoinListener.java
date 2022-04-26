package com.thebetterchoiceminecraft.utils;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import io.lumine.mythic.utils.promise.ThreadContext;
import io.lumine.mythic.utils.tasks.builder.TaskBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements BaseListener {
    public JoinListener() {

    }

    @Override
    public void unregisterAfter(long timeZone, boolean offForGood) {
        TaskBuilder.newBuilder().on(ThreadContext.SYNC).after(timeZone).run(() -> new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    HandlerList.unregisterAll(JoinListener.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TBCPlugin.log("Unregistered " + JoinListener.this + " after " + timeZone);
            }
        });
    }

    @Override
    public boolean unregister() {
        try {
            HandlerList.unregisterAll(this);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this,TBCPlugin.getPlugin());
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (TBCPlugin.getPlugin().getConfig().isConfigurationSection(MessageUtil.MessageKeys.WELCOME.getPath())){
            TBCPlugin.log("Welcome Message Found for " + player.getName());
        }
        //TODO Process Messages Better
        if (!TBCPlugin.getMainConfig().exists(MessageUtil.MessageKeys.WELCOME.getPath())){
            TBCPlugin.getMainConfig().set(MessageUtil.MessageKeys.WELCOME.getFirst(), "&aThank you for joining &lTheBetterChoice!");
            TBCPlugin.getMainConfig().set(MessageUtil.MessageKeys.WELCOME.getPlayedBeforeMessage(), "&aWelcome back to TBC!");
            TBCPlugin.getMainConfig().save();
        }
        if (!player.hasPlayedBefore()){
            MessageUtil.send(MessageUtil.MessageKeys.WELCOME.getFirst(), player);
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
            MessageUtil.send(MessageUtil.MessageKeys.WELCOME.getPlayedBeforeMessage(), player);
        }

    }
}
