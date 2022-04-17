package psikuvit.tbcenchant.Files;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import psikuvit.tbcenchant.TBCEnchant;

public class YAMLFile {
    private static YAMLFile instance;

    private FileManager.Config config;

    private final TBCEnchant core;

    private final String name;

    public static YAMLFile getInstance() {
        return instance;
    }

    public FileManager.Config getConfig() {
        return this.config;
    }

    public TBCEnchant getCore() {
        return this.core;
    }

    public String getName() {
        return this.name;
    }

    public YAMLFile(TBCEnchant TBCEnchant, String name) {
        instance = this;
        this.name = name;
        this.core = TBCEnchant;
    }

    public void reload() {
        this.config.reload();
    }

    public void enable() {
        this.config = this.core.getFileManager().getConfig(this.name + ".yml").copyDefaults(true).save();
    }

    public void disable() {
        for (Player p : Bukkit.getServer().getOnlinePlayers())
            p.closeInventory();
    }
}
