package psikuvit.tbcenchant.Enchs;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.libs.XEnchantment;

import java.util.HashMap;
import java.util.Objects;

public class NormalEnchantment extends TBCEnchants {
    public NormalEnchantment(String displayName, int maxLevel, boolean useMoney, HashMap<Integer, Double> requiredMoney, String enchantmentName, String description, HashMap<Integer, Integer> requiredLevel, int requiredBookShelf) {
        super(displayName,
                (XEnchantment.valueOf(enchantmentName.toUpperCase()).parseEnchantment() == null) ? maxLevel : ((Enchantment) Objects.<Enchantment>requireNonNull(XEnchantment.valueOf(enchantmentName.toUpperCase()).parseEnchantment())).getMaxLevel(), useMoney, requiredMoney, enchantmentName,
                Utils.translateDescription(description), requiredLevel, requiredBookShelf);
    }

    public int getMaxLevel() {
        if (this.maxLevel == 0)
            setMaxLevel(getEnchantment().getMaxLevel());
        return this.maxLevel;
    }

    public boolean itemCanBeEnchanted(ItemStack itemStack) {
        if (getEnchantment() == null || itemStack == null)
            return false;
        return getEnchantment().canEnchantItem(itemStack);
    }

    public boolean itemIsEnchanted(ItemStack itemStack, int level) {
        return (itemStack.getEnchantmentLevel(getEnchantment()) >= level);
    }

    public int getItemLevel(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR)
            return 0;
        NBTItem nbtItem = new NBTItem(itemStack);
        if (nbtItem.hasKey("hp_" + this.enchantmentName).booleanValue())
            return nbtItem.getInteger("hp_" + this.enchantmentName).intValue();
        ItemMeta meta = itemStack.getItemMeta();
        int vanillaLevel = itemStack.getEnchantmentLevel(getEnchantment());
        if (meta instanceof EnchantmentStorageMeta)
            return ((EnchantmentStorageMeta)meta).getStoredEnchantLevel(getEnchantment());
        return vanillaLevel;
    }

    public boolean hasConflictWith(Enchantment enchantment) {
        Enchantment enchant = getEnchantment();
        try {
            return (enchant != enchantment && enchant.conflictsWith(enchantment));
        } catch (Exception e) {
            return false;
        }
    }
}
