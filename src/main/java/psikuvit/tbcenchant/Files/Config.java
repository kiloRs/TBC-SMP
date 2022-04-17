package psikuvit.tbcenchant.Files;

import org.bukkit.configuration.file.YamlConfiguration;
import psikuvit.tbcenchant.Item;
import psikuvit.tbcenchant.Object.AdvancedSettings;
import psikuvit.tbcenchant.Object.LoreAddition;
import psikuvit.tbcenchant.Object.LoreSettings;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config extends YAMLFile {
    public String prefix = "&e&lTBCEnchant &7";

    public String mainCommandPerm = "";

    public boolean setAsDefaultEnchantingTable = true;

    public boolean addInfoOnEnchant = true;

    public List<String> infoToEnchantedItem = Arrays.asList(new String[] { "&9%enchant_name% %enchant_level%", "&7%enchant_description%" });

    public boolean hideOriginalEnchant = true;

    public boolean byPassEnchantmentRestrictions = false;

    public List<String> disabledWorlds = new ArrayList<>();

    public boolean loadAllEcoEnchants = false;

    public boolean loadAllAdvancedEnchants = false;

    public boolean removeIncompatibleEnchants = true;

    public AdvancedSettings advancedSettings;

    public LoreSettings loreSettings;

    public Item bookItem;

    public double aurelliumXP;

    public String aurelliumSkill;

    public Config(TBCEnchant TBCEnchant, String name) {
        super(TBCEnchant, name);
    }

    public void enable() {
        super.enable();
        loadDefaults();
    }

    public void reload() {
        super.reload();
        loadDefaults();
    }

    private void loadDefaults() {
        YamlConfiguration cf = getConfig().get();
        this.prefix = cf.getString("prefix");
        this.mainCommandPerm = cf.getString("mainCommandPerm");
        this.setAsDefaultEnchantingTable = cf.getBoolean("setAsDefaultEnchantingTable");
        this.setAsDefaultEnchantingTable = cf.getBoolean("setAsDefaultEnchantingTable");
        this.addInfoOnEnchant = cf.getBoolean("addInfoOnEnchant");
        this.infoToEnchantedItem = cf.getStringList("infoToEnchantedItem");
        this.hideOriginalEnchant = cf.getBoolean("hideOriginalEnchant");
        this.byPassEnchantmentRestrictions = cf.getBoolean("byPassEnchantmentRestrictions");
        this.disabledWorlds = cf.getStringList("disabledWorlds");
        this.loadAllEcoEnchants = cf.getBoolean("loadAllEcoEnchants");
        this.loadAllAdvancedEnchants = cf.getBoolean("loadAllAdvancedEnchants");
        this.removeIncompatibleEnchants = cf.getBoolean("removeIncompatibleEnchants");
        this

                .advancedSettings = new AdvancedSettings(cf.getInt("advancedSettings.limit"), cf.getInt("advancedSettings.perLine"), cf.getString("advancedSettings.line"));
        this.loreSettings = new LoreSettings(LoreAddition.valueOf(cf.getString("loreSettings.type")), cf.getInt("loreSettings.line"));
        this.bookItem = Utils.getItemFromConfig(getConfig().get(), "bookItem");
        this.aurelliumXP = getConfig().get().getDouble("aurelliumSkills.xp");
        this.aurelliumSkill = getConfig().get().getString("aurelliumSkills.skill");
    }
}
