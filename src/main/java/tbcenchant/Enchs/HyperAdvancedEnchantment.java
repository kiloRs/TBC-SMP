package psikuvit.tbcenchant.Enchs;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.Utils.AEUtils;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.Map;

public class HyperAdvancedEnchantment extends TBCEnchants {
    public HyperAdvancedEnchantment(String displayName, int maxLevel, boolean useMoney, Map<Integer, Double> requiredMoney, String enchantmentName, Map<Integer, Integer> requiredLevel, int requiredBookShelf) {
        super(displayName,
                (AEUtils.instance(enchantmentName).getHighestLevel().intValue() < 0) ? maxLevel : AEUtils.instance(enchantmentName).getHighestLevel().intValue(), useMoney, requiredMoney, enchantmentName,
                Utils.translateDescription(AEUtils.instance(enchantmentName).getDescription()), requiredLevel, requiredBookShelf);
    }

    public int getMaxLevel() {
        if (this.maxLevel == 0)
            this.maxLevel = AEUtils.instance(this.enchantmentName).getHighestLevel().intValue();
        return this.maxLevel;
    }

    public boolean itemCanBeEnchanted(ItemStack itemStack) {
        return AEUtils.instance(this.enchantmentName).canBeApplied(itemStack.getType());
    }

    public boolean itemIsEnchanted(ItemStack itemStack, int level) {
        return (getItemLevel(itemStack) >= level);
    }

    public int getItemLevel(ItemStack itemStack) {
        return AEUtils.getItemLevel(this.enchantmentName, itemStack);
    }

    public boolean hasConflictWith(Enchantment enchantment) {
        return AEUtils.hasConflictWith(this.enchantmentName, enchantment);
    }
}
