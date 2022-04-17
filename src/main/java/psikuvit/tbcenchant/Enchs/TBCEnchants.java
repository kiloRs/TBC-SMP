package psikuvit.tbcenchant.Enchs;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.libs.XEnchantment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class TBCEnchants {
    protected final String displayName;

    protected transient int maxLevel;

    protected final boolean useMoney;

    protected final Map<Integer, Double> requiredMoney;

    protected final String enchantmentName;

    protected final List<String> description;

    protected final Map<Integer, Integer> requiredLevel;

    protected final int requiredBookShelf;

    public TBCEnchants(String displayName, int maxLevel, boolean useMoney, Map<Integer, Double> requiredMoney, String enchantmentName, List<String> description, Map<Integer, Integer> requiredLevel, int requiredBookShelf) {
        this.displayName = displayName;
        this.maxLevel = maxLevel;
        this.useMoney = useMoney;
        this.requiredMoney = requiredMoney;
        this.enchantmentName = enchantmentName;
        this.description = description;
        this.requiredLevel = requiredLevel;
        this.requiredBookShelf = requiredBookShelf;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public boolean isUseMoney() {
        return this.useMoney;
    }

    public Map<Integer, Double> getRequiredMoney() {
        return this.requiredMoney;
    }

    public String getEnchantmentName() {
        return this.enchantmentName;
    }

    public List<String> getDescription() {
        return this.description;
    }

    public Map<Integer, Integer> getRequiredLevel() {
        return this.requiredLevel;
    }

    public int getRequiredBookShelf() {
        return this.requiredBookShelf;
    }

    public int getRequiredLevel(int level) {
        return ((Integer)this.requiredLevel.getOrDefault(Integer.valueOf(level), Integer.valueOf(level * 5))).intValue();
    }

    public Double getRequiredMoney(int level) {
        return this.requiredMoney.getOrDefault(Integer.valueOf(level), Double.valueOf(level * 100.0D));
    }

    public boolean hasEnchantmentByReforge(ItemStack itemStack) {
        if (itemStack == null)
            return false;
        return (new NBTItem(itemStack)).hasKey("rf_" + getEnchantmentName()).booleanValue();
    }

    public Optional<Enchantment> hasEnchantmentConflicts(ItemStack itemStack) {
        return TBCEnchant.getInstance().getApi().getItemEnchantments(itemStack).keySet()
                .stream()
                .filter(tbcenchant -> !(tbcenchant instanceof HyperAdvancedEnchantment))
                .map(TBCEnchants::getEnchantment)
                .filter(this::hasConflictWith).findFirst();
    }

    public ItemStack getEnchantedItem(ItemStack itemStack, int level, boolean reforged) {
        return TBCEnchant.getInstance().getEnchantsHandler().addEnchantment(itemStack, level, reforged, this);
    }

    public ItemStack removeEnchant(ItemStack itemStack) {
        return TBCEnchant.getInstance().getEnchantsHandler().removeEnchantment(itemStack, this);
    }

    public Enchantment getEnchantment() {
        return XEnchantment.valueOf(this.enchantmentName.toUpperCase()).parseEnchantment();
    }

    public abstract boolean itemCanBeEnchanted(ItemStack paramItemStack);

    public abstract boolean itemIsEnchanted(ItemStack paramItemStack, int paramInt);

    public abstract int getItemLevel(ItemStack paramItemStack);

    public abstract boolean hasConflictWith(Enchantment paramEnchantment);
}
