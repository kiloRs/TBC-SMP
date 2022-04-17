package psikuvit.tbcenchant;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import psikuvit.tbcenchant.API.TBCEnchantAPI;
import psikuvit.tbcenchant.API.TBCEnchantAPIIIMP;
import psikuvit.tbcenchant.Files.*;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.cms.CommandManager;
import psikuvit.tbcenchant.listener.AureliumSkillsListener;
import psikuvit.tbcenchant.listener.CommandsListener;
import psikuvit.tbcenchant.listener.EnchantingTableListener;
import psikuvit.tbcenchant.managers.AddonsManager;
import psikuvit.tbcenchant.managers.ItemManager;
import psikuvit.tbcenchant.playerdata.PlayersData;

public class TBCEnchant extends JavaPlugin {
    private Config configuration;

    private Messages messages;

    private TBCEnchantement hyperEnchantments;

    private Inventories inventories;

    private PlayersData playersData;

    private CommandManager commandManager;

    private AddonsManager addonsManager;

    private Economy economy;

    private FileManager fileManager;

    private boolean advancedEnchantments;

    private boolean ecoEnchants;

    private EnchantsHandler enchantsHandler;

    private Commands commands;

    private TBCEnchantAPI api;

    public Config getConfiguration() {
        return this.configuration;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public TBCEnchantement getHyperEnchantments() {
        return this.hyperEnchantments;
    }

    public Inventories getInventories() {
        return this.inventories;
    }

    public PlayersData getPlayersData() {
        return this.playersData;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public AddonsManager getAddonsManager() {
        return this.addonsManager;
    }

    public Economy getEconomy() {
        return this.economy;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public boolean isAdvancedEnchantments() {
        return this.advancedEnchantments;
    }

    public boolean isEcoEnchants() {
        return this.ecoEnchants;
    }

    public EnchantsHandler getEnchantsHandler() {
        return this.enchantsHandler;
    }

    public Commands getCommands() {
        return this.commands;
    }

    public TBCEnchantAPI getApi() {
        return this.api;
    }

    public static TBCEnchant getInstance() {
        return (TBCEnchant)getPlugin(TBCEnchant.class);
    }

    public void onEnable() {
        this.fileManager = new FileManager(this);
        this.enchantsHandler = new EnchantsHandler(this);
        setupAddons();
        loadConfigs();
        registerListeners(new Listener[] { (Listener)new EnchantingTableListener(), (Listener)new ItemManager(), (Listener)new CommandsListener(this) });
        hookAurelium();
        this.commandManager = new CommandManager("tbcenchant");
        this.addonsManager = new AddonsManager(this);
        this.playersData = new PlayersData();
        this.api = (TBCEnchantAPI)new TBCEnchantAPIIIMP(this);
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + getDescription().getName() + " Has been enabled!"));
        setupEconomy();
    }

    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(HumanEntity::closeInventory);
        getLogger().info(getDescription().getName() + " Disabled!");
    }

    public void registerListeners(Listener... listener) {
        for (Listener l : listener)
            Bukkit.getPluginManager().registerEvents(l, (Plugin)this);
    }

    private void hookAurelium() {
        if (Bukkit.getPluginManager().getPlugin("AureliumSkills") != null) {
            Bukkit.getConsoleSender().sendMessage(Utils.color("&e[" + getDescription().getName() + "] &aSuccessfully hooked with AureliumSkills!"));
            registerListeners(new Listener[] { (Listener)new AureliumSkillsListener(this) });
        }
    }

    private void setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
            if (rsp != null)
                this.economy = (Economy)rsp.getProvider();
        }
    }

    private void setupAddons() {
        if (Bukkit.getPluginManager().getPlugin("AdvancedEnchantments") != null) {
            Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + getDescription().getName() + " &aSuccessfully hooked into AdvancedEnchantments!"));
            this.advancedEnchantments = true;
        }
    }

    public void loadConfigs() {
        this.configuration = new Config(this, "config");
        this.messages = new Messages(this, "messages");
        this.inventories = new Inventories(this, "inventories");
        this.hyperEnchantments = new TBCEnchantement(this, "tbcEnchantments");
        this.commands = new Commands(this, "commands");
        this.configuration.enable();
        this.hyperEnchantments.enable();
        this.messages.enable();
        this.inventories.enable();
        this.commands.enable();
    }

    public void reloadConfigs() {
        this.configuration.reload();
        this.hyperEnchantments.reload();
        this.messages.reload();
        this.inventories.reload();
        this.commands.reload();
    }
}
