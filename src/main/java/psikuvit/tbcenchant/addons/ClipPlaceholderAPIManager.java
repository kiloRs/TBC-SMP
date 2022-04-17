package psikuvit.tbcenchant.addons;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Files.TBCEnchantement;
import psikuvit.tbcenchant.TBCEnchant;

public class ClipPlaceholderAPIManager extends PlaceholderExpansion {
    private final TBCEnchant plugin;

    public ClipPlaceholderAPIManager(TBCEnchant plugin) {
        this.plugin = plugin;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    
    public String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    
    public String getIdentifier() {
        return "tbcenchant";
    }

    
    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player,  String identifier) {
        if (player == null)
            return identifier;
        TBCEnchantement tbcEnchantement = this.plugin.getHyperEnchantments();
        for (TBCEnchants tbcenchant : tbcEnchantement.enchantments.values()) {
            if (identifier.equals("name_" + tbcenchant.getEnchantmentName()))
                return tbcenchant.getDisplayName();
            if (identifier.equals("lore_" + tbcenchant.getEnchantmentName()))
                return String.join(" ", tbcenchant.getDescription());
        }
        return null;
    }
}
