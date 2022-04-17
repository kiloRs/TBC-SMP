package psikuvit.tbcenchant.Utils;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import psikuvit.tbcenchant.EnchantState;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Item;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.libs.XMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InventoryUtils {
    public static ItemStack makeItemHidden(Item item, List<Placeholder> placeholders, TBCEnchants tbcenchant, ItemStack itemStack) {
        try {
            Optional<Enchantment> conflict = tbcenchant.hasEnchantmentConflicts(itemStack);
            String key = conflict.<String>map(Utils::getEnchantmentKey).orElse(null);
            List<String> lore = new ArrayList<>();
            for (String line : item.lore) {
                if (line.contains("%enchant_incompatibility%")) {
                    if (key != null && (TBCEnchant.getInstance().getConfiguration()).removeIncompatibleEnchants) {
                        TBCEnchants conflictEnchant = TBCEnchant.getInstance().getHyperEnchantments().getEnchantmentByID(key);
                        TBCEnchant.getInstance().getMessages().getIncompatibilityError().forEach(subLine -> lore.add(subLine.replace("%incompatible_enchant%", conflictEnchant.getDisplayName())));
                    }
                    continue;
                }
                if (line.contains("%enchant_description%")) {
                    tbcenchant.getDescription().forEach(desLine -> lore.add(Utils.color(line.replace("%enchant_description%", desLine))));
                    continue;
                }
                lore.add(line);
            }
            ItemStack itemstack = makeItemHidden(item.material, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(lore, placeholders)));
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            e.printStackTrace();
            return makeItemHidden(XMaterial.STONE, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(item.lore, placeholders)));
        }
    }

    public static ItemStack makeItemHidden(Item item, List<Placeholder> placeholders) {
        try {
            ItemStack itemstack = makeItemHidden(item.material, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(item.lore, placeholders)));
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            e.printStackTrace();
            return makeItemHidden(XMaterial.STONE, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(item.lore, placeholders)));
        }
    }

    public static ItemStack makeItemHidden(Item item, List<Placeholder> placeholders, TBCEnchants tbcenchant) {
        try {
            List<String> lore = new ArrayList<>();
            for (String line : item.lore) {
                if (line.contains("%enchant_description%")) {
                    tbcenchant.getDescription().forEach(desLine -> lore.add(Utils.color(line.replace("%enchant_description%", desLine))));
                    continue;
                }
                lore.add(line);
            }
            ItemStack itemstack = makeItemHidden(item.material, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(lore, placeholders)));
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            e.printStackTrace();
            return makeItemHidden(XMaterial.STONE, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.color(Utils.processMultiplePlaceholders(item.lore, placeholders)));
        }
    }

    public static ItemStack makeItemHidden(XMaterial material, int amount, String name, List<String> lore) {
        ItemStack item = material.parseItem();
        if (item == null)
            return null;
        item.setAmount(amount);
        ItemMeta m = item.getItemMeta();
        m.setLore(Utils.color(lore));
        m.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE });
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
    }

    public static ItemStack makeItemHidden(Item item, EnchantState enchantState) {
        try {
            List<String> lore = new ArrayList<>();
            if (enchantState != null)
                for (String line : item.lore) {
                    if (line.contains("%error_placeholder%")) {
                        lore.add(enchantState.error);
                        continue;
                    }
                    lore.add(line);
                }
            ItemStack itemstack = makeItemHidden(item.material, item.amount, item.title, lore);
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            e.printStackTrace();
            return makeItemHidden(XMaterial.STONE, item.amount, item.title, item.lore);
        }
    }

    public static ItemStack makeItemHidden(Item item) {
        try {
            ItemStack itemstack = makeItemHidden(item.material, item.amount, item.title, item.lore);
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            e.printStackTrace();
            return makeItemHidden(XMaterial.STONE, item.amount, item.title, item.lore);
        }
    }

    public static ItemStack makeItem(XMaterial material, int amount, String name, List<String> lore) {
        ItemStack item = material.parseItem();
        if (item == null)
            return null;
        item.setAmount(amount);
        ItemMeta m = item.getItemMeta();
        m.setLore(Utils.color(lore));
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return item;
    }

    public static ItemStack makeItem(Item item) {
        try {
            ItemStack itemstack = makeItem(item.material, item.amount, item.title, item.lore);
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(item.headOwner);
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            return makeItem(XMaterial.STONE, item.amount, item.title, item.lore);
        }
    }

    public static ItemStack makeItem(Item item, List<Placeholder> placeholders) {
        try {
            ItemStack itemstack = makeItem(item.material, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.processMultiplePlaceholders(item.lore, placeholders));
            if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
                NBTItem nbtItem = new NBTItem(itemstack);
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                skull.setString("Name", "tr7zw");
                skull.setString("Id", UUID.randomUUID().toString());
                NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                texture.setString("Value", item.headData);
                return nbtItem.getItem();
            }
            if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
                SkullMeta m = (SkullMeta)itemstack.getItemMeta();
                m.setOwner(Utils.processMultiplePlaceholders(item.headOwner, placeholders));
                itemstack.setItemMeta((ItemMeta)m);
            }
            return itemstack;
        } catch (Exception e) {
            return makeItem(XMaterial.STONE, item.amount, Utils.processMultiplePlaceholders(item.title, placeholders), Utils.processMultiplePlaceholders(item.lore, placeholders));
        }
    }
}
