package com.thebetterchoiceminecraft.play;

import com.thebetterchoiceminecraft.play.commands.Command;
import com.thebetterchoiceminecraft.play.content.ItemTierHandler;
import com.thebetterchoiceminecraft.play.enchanting.MenuListener;
import com.thebetterchoiceminecraft.utils.MessageUtil;
import me.devtec.shared.dataholder.Config;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class TBCPlugin extends JavaPlugin {
    private static TBCPlugin javaPlugin;
    private static Config mainConfig;
    private static Config settings;
    private static final Logger minecraft = Logger.getLogger("Minecraft");

    public static Plugin getPlugin(){
        return javaPlugin;
    }


    public static Config getMainConfig() {
        return mainConfig;
    }


    @Override
    public void onLoad() {
        javaPlugin = this;
        String tbcCore = "tbcCore";
        mainConfig = new Config( "plugins/" + tbcCore + "/" + "config.yml");
        settings = new Config("plugins/" + tbcCore + "/" + "settings.yml");

        //Load Events Register Listeners
    }


    public static void log(String i){
        if (i.startsWith("-")){
            minecraft.warning("[ T B C S M P ] " +  i);
            return;
        }
        if (i.startsWith("!") || i.endsWith("-")){
            throw new RuntimeException(i);
        }
        else {
            log(i,false);
        }
    }
    private static void log(String s, boolean urgent) {
        if (urgent) {
            minecraft.warning("[TBC LOGGER] - " + s);
            return;
        }
        minecraft.info("[ T B C - L O G ] " + s);
    }

    @Override
    public void onEnable() {
        javaPlugin = this;

        String tbcCore = "tbcCore";
        mainConfig = new Config( "plugins/" + tbcCore + "/" + "config.yml");
        settings = new Config("plugins/" + tbcCore + "/" + "settings.yml");

        //Here
        runMessageLoader().init();


        log("-Loading Hooked Plugins...");
        if (javaPlugin.getServer().getPluginManager().isPluginEnabled("MythicMobs")){
            log("Located MythicMobs");
        }
        if (javaPlugin.getServer().getPluginManager().isPluginEnabled("MMOCore")){
            log("Located MMOCore");
        }
        if (javaPlugin.getServer().getPluginManager().isPluginEnabled("WorldGuard")){
            log("Located WorldGuard!");
        }
        log("-Hooked Plugins Complete");



        //TODO - Finish Configurations / OnEnable

        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getCommand("enchants").setExecutor(new Command());

    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Config getSettings() {
        return settings;
    }
    public static ItemTierHandler itemHandler(){
        return ItemTierHandler.getHandler();
    }
    public static void debug(String word, int level){
        if (settings.exists("Debug.Level")){
            if (settings.getInt("Debug.Level")>level){
                debug(word);
            }
        }
    }
    public static void debug(String string){
        Logger m = Logger.getLogger("Minecraft");

        m.warning(() -> "[TBC] -> " + string);
    }
    public static MessageUtil getMessageUtil(){
       return getMessageUtil(TBCPlugin.getMainConfig());
    }
    private static MessageUtil getMessageUtil(Config toUse){
        return new MessageUtil(toUse);
    }
    private static MessageUtil.MessageLoader runMessageLoader(){
        return new MessageUtil.MessageLoader();
    }


    public static TBCPlugin getInstance() {
        return javaPlugin;
    }
}

