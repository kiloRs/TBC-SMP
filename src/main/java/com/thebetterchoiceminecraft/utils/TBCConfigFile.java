package com.thebetterchoiceminecraft.utils;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.guild.provided.Guild;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Credit: MMOCore Devs
 * Used this format as it seems so useful, but it is from MMOCore [ConfigFile]
 */
public class TBCConfigFile {
        private final File file;
        private final String name;
        private final FileConfiguration config;

        public TBCConfigFile(Player player) {
            this(player.getUniqueId());
        }

        public TBCConfigFile(TBCPlayer p){
            this(TBCPlugin.getPlugin(),"/users",p.getStoredPlayer().getName());
        }
        public TBCConfigFile(UUID uuid) {
            this(TBCPlugin.getPlugin(), "/userdata", uuid.toString());
        }

        public TBCConfigFile(Guild guild) {
            this(TBCPlugin.getPlugin(), "/guilds", guild.getId());
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

        public boolean exists() {
            return file.exists();
        }

        public FileConfiguration getConfig() {
            return config;
        }

        public ConfigurationSection getSection(String i){
            return config.isConfigurationSection(i)?config.getConfigurationSection(i):null;
        }
        public ConfigurationSection getOrNewSection(String id){
            return config.isConfigurationSection(id)?getSection(id):config.createSection(id);
        }
        public ConfigurationSection getOrNewSection(String id, Map<String,Object> dataSerialized){
            return config.isConfigurationSection(id)?getOrNewSection(id):config.createSection(id,dataSerialized);
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
        public boolean rename(String newName, Plugin plugin){
           return file.renameTo(new File(plugin.getDataFolder() + "/" + newName + ".yml"));
    }
}
