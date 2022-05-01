package com.thebetterchoiceminecraft.utils;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import net.Indyuce.mmocore.MMOCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Credit: MMOCore Devs
 * Used this format as it seems so useful, but it is from MMOCore [ConfigFile]
 */
public class TBCConfigFile {
        private final File file;
        private final String name;
        private final FileConfiguration config;

        public TBCConfigFile(TBCPlayer p){
            this(TBCPlugin.getPlugin(),"/users",p.getStoredPlayer().getName());
        }

        public TBCConfigFile(String name) {
            this(TBCPlugin.getPlugin(), "", name);
        }

        public TBCConfigFile(String folder, String name) {
            this(MMOCore.plugin, folder, name);
        }

        public TBCConfigFile(Plugin plugin, String folder, String name) {
            config = YamlConfiguration.loadConfiguration(file = new File(plugin.getDataFolder() + folder, (this.name = name) + ".yml"));
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        void create(){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public boolean exists() {
            return file.exists();
        }

        public FileConfiguration getConfig() {
            return config;
        }

        public void save() {
            try {
                config.save(file);
            } catch (IOException exception) {
                TBCPlugin.getPlugin().getLogger().log(Level.SEVERE, "Could not save " + name + ".yml: " + exception.getMessage());
            }
        }

        public void delete() {
            if (file.exists())
                if (!file.delete())
                    TBCPlugin.getPlugin().getLogger().log(Level.SEVERE, "Could not delete " + name + ".yml.");
        }
}
