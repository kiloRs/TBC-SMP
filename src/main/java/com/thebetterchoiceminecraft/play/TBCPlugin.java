package com.thebetterchoiceminecraft.play;

import com.thebetterchoiceminecraft.play.commands.Command;
import com.thebetterchoiceminecraft.play.commands.CoreCommand;
import com.thebetterchoiceminecraft.play.commands.TBCCommand;
import com.thebetterchoiceminecraft.play.hooks.MythicLibHook;
import com.thebetterchoiceminecraft.utils.TBCConfigFile;
import de.jeff_media.jefflib.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class TBCPlugin extends JavaPlugin {
    private static TBCPlugin javaPlugin;
    private static TBCConfigFile mainConfig;
    private static final Logger minecraft = Logger.getLogger("Minecraft");
    private static TBCConfigFile tierConfig;
    private static TBCConfigFile userConfig;

    public static Plugin getPlugin(){
        return javaPlugin;
    }

    public static TBCConfigFile getMainConfig() {
        return mainConfig;
    }

    public static TBCConfigFile getTierConfig() {
        return tierConfig;
    }
        public static TBCConfigFile getUserConfig() {
        return userConfig;
    }


    @Override
    public void onLoad() {
        javaPlugin = this;
        String tbcCore = "tbcCore";
        mainConfig = new TBCConfigFile(this,"/","config");
        tierConfig = new TBCConfigFile(this,"/","tiers");
        userConfig = new TBCConfigFile(this,"/","users");


        //Load Events Register Listeners
    }


    public static void log(String i){
        if (i.startsWith("-")){
            minecraft.warning("[TBC-Plugin] " +  i);
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
            minecraft.warning("[TBC-Plugin] - " + s);
            return;
        }
        minecraft.info("[TBC-Plugin] " + s);
    }

    @Override
    public void onEnable() {
        javaPlugin = this;
        //Here


        //TODO - Finish Configurations / OnEnable

//        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getCommand("enchants").setExecutor(new Command());

        CoreCommand coreCommand = new TBCCommand();

        if (Bukkit.getPluginManager().isPluginEnabled("MythicLib")) {
            new MythicLibHook(this);
        }
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static void debug(String string){
        minecraft.warning(() -> TextUtils.color("&c[TBC-Plugin] " + string));
    }



    public static TBCPlugin getInstance() {
        return javaPlugin;
    }
}

