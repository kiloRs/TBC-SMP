package psikuvit.tbcenchant.Files;

import net.advancedplugins.ae.api.AEAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.Enchs.HyperAdvancedEnchantment;
import psikuvit.tbcenchant.Enchs.NormalEnchantment;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.AEUtils;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.libs.XEnchantment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TBCEnchantement extends YAMLFile {
    public HashMap<String, TBCEnchants> enchantments;

    public TBCEnchantement(TBCEnchant TBCEnchant, String name) {
        super(TBCEnchant, name);
    }

    public void enable() {
        super.enable();
        loadDefaults();
    }

    public void reload() {
        getConfig().reload();
        loadDefaults();
    }

    private void loadDefaults() {
        this.enchantments = new HashMap<>();
        ConfigurationSection section = getConfig().get().getConfigurationSection("tbcenchantments");
        if (section == null)
            return;
        int ad = 0;
        int van = 0;
        int eco = 0;
        for (String enchantID : section.getKeys(false)) {
            try {
                NormalEnchantment normalEnchantment = null;
                if (getConfig().get().contains("tbcenchantments." + enchantID + ".enabled") && !getConfig().get().getBoolean("tbcenchantments." + enchantID + ".enabled"))
                    continue;
                String displayName = getConfig().get().getString("tbcenchantments." + enchantID + ".displayName");
                boolean useMoney = getConfig().get().getBoolean("tbcenchantments." + enchantID + ".useMoney");
                HashMap<Integer, Double> requiredMoney = getConfig().get().contains("tbcenchantments." + enchantID + ".requiredMoney") ? Utils.deserializeRequiredMoney(getConfig().get().getStringList("tbcenchantments." + enchantID + ".requiredMoney")) : new HashMap<>();
                String descriptionYAML = getConfig().get().getString("tbcenchantments." + enchantID + ".description");
                HashMap<Integer, Integer> requiredLevel = getConfig().get().contains("tbcenchantments." + enchantID + ".requiredLevel") ? Utils.deserializeRequiredLevels(getConfig().get().getStringList("tbcenchantments." + enchantID + ".requiredLevel")) : new HashMap<>();
                int requiredBook = getConfig().get().getInt("tbcenchantments." + enchantID + ".requiredBookShelf");
                if (getConfig().get().contains("tbcenchantments." + enchantID + ".advancedEnchantment") && TBCEnchant.getInstance().isAdvancedEnchantments() && AEAPI.isAnEnchantment(enchantID)) {
                    HyperAdvancedEnchantment hyperAdvancedEnchantment = new HyperAdvancedEnchantment(displayName, 1, useMoney, requiredMoney, enchantID, requiredLevel, requiredBook);
                    ad++;
                } else {
                    String key = Utils.getEnchantmentKey(XEnchantment.valueOf(enchantID).parseEnchantment());
                    assert descriptionYAML != null;
                    normalEnchantment = new NormalEnchantment(displayName, 1, useMoney, requiredMoney, enchantID, descriptionYAML, requiredLevel, requiredBook);
                    van++;
                    this.enchantments.put(key, normalEnchantment);
                    continue;
                }
                this.enchantments.put(enchantID.toLowerCase(), normalEnchantment);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + TBCEnchant.getInstance().getDescription().getName() + " &cError loading enchantment with ID " + enchantID + "!"));
            }
        }
        if ((TBCEnchant.getInstance().getConfiguration()).loadAllAdvancedEnchants)
            ad += AEUtils.loadAllAEEnchants();
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + TBCEnchant.getInstance().getDescription().getName() + " &6Successfully loaded " + van + " Vanilla Enchantments!"));
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + TBCEnchant.getInstance().getDescription().getName() + " &dSuccessfully loaded " + ad + " enchantments from AdvancedEnchantments!"));
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e" + TBCEnchant.getInstance().getDescription().getName() + " &bSuccessfully loaded " + eco + " enchantments from EcoEnchants!"));
    }

    public List<TBCEnchants> getAvailableEnchantments(ItemStack itemStack) {
        if (itemStack == null)
            return null;
        return (List<TBCEnchants>)this.enchantments.values().stream().filter(enchant -> enchant.itemCanBeEnchanted(itemStack)).collect(Collectors.toList());
    }

    public TBCEnchants getEnchantmentByID(String enchantmentID) {
        return this.enchantments.getOrDefault(enchantmentID, null);
    }

    public Map<TBCEnchants, Integer> getItemEnchantments(ItemStack itemStack) {
        Map<TBCEnchants, Integer> tbcenchants = new HashMap<>();
        for (TBCEnchants tbcenchant : this.enchantments.values()) {
            int level = tbcenchant.getItemLevel(itemStack);
            if (level > 0)
                tbcenchants.put(tbcenchant, Integer.valueOf(level));
        }
        return tbcenchants;
    }
}
