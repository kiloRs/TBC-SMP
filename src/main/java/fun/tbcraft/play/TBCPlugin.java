package fun.tbcraft.play;

import fun.tbcraft.utils.MessageUtil;
import me.devtec.shared.dataholder.Config;
import me.devtec.shared.dataholder.DataType;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TBCPlugin extends JavaPlugin {
    private static JavaPlugin javaPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("TBC-SMP");
    private static Config mainConfig;
    @SuppressWarnings("unused")
    private static final boolean containsExtraData = false;
    private static Config settings;
    private static final Logger minecraft = Logger.getLogger("Minecraft");
    private final String tbcCore = "tbcCore";

    public static Plugin getPlugin(){
        return javaPlugin;
    }

    public static Config getMainConfig() {
        return mainConfig;
    }


    @Override
    public void onLoad() {
        javaPlugin = this;
        mainConfig = new Config(tbcCore + "/" + "config.yml");
        settings = new Config(tbcCore + "/" + "settings.yml");

        loadSettings(!settings.getKeys().isEmpty(),settings);

        if (!mainConfig.existsKey("Attack.Listener")) {
            mainConfig.setIfAbsent("Attack.Listener",true, List.of("#True to enable block and entity listeners!"));
            mainConfig.save(DataType.YAML);

            Server bukkit = Bukkit.getServer();
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
        private MessageUtil.Message message;
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
}
