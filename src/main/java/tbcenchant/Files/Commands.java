package psikuvit.tbcenchant.Files;

import org.bukkit.configuration.file.YamlConfiguration;
import psikuvit.tbcenchant.TBCEnchant;

import java.util.List;

public class Commands extends YAMLFile {
    public List<String> commands;

    public Commands(TBCEnchant tbcEnchant, String name) {
        super(tbcEnchant, name);
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
        this.commands = cf.getStringList("commands");
    }
}
