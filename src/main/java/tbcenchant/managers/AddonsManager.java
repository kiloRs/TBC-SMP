package psikuvit.tbcenchant.managers;

import org.bukkit.Bukkit;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.addons.ClipPlaceholderAPIManager;

public class AddonsManager {
    private final TBCEnchant plugin;

    private ClipPlaceholderAPIManager placeholderAPI;


    public TBCEnchant getPlugin() {
        return this.plugin;
    }

    public ClipPlaceholderAPIManager getPlaceholderAPI() {
        return this.placeholderAPI;
    }


    public AddonsManager(TBCEnchant plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        String name = this.plugin.getDescription().getName();
        if (isPlugin("PlaceholderAPI")) {
            this.placeholderAPI = new ClipPlaceholderAPIManager(this.plugin);
            this.placeholderAPI.register();
            Bukkit.getConsoleSender().sendMessage(Utils.color("&e[" + name + "] &aSuccessfully hooked into PlaceholderAPI!"));
        }

    }

    private boolean isPlugin(String name) {
        return (Bukkit.getServer().getPluginManager().getPlugin(name) != null);
    }
}
