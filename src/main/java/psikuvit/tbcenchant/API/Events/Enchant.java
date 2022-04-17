package psikuvit.tbcenchant.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import psikuvit.tbcenchant.Enchs.TBCEnchants;

public class Enchant extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final int levelCost;

    private final double moneyCost;

    private final TBCEnchants enchant;

    private final int enchantLevel;

    private final ItemStack item;

    private final boolean isAdvancedEnchantment;

    public int getLevelCost() {
        return this.levelCost;
    }

    public double getMoneyCost() {
        return this.moneyCost;
    }

    public TBCEnchants getEnchant() {
        return this.enchant;
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public boolean isAdvancedEnchantment() {
        return this.isAdvancedEnchantment;
    }

    public Enchant( Player player, int levelCost, double moneyCost, TBCEnchants enchant, int enchantLevel, ItemStack item, boolean isAdvancedEnchantment) {
        super(player);
        this.levelCost = levelCost;
        this.moneyCost = moneyCost;
        this.enchant = enchant;
        this.enchantLevel = enchantLevel;
        this.item = item;
        this.isAdvancedEnchantment = isAdvancedEnchantment;
    }

    
    public final HandlerList getHandlers() {
        return handlers;
    }

    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
