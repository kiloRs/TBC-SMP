package fun.tbcraft.play;

import fun.tbcraft.play.commands.SimpleCommand;
import fun.tbcraft.play.enchanting.MenuListener;
import fun.tbcraft.play.commands.Command;
import fun.tbcraft.play.exceptions.InvalidTBCException;
import fun.tbcraft.utils.MessageUtil;
import me.devtec.shared.dataholder.Config;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
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

        loadSettings(!settings.getKeys().isEmpty(),settings);


        mainConfig.setIfAbsent("Waypoint.Main.Enabled",false);
        mainConfig.setIfAbsent("Waypoint.Main.Permission.Required",false);
        mainConfig.setIfAbsent("Waypoint.Main.Permission.Used","");

        mainConfig.save();

        //TODO Finish COnfigurations
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

        var c = new SimpleCommand("TBC");

        if (c.isRegistered()){
            log("Successful Registered Simple Command");
        }
        //Load Events Register Listeners
    }
    private static void loadSettings(boolean defaulting, Config whichConfig){
        if (defaulting){
            whichConfig.setIfAbsent("Debug.Enabled",true, Arrays.stream(new String[]{"#Hooks up the debug system if enabled."}).toList());
            whichConfig.reload();
        }
        else {
            whichConfig.reload();
        }
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
        try {
            MessageUtil.getLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tbcCore = "tbcCore";
        mainConfig = new Config( "plugins/" + tbcCore + "/" + "config.yml");
        settings = new Config("plugins/" + tbcCore + "/" + "settings.yml");


        if (settings.getKeys().isEmpty()){
            debug("No Settings Found...Loading New Settings - AimCore");
        }
        loadSettings(!settings.getKeys().isEmpty(),settings);
        debug("Successful Debug on Configs");


        mainConfig.setIfAbsent("Waypoint.Main.Enabled",false);
        mainConfig.setIfAbsent("Waypoint.Main.Permission.Required",false);
        mainConfig.setIfAbsent("Waypoint.Main.Permission.Used","");

        mainConfig.save();

        if (!this.getServer().getOnlineMode()){
            throw new InvalidTBCException("Online Mode has been disabled, meaning UUID's could be broken!");
        }
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

        var c = new SimpleCommand("TBC");

        SimpleCommand command = new SimpleCommand("TBC");
        if (c.isRegistered()){
            log("Successful Registered Simple Command");
        }



        getServer().getPluginManager().registerEvents((Listener)new MenuListener(this), (Plugin)this);
        getCommand("enchants").setExecutor((CommandExecutor)new Command());

    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Config getSettings() {
        return settings;
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


    public enum Constants{
        WELCOME("Welcome"),LEAVE("Leave"),KICK("Kick"),BAN("Ban"),ENTER_COMBAT("Combat.Enter"),LEAVE_COMBAT("Combat.Leave"),BOSS_DEFEAT("Boss.Defeat"),BOSS_SUCCESS("Boss.Success"),ERROR_LIGHT("Error.Light"),ERROR_HEAVY("Error.Heavy");
        private final String key;
        private final MessageUtil.Message message;
        Constants(String key){
            this.key = key;
            this.message = getMessageUtil().getMessage(this.key);
        }
        private String getKey() {
            return key;
        }
        public String getPath(){
            return "Message." + key;
        }
    }
    public static TBCPlugin getInstance() {
        return javaPlugin;
    }
}

