package psikuvit.tbcenchant.API;

import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.TBCEnchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TBCEnchantAPIIIMP implements TBCEnchantAPI {
    private final TBCEnchant plugin;

    public TBCEnchantAPIIIMP(TBCEnchant plugin) {
        this.plugin = plugin;
    }

    public ItemStack enchantItem(ItemStack itemStack, TBCEnchants tbcenchant, int level, boolean reforged) {
        return tbcenchant.getEnchantedItem(itemStack, level, reforged);
    }

    public ItemStack removeEnchant(ItemStack itemStack, TBCEnchants tbcenchant) {
        return tbcenchant.removeEnchant(itemStack);
    }

    public boolean hasReforgedEnchantment(ItemStack itemStack, TBCEnchants tbcenchant) {
        return tbcenchant.hasEnchantmentByReforge(itemStack);
    }

    public TBCEnchants getEnchantmentInstance(String enchantmentID) {
        return this.plugin.getHyperEnchantments().getEnchantmentByID(enchantmentID);
    }

    public boolean itemCanBeEnchanted(ItemStack itemStack, TBCEnchants tbcenchant) {
        return tbcenchant.itemCanBeEnchanted(itemStack);
    }

    public boolean itemIsEnchanted(ItemStack itemStack, TBCEnchants tbcenchant, int level) {
        return tbcenchant.itemIsEnchanted(itemStack, level);
    }

    public List<TBCEnchants> getAvailableEnchantments(ItemStack itemStack) {
        return this.plugin.getHyperEnchantments().getAvailableEnchantments(itemStack);
    }

    public Map<TBCEnchants, Integer> getItemEnchantments(ItemStack itemStack) {
        return this.plugin.getHyperEnchantments().getItemEnchantments(itemStack);
    }

    public List<TBCEnchants> getAllEnchantments() {
        return new ArrayList<>((this.plugin.getHyperEnchantments()).enchantments.values());
    }

    public ItemStack getEnchantedBook(TBCEnchants tbcenchant, int level) {
        return this.plugin.getEnchantsHandler().getEnchantedBook(tbcenchant, level);
    }
}
