package fun.tbcraft.play;

import com.sk89q.worldguard.WorldGuard;
import io.lumine.mythic.lib.comp.flags.WorldGuardFlags;
import me.devtec.shared.dataholder.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

public class TBCPlugin extends JavaPlugin {
    private static JavaPlugin javaPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("TBC-SMP");
    private static Config mainConfig;
    @SuppressWarnings("unused")
    private static final boolean containsExtraData = false;
    private static Config settings;
    private static final Logger minecraft = Logger.getLogger("Minecraft");
    private static Config commands;

    public static Plugin getPlugin(){
        return javaPlugin;
    }

    public static Config getMainConfig() {
        return mainConfig;
    }

    public static Config getCommands() {
        return commands;
    }

    @Override
    public void onLoad() {
        javaPlugin = this;
        mainConfig = new Config("tbcSMP/" + "config.yml");
        settings = new Config("tbcSMP/" + "settings.yml");
        commands = new Config("tbcSMP/" + "commands.yml");

        loadSettings(!settings.getKeys().isEmpty(),settings);

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
            minecraft.warning("[TBC SMP] - " + s);
            return;
        }
        minecraft.info("[ T B C - S M P ] " + s);
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
}
