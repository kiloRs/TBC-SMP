package psikuvit.tbcenchant.Utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.EnchantState;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Item;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.libs.XMaterial;
import psikuvit.tbcenchant.libs.XSound;
import psikuvit.tbcenchant.managers.GUIManager;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    private static final TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(Integer.valueOf(1000000), "m");
        map.put(Integer.valueOf(900000), "cm");
        map.put(Integer.valueOf(500000), "d");
        map.put(Integer.valueOf(100000), "c");
        map.put(Integer.valueOf(90000), "xc");
        map.put(Integer.valueOf(50000), "l");
        map.put(Integer.valueOf(10000), "x");
        map.put(Integer.valueOf(9000), "Mx");
        map.put(Integer.valueOf(5000), "v");
        map.put(Integer.valueOf(1000), "M");
        map.put(Integer.valueOf(900), "CM");
        map.put(Integer.valueOf(500), "D");
        map.put(Integer.valueOf(400), "CD");
        map.put(Integer.valueOf(100), "C");
        map.put(Integer.valueOf(90), "XC");
        map.put(Integer.valueOf(50), "L");
        map.put(Integer.valueOf(40), "XL");
        map.put(Integer.valueOf(10), "X");
        map.put(Integer.valueOf(9), "IX");
        map.put(Integer.valueOf(5), "V");
        map.put(Integer.valueOf(4), "IV");
        map.put(Integer.valueOf(1), "I");
        map.put(Integer.valueOf(0), "");
    }

    public static void playSound(Player player, String sound) {
        try {
            player.playSound(player.getLocation(), XSound.valueOf(sound).parseSound(), 1.0F, 1.0F);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EnchantState manageEnchant(GUIManager manager) {
        return (manager.getToEnchantItem().getAmount() > 1) ? EnchantState.REACH_STACK_SIZE : (
                (TBCEnchant.getInstance().getHyperEnchantments().getAvailableEnchantments(manager.getToEnchantItem()).size() > 0) ?
                        EnchantState.READY_TO_ENCHANT : EnchantState.NO_ENCHANTS_AVAILABLE);
    }

    public static Item getItemFromConfig(YamlConfiguration yamlConfig, String path) {
        Item item = new Item();
        if (yamlConfig.contains(path + ".material"))
            item.material = XMaterial.valueOf(yamlConfig.getString(path + ".material"));
        if (yamlConfig.contains(path + ".title"))
            item.title = yamlConfig.getString(path + ".title");
        if (yamlConfig.contains(path + ".lore"))
            item.lore = yamlConfig.getStringList(path + ".lore");
        if (yamlConfig.contains(path + ".slot"))
            item.slot = Integer.valueOf(yamlConfig.getInt(path + ".slot"));
        if (yamlConfig.contains(path + ".command"))
            item.command = yamlConfig.getString(path + ".command");
        if (yamlConfig.contains(path + ".headData"))
            item.headData = yamlConfig.getString(path + ".headData");
        if (yamlConfig.contains(path + ".amount"))
            item.amount = yamlConfig.getInt(path + ".amount");
        return item;
    }

    public static String getEnchantmentKey(Enchantment enchantment) {
        return (XMaterial.getVersion() > 13) ? enchantment.getKey().getKey() : enchantment.getName();
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String uncolor(String string) {
        return ChatColor.stripColor(string);
    }

    public static List<String> translateDescription(String description) {
        description = description.replaceAll("\\r|\\n", "%separator%");
        return Arrays.asList(description.split("%separator%"));
    }

    public static List<String> color(List<String> strings) {
        return (List<String>)strings.stream().map(Utils::color).collect(Collectors.toList());
    }

    public static List<Placeholder> getEnchantPlaceholders(TBCEnchants tbcenchant) {
        return new ArrayList<>(Arrays.asList(new Placeholder[] { new Placeholder("enchant_name", tbcenchant
                .getDisplayName()), new Placeholder("bookshelf_required",
                String.valueOf(tbcenchant.getRequiredBookShelf())) }));
    }

    public static HashMap<Integer, Double> deserializeRequiredMoney(List<String> map) {
        HashMap<Integer, Double> moneyMap = new HashMap<>();
        try {
            for (String money : map) {
                String[] moneyArray = money.split(":");
                moneyMap.put(Integer.valueOf(moneyArray[0]), Double.valueOf(moneyArray[1]));
            }
        } catch (Exception e) {
            return moneyMap;
        }
        return moneyMap;
    }

    public static HashMap<Integer, Integer> deserializeRequiredLevels(List<String> map) {
        HashMap<Integer, Integer> moneyMap = new HashMap<>();
        try {
            for (String money : map) {
                String[] moneyArray = money.split(":");
                moneyMap.put(Integer.valueOf(moneyArray[0]), Integer.valueOf(moneyArray[1]));
            }
        } catch (Exception e) {
            return moneyMap;
        }
        return moneyMap;
    }

    public static List<Placeholder> getMoneyPlaceholders(UUID player, TBCEnchants tbcenchant, int level, ItemStack itemStack) {
        Economy eco = TBCEnchant.getInstance().getEconomy();
        double currentMoney = eco.getBalance(Bukkit.getOfflinePlayer(player));
        double required = tbcenchant.getRequiredMoney(level).doubleValue();
        String requiredMessage = !tbcenchant.itemIsEnchanted(itemStack, level) ? ((currentMoney < required) ? TBCEnchant.getInstance().getMessages().getMessage("notEnoughMoney") : TBCEnchant.getInstance().getMessages().getMessage("clickToEnchantState")) : TBCEnchant.getInstance().getMessages().getMessage("alreadyState");
        return new ArrayList<>(Arrays.asList(new Placeholder[] { new Placeholder("enchant_name", tbcenchant.getDisplayName()), new Placeholder("enchant_level",
                toRoman(level)), new Placeholder("state", requiredMessage), new Placeholder("enchant_cost",

                color(TBCEnchant.getInstance().getMessages().getMessage("moneyCost").replace("%cost%", String.valueOf(required)))) }));
    }

    public static List<Placeholder> getEnchantPlaceholders(UUID uuid, TBCEnchants tbcenchant, int level, ItemStack itemStack) {
        Player player = Bukkit.getPlayer(uuid);
        int playerLevel = (player != null) ? player.getLevel() : 0;
        int required = tbcenchant.getRequiredLevel(level);
        int itemLevel = tbcenchant.getItemLevel(itemStack);
        int requiredLevel = !tbcenchant.itemIsEnchanted(itemStack, level) ? ((itemLevel > 0) ? getNewRequiredLevel(required) : required) : required;
        String requiredMessage = !tbcenchant.itemIsEnchanted(itemStack, level) ? ((playerLevel < requiredLevel) ? TBCEnchant.getInstance().getMessages().getMessage("notEnoughState") : TBCEnchant.getInstance().getMessages().getMessage("clickToEnchantState")) : TBCEnchant.getInstance().getMessages().getMessage("alreadyState");
        return new ArrayList<>(Arrays.asList(new Placeholder[] { new Placeholder("enchant_name", tbcenchant
                .getDisplayName()), new Placeholder("enchant_level",
                toRoman(level)), new Placeholder("state", requiredMessage), new Placeholder("enchant_cost",

                (requiredLevel == required) ? color(TBCEnchant.getInstance().getMessages().getMessage("normalCost").replace("%cost%", String.valueOf(required))) : color(TBCEnchant.getInstance().getMessages().getMessage("replacedCost").replace("%new_cost%", String.valueOf(requiredLevel)).replace("%cost%", String.valueOf(required)))) }));
    }

    public static int getNewRequiredLevel(int required) {
        return 93 * required / 100;
    }

    public static List<String> processMultiplePlaceholders(List<String> lines, List<Placeholder> placeholders) {
        List<String> newlist = new ArrayList<>();
        for (String string : lines)
            newlist.add(processMultiplePlaceholders(string, placeholders));
        return newlist;
    }

    public static String processMultiplePlaceholders(String line, List<Placeholder> placeholders) {
        for (Placeholder placeholder : placeholders)
            line = placeholder.process(line);
        return color(line);
    }

    public static int getBookShelfPower(Block block) {
        int quantity = 0;
        if (block == null)
            return quantity;
        for (Block nearBlock : getNearbyBlocks(block.getLocation(), 5)) {
            if (nearBlock.getType().equals(XMaterial.BOOKSHELF.parseMaterial()))
                quantity++;
        }
        return quantity;
    }

    public static int getBookShelfPower(Player player) {
        int quantity = 0;
        if (player == null)
            return quantity;
        for (Block nearBlock : getNearbyBlocks(player.getLocation(), 5)) {
            if (nearBlock.getType().equals(XMaterial.BOOKSHELF.parseMaterial()))
                quantity++;
        }
        return quantity;
    }

    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++)
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
            }
        }
        return blocks;
    }

    public static String toRoman(int number) {
        if (number >= 0) {
            int l = ((Integer)map.floorKey(Integer.valueOf(number))).intValue();
            if (number == l)
                return map.get(Integer.valueOf(number));
            return (String)map.get(Integer.valueOf(l)) + toRoman(number - l);
        }
        return String.valueOf(number);
    }
}
