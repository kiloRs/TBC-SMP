package psikuvit.tbcenchant;

import com.google.common.collect.Lists;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Object.AdvancedSettings;
import psikuvit.tbcenchant.Utils.InventoryUtils;
import psikuvit.tbcenchant.Utils.Placeholder;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.libs.XMaterial;

import java.util.*;
import java.util.stream.Collectors;

public class EnchantsHandler {
    private final TBCEnchant plugin;

    public EnchantsHandler(TBCEnchant plugin) {
        this.plugin = plugin;
    }

    private ItemStack convertToUltimateBook(ItemStack itemStack, TBCEnchants tbcenchant, int level, Map<TBCEnchants, Integer> enchantments) {
        int cost = calculateCost(enchantments);
        ItemStack ultimateBook = InventoryUtils.makeItem((this.plugin.getConfiguration()).bookItem, Collections.singletonList(new Placeholder("apply_cost", this.plugin
                .getMessages().getMessage("levelCostLine").replace("%cost_level%", String.valueOf(cost)))));
        ItemMeta ultimateMeta = ultimateBook.getItemMeta();
        itemStack.setType(ultimateBook.getType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ultimateMeta.getDisplayName());
        meta.setLore(ultimateMeta.getLore());
        itemStack.setItemMeta(meta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("applyCost", Integer.valueOf(cost));
        nbtItem.setInteger("ue_enchant-" + tbcenchant.getEnchantmentName(), Integer.valueOf(level));
        return nbtItem.getItem();
    }

    public int calculateCost(Map<TBCEnchants, Integer> enchantments) {
        int finalCost = 0;
        for (TBCEnchants tbcenchant : enchantments.keySet())
            finalCost += tbcenchant.getRequiredLevel(((Integer)enchantments.get(tbcenchant)).intValue());
        return finalCost;
    }

    public ItemStack getEnchantedBook(TBCEnchants tbcenchant, int level) {
        return this.plugin.getApi().enchantItem(XMaterial.BOOK.parseItem(), tbcenchant, level, false);
    }

    public ItemStack addEnchantment(ItemStack itemStack, int level, boolean reforged, TBCEnchants tbcenchant) {
        String enchantmentName = tbcenchant.getEnchantmentName();
        Map<TBCEnchants, Integer> enchantments = this.plugin.getApi().getItemEnchantments(itemStack);
        enchantments.put(tbcenchant, Integer.valueOf(level));
        if (itemStack.getType().toString().contains("BOOK"))
            itemStack = convertToUltimateBook(itemStack, tbcenchant, level, enchantments);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setInteger("hp_" + enchantmentName, Integer.valueOf(level));
        if (reforged)
            nbtItem.setInteger("rf_" + enchantmentName, Integer.valueOf(level));
        itemStack = nbtItem.getItem();
        itemStack = addEnchant(itemStack, tbcenchant, level).clone();
        itemStack = checkItemFlags(itemStack).clone();
        itemStack = removeLore(itemStack, enchantments).clone();
        itemStack = updateLore(itemStack, enchantments);
        TBCEnchants incompatible = getIncompatible(itemStack, tbcenchant);
        if (incompatible != null)
            itemStack = removeEnchantment(itemStack, incompatible).clone();
        return itemStack;
    }

    private ItemStack updateLore(ItemStack itemStack, Map<TBCEnchants, Integer> enchantments) {
        if (enchantments.size() >= (this.plugin.getConfiguration()).advancedSettings.getLimit()) {
            itemStack = addAdvancedLore(itemStack, enchantments).clone();
        } else {
            itemStack = addLore(itemStack, enchantments).clone();
        }
        return itemStack;
    }

    public ItemStack removeEnchantment(ItemStack itemStack, TBCEnchants tbcenchant) {
        Map<TBCEnchants, Integer> enchantments = this.plugin.getApi().getItemEnchantments(itemStack);
        itemStack = removeLore(itemStack, enchantments).clone();
        enchantments.remove(tbcenchant);
        String enchantmentName = tbcenchant.getEnchantmentName();
        itemStack = updateLore(itemStack, enchantments).clone();
        itemStack = removeEnchant(itemStack, tbcenchant).clone();
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.removeKey("hp_" + enchantmentName);
        nbtItem.removeKey("rf_" + enchantmentName);
        return nbtItem.getItem();
    }

    private ItemStack addLore(ItemStack itemStack, Map<TBCEnchants, Integer> enchantments) {
        List<String> newLore = null;
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = (meta != null && meta.hasLore()) ? itemStack.getItemMeta().getLore() : Collections.<String>emptyList();
        if ((this.plugin.getConfiguration()).addInfoOnEnchant)
            newLore = getNormalLore(enchantments);
        if (lore != null && meta != null)
            meta.setLore((this.plugin.getConfiguration()).loreSettings.getToAddLore(lore, newLore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack addAdvancedLore(ItemStack itemStack, Map<TBCEnchants, Integer> enchantments) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = (meta != null && meta.hasLore()) ? itemStack.getItemMeta().getLore() : Collections.<String>emptyList();
        if ((this.plugin.getConfiguration()).addInfoOnEnchant && meta != null) {
            meta.setLore((this.plugin.getConfiguration()).loreSettings.getToAddLore(lore, getShrinkLore(enchantments)));
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    private ItemStack removeLore(ItemStack itemStack, Map<TBCEnchants, Integer> enchantments) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = (meta != null && meta.hasLore()) ? itemStack.getItemMeta().getLore() : Collections.<String>emptyList();
        List<String> newLore = new ArrayList<>();
        if (lore != null && meta != null) {
            for (String line : lore) {
                boolean add = true;
                for (TBCEnchants tbcenchant : enchantments.keySet()) {
                    String unLine = Utils.uncolor(line);
                    String unColorName = Utils.uncolor(Utils.color(tbcenchant.getDisplayName()));
                    if (unLine.contains(unColorName) || unLine.contains(tbcenchant.getEnchantmentName())) {
                        add = false;
                        continue;
                    }
                    for (String descriptionLine : tbcenchant.getDescription()) {
                        String uncolorDesc = Utils.uncolor(descriptionLine);
                        if (unLine.contains(uncolorDesc))
                            add = false;
                    }
                }
                if (add)
                    newLore.add(Utils.color(line));
            }
            meta.setLore(newLore);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack addEnchant(ItemStack itemStack, TBCEnchants tbcenchant, int level) {
        Enchantment enchantment = tbcenchant.getEnchantment();
        ItemMeta meta = itemStack.getItemMeta();
        if (enchantment != null)
            if (meta instanceof EnchantmentStorageMeta) {
                ((EnchantmentStorageMeta)meta).addStoredEnchant(enchantment, level, true);
            } else {
                meta.addEnchant(enchantment, level, false);
            }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack removeEnchant(ItemStack itemStack, TBCEnchants tbcenchant) {
        ItemMeta meta = itemStack.getItemMeta();
        Enchantment enchant = tbcenchant.getEnchantment();
        if (meta instanceof EnchantmentStorageMeta) {
            ((EnchantmentStorageMeta)meta).removeStoredEnchant(enchant);
        } else {
            meta.removeEnchant(enchant);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private TBCEnchants getIncompatible(ItemStack itemStack, TBCEnchants tbcenchant) {
        if ((this.plugin.getConfiguration()).removeIncompatibleEnchants) {
            Optional<Enchantment> conflict = tbcenchant.hasEnchantmentConflicts(itemStack);
            String key = conflict.<String>map(Utils::getEnchantmentKey).orElse(null);
            return this.plugin.getHyperEnchantments().getEnchantmentByID(key);
        }
        return null;
    }

    private ItemStack checkItemFlags(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if ((this.plugin.getConfiguration()).hideOriginalEnchant) {
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        } else {
            meta.removeItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private List<String> getShrinkLore(Map<TBCEnchants, Integer> enchantments) {
        AdvancedSettings settings = (this.plugin.getConfiguration()).advancedSettings;
        List<String> newLore = new ArrayList<>();
        if (enchantments.size() >= settings.getPerLine()) {
            List<String> enchantNames = (List<String>)enchantments.keySet().stream().map(enchant -> Utils.color(settings.getLine().replace("%enchant_level%", Utils.toRoman(((Integer)enchantments.get(enchant)).intValue())).replace("%enchant_name%", ChatColor.stripColor(enchant.getDisplayName())))).sorted().distinct().collect(Collectors.toList());
            List<List<String>> partitionedCombinedLoreList = Lists.partition(enchantNames, settings.getPerLine());
            partitionedCombinedLoreList.forEach(list -> {
                StringBuilder builder = new StringBuilder();
                for (String s : list) {
                    builder.append(s);
                    builder.append(", ");
                }
                String line = builder.toString();
                line = line.substring(0, line.length() - 2);
                newLore.add(line);
            });
        }
        return newLore;
    }

    private List<String> getNormalLore(Map<TBCEnchants, Integer> enchantments) {
        List<String> newLore = new ArrayList<>();
        for (Iterator<TBCEnchants> iterator = enchantments.keySet().iterator(); iterator.hasNext(); ) {
            TBCEnchants tbcenchant = iterator.next();
            (this.plugin.getConfiguration()).infoToEnchantedItem.forEach(line -> {
                if (line.contains("%enchant_description%")) {
                    tbcenchant.getDescription();
                } else {
                    newLore.add(Utils.color(line.replace("%enchant_level%", Utils.toRoman(((Integer)enchantments.get(tbcenchant)).intValue())).replace("%enchant_name%", tbcenchant.getDisplayName())));
                }
            });
        }
        return newLore;
    }
}
