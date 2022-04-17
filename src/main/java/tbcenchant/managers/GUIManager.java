package psikuvit.tbcenchant.managers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import psikuvit.tbcenchant.API.Events.Enchant;
import psikuvit.tbcenchant.EnchantState;
import psikuvit.tbcenchant.Enchs.HyperAdvancedEnchantment;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Item;
import psikuvit.tbcenchant.Object.EnchantObject;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.InventoryUtils;
import psikuvit.tbcenchant.Utils.Placeholder;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.*;

public class GUIManager {
    private final Inventory inventory;

    private ItemStack toEnchantItem;

    private EnchantState enchantState;

    private final TBCEnchant plugin;

    private final Map<Integer, EnchantObject> enchantsMap;

    private final int bokShelfPower;

    private int booksPage;

    private final BukkitScheduler tasks;

    public ItemStack getToEnchantItem() {
        return this.toEnchantItem;
    }

    public void setToEnchantItem(ItemStack toEnchantItem) {
        this.toEnchantItem = toEnchantItem;
    }

    public Map<Integer, EnchantObject> getEnchantsMap() {
        return this.enchantsMap;
    }

    public int getBokShelfPower() {
        return this.bokShelfPower;
    }

    public int getBooksPage() {
        return this.booksPage;
    }

    public void setBooksPage(int booksPage) {
        this.booksPage = booksPage;
    }


    public GUIManager(Inventory inventory, int bokShelfPower) {
        this.inventory = inventory;
        this.plugin = TBCEnchant.getInstance();
        this.enchantsMap = new HashMap<>();
        this.booksPage = 1;
        this.tasks = Bukkit.getScheduler();
        this.bokShelfPower = bokShelfPower;
    }

    public void checkEnchantmentTable() {
        clearFirstPage();
        setupFirstPageItems();
        this.tasks.runTask((Plugin)this.plugin, () -> {
            this.toEnchantItem = this.inventory.getItem(19);
            if (this.toEnchantItem != null && !this.toEnchantItem.getType().equals(Material.AIR)) {
                this.enchantState = Utils.manageEnchant(this);
                clearFirstPage();
                if (this.enchantState == EnchantState.READY_TO_ENCHANT) {
                    setFirstPageBooks();
                } else {
                    this.tasks.runTask((Plugin)this.plugin, () -> {
                });
                }
            } else {
                clearFirstPage();
                setupFirstPageItems();
            }
        });
    }

    private void setupFirstPageItems() {
        setItemSync(23, (this.plugin.getInventories()).emptyItem);
        setItemSync((this.plugin.getInventories()).bookShelfItem.slot.intValue(), (this.plugin.getInventories()).bookShelfItem, Collections.singletonList(new Placeholder("bookshelf_power", String.valueOf(this.bokShelfPower))));
        setItemSync((this.plugin.getInventories()).enchantingTable.slot.intValue(), (this.plugin.getInventories()).enchantingTable, Collections.singletonList(new Placeholder("bookshelf_power", String.valueOf(this.bokShelfPower))));
    }

    private void setFirstPageBooks() {
        List<TBCEnchants> tbcenchants = this.plugin.getHyperEnchantments().getAvailableEnchantments(this.toEnchantItem);
        int slotsSize = (this.plugin.getInventories()).bookSlots.size();
        int slot = 0;
        int i = slotsSize * (this.booksPage - 1);
        if (tbcenchants.size() > 0)
            while (slot < slotsSize) {
                if (tbcenchants.size() > i && i >= 0) {
                    TBCEnchants tbcenchant = tbcenchants.get(i);
                    int finalSlot = ((Integer)(this.plugin.getInventories()).bookSlots.get(slot)).intValue();
                    if (this.bokShelfPower >= tbcenchant.getRequiredBookShelf()) {
                        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem(finalSlot, InventoryUtils.makeItemHidden((this.plugin.getInventories()).availableBook, Utils.getEnchantPlaceholders(tbcenchant), tbcenchant)));
                    } else {
                        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem(finalSlot, InventoryUtils.makeItemHidden((this.plugin.getInventories()).notAvailableBook, Utils.getEnchantPlaceholders(tbcenchant), tbcenchant)));
                    }
                    this.enchantsMap.put(Integer.valueOf(finalSlot), new EnchantObject(tbcenchant));
                    slot++;
                    i++;
                    continue;
                }
                slot++;
            }
        if (this.booksPage > 1)
            setItemSync((this.plugin.getInventories()).backPageButton.slot.intValue(), (this.plugin.getInventories()).backPageButton, Collections.singletonList(new Placeholder("previous_page", String.valueOf(this.booksPage - 1))));
        if (tbcenchants.size() > slotsSize * this.booksPage)
            setItemSync((this.plugin.getInventories()).nextPageButton.slot.intValue(), (this.plugin.getInventories()).nextPageButton, Collections.singletonList(new Placeholder("next_page", String.valueOf(this.booksPage + 1))));
    }

    private void clearFirstPage() {
        ItemStack background = InventoryUtils.makeItemHidden((this.plugin.getInventories()).background);
        this.enchantsMap.forEach((slot, enchant) -> this.tasks.runTask((Plugin)this.plugin, () -> {

        }));
        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem((this.plugin.getInventories()).backPageButton.slot.intValue(), background));
        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem((this.plugin.getInventories()).nextPageButton.slot.intValue(), background));
        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem(23, background));
        this.enchantsMap.clear();
    }

    public ItemStack enchantItem(Player player, ItemStack itemStack, EnchantObject enchantObject) {
        if (enchantObject == null)
            return null;
        TBCEnchants tbcenchant = enchantObject.getTBCEnchant();
        Optional<Enchantment> enchantment = tbcenchant.hasEnchantmentConflicts(itemStack);
        if (!(TBCEnchant.getInstance().getConfiguration()).byPassEnchantmentRestrictions &&
                enchantment.isPresent() && !(this.plugin.getConfiguration()).removeIncompatibleEnchants) {
            player.sendMessage(Utils.color(this.plugin.getMessages().getMessage("incompatibleEnchantment").replace("%prefix%", (this.plugin.getConfiguration()).prefix)));
            Utils.playSound(player, "ENTITY_VILLAGER_NO");
            return null;
        }
        int level = enchantObject.getLevel();
        if (!tbcenchant.isUseMoney()) {
            boolean isEnchanted = tbcenchant.itemIsEnchanted(itemStack, level);
            int required = tbcenchant.getRequiredLevel(level);
            int itemLevel = tbcenchant.getItemLevel(itemStack);
            required = !isEnchanted ? ((itemLevel > 0) ? Utils.getNewRequiredLevel(required) : required) : required;
            if (player.getLevel() >= required) {
                if (level > itemLevel) {
                    itemStack = this.plugin.getEnchantsHandler().addEnchantment(itemStack, level, false, tbcenchant);
                    player.setLevel(player.getLevel() - required);
                    Utils.playSound(player, "ENTITY_EXPERIENCE_ORB_PICKUP");
                    Bukkit.getServer().getPluginManager().callEvent((Event)new Enchant(player, required, 0.0D, tbcenchant, level, itemStack, tbcenchant instanceof HyperAdvancedEnchantment));
                    return itemStack;
                }
                player.sendMessage(Utils.color(this.plugin.getMessages().getMessage("alreadyState").replace("%prefix%", (this.plugin.getConfiguration()).prefix)));
                Utils.playSound(player, "ENTITY_VILLAGER_NO");
            } else {
                player.sendMessage(Utils.color(this.plugin.getMessages().getMessage("notEnoughState").replace("%prefix%", (this.plugin.getConfiguration()).prefix)));
                Utils.playSound(player, "ENTITY_VILLAGER_NO");
            }
        } else {
            try {
                Economy eco = TBCEnchant.getInstance().getEconomy();
                double currentMoney = eco.getBalance((OfflinePlayer)player);
                double requiredMoney = tbcenchant.getRequiredMoney(level).doubleValue();
                if (currentMoney >= requiredMoney) {
                    itemStack = this.plugin.getEnchantsHandler().addEnchantment(itemStack, level, false, tbcenchant);
                    eco.withdrawPlayer((OfflinePlayer)player, requiredMoney);
                    Utils.playSound(player, "ENTITY_EXPERIENCE_ORB_PICKUP");
                    Bukkit.getServer().getPluginManager().callEvent((Event)new Enchant(player, 0, requiredMoney, tbcenchant, level, itemStack, tbcenchant instanceof HyperAdvancedEnchantment));
                    return itemStack;
                }
                player.sendMessage(Utils.color(this.plugin.getMessages().getMessage("notEnoughMoney").replace("%prefix%", (this.plugin.getConfiguration()).prefix)));
                Utils.playSound(player, "ENTITY_VILLAGER_NO");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(Utils.color("&c[tbcenchantments] Economy Plugin wasn't found"));
            }
        }
        return null;
    }

    private void setItemSync(int slot, Item item, List<Placeholder> placeholders) {
        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem(slot, InventoryUtils.makeItem(item, placeholders)));
    }

    private void setItemSync(int slot, Item item) {
        this.tasks.runTask((Plugin)this.plugin, () -> this.inventory.setItem(slot, InventoryUtils.makeItem(item)));
    }
}
