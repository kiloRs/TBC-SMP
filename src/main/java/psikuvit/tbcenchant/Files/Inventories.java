package psikuvit.tbcenchant.Files;

import org.bukkit.configuration.ConfigurationSection;
import psikuvit.tbcenchant.Item;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.libs.XMaterial;

import java.util.*;

public class Inventories extends YAMLFile {
    public String mainMenuTitle = "&8Main Menu";

    public int mainMenuSize = 54;

    public Item enchantingTable = new Item(XMaterial.ENCHANTING_TABLE, 28, 1, "&aEnchant Item", Arrays.asList(new String[] { "&7Add an item to the slot above to", "&7view enchantment options" }));

    public Item emptyItem = new Item(XMaterial.GRAY_DYE, 1, "&cEnchant Item", Arrays.asList(new String[] { "&7Place an item in the open slot", "&7to enchant it" }));

    public Item errorItem = new Item(XMaterial.POPPY, 1, "&cCannot Enchant Item!", Collections.singletonList("%error_placeholder%"));

    public Item bookShelfItem = new Item(XMaterial.BOOKSHELF, 48, 1, "&dBookshelf Power",
            Arrays.asList(new String[] { "&7Stronger enchantments require", "&7more bookshelf power which can", "&7be increased by placing", "&7bookshelves nearby.", "", "&7Current Bookshelf Power: &d%bookshelf_power%" }));

    public Item availableBook = new Item(XMaterial.ENCHANTED_BOOK, 1, "&a%enchant_name%", Arrays.asList(new String[] { "&7%enchant_description%", "", "&eClick to view!" }));

    public Item notAvailableBook = new Item(XMaterial.ENCHANTED_BOOK, 1, "&c%enchant_name%", Arrays.asList(new String[] { "&7%enchant_description%", "", "&cRequires %bookshelf_required% power!" }));

    public List<Integer> bookSlots = Arrays.asList(new Integer[] {
            Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25),
            Integer.valueOf(30), Integer.valueOf(31), Integer.valueOf(32), Integer.valueOf(33), Integer.valueOf(34) });

    public Item levelEnchantBook = new Item(XMaterial.ENCHANTED_BOOK, 1, "&fEnchanted Book", Arrays.asList(new String[] { "&9%enchant_name% %enchant_level%", "&7%enchant_description%", "", "&7Cost: &3%enchant_cost%", "", "%state%" }));

    public Item moneyEnchantBook = new Item(XMaterial.ENCHANTED_BOOK, 1, "&fEnchanted Book", Arrays.asList(new String[] { "&9%enchant_name% %enchant_level%", "&7%enchant_description%", "", "&7Cost: &3%enchant_cost%$", "", "%state%" }));

    public Map<Integer, List<Integer>> levelSlots = new HashMap<Integer, List<Integer>>() {

    };

    public Item backPageButton = new Item(XMaterial.PLAYER_HEAD, 17, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRhNTY2N2VmNzI4NWM5MjI1ZmMyNjdkNDUxMTdlYWI1NDc4Yzc4NmJkNWFmMGExOTljMjlhMmMxNGMxZiJ9fX0=", 1, "&aBack Page", Collections.singletonList("Page %previous_page%"));

    public Item nextPageButton = new Item(XMaterial.PLAYER_HEAD, 35, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDFiNjJkYjVjMGEzZmExZWY0NDFiZjcwNDRmNTExYmU1OGJlZGY5YjY3MzE4NTNlNTBjZTkwY2Q0NGZiNjkifX19", 1, "&aNext Page", Collections.singletonList("Page %next_page%"));

    public Item previousPage = new Item(XMaterial.ARROW, 48, 1, "&ePrevious Page", new ArrayList());

    public Item background = new Item(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", new ArrayList());

    public Item closeButton = new Item(XMaterial.BARRIER, 49, 1, "&cClose Menu", new ArrayList());

    public Inventories(TBCEnchant TBCEnchant, String name) {
        super(TBCEnchant, name);
    }

    public void enable() {
        super.enable();
        loadItems();
    }

    public void reload() {
        super.reload();
        loadItems();
    }

    private void loadItems() {
        this.mainMenuTitle = getConfig().get().getString("mainMenu.title");
        this.mainMenuSize = getConfig().get().getInt("mainMenu.size");
        this.enchantingTable = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.enchantingTable");
        this.enchantingTable = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.enchantingTable");
        this.emptyItem = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.emptyItem");
        this.errorItem = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.errorItem");
        this.bookShelfItem = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.bookShelfItem");
        this.availableBook = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.availableBook");
        this.notAvailableBook = Utils.getItemFromConfig(getConfig().get(), "mainMenu.items.notAvailableBook");
        this.levelEnchantBook = Utils.getItemFromConfig(getConfig().get(), "subMenu.items.levelEnchantBook");
        this.moneyEnchantBook = Utils.getItemFromConfig(getConfig().get(), "subMenu.items.moneyEnchantBook");
        this.bookSlots = getConfig().get().getIntegerList("mainMenu.bookSlots");
        this.background = Utils.getItemFromConfig(getConfig().get(), "buttons.background");
        this.previousPage = Utils.getItemFromConfig(getConfig().get(), "buttons.previousPage");
        this.closeButton = Utils.getItemFromConfig(getConfig().get(), "buttons.closeButton");
        this.backPageButton = Utils.getItemFromConfig(getConfig().get(), "buttons.backPageButton");
        this.nextPageButton = Utils.getItemFromConfig(getConfig().get(), "buttons.nextPageButton");
        ConfigurationSection section = getConfig().get().getConfigurationSection("subMenu.levelSlots");
        if (section == null)
            return;
        for (String key : section.getKeys(false))
            this.levelSlots.put(Integer.valueOf(key), getConfig().get().getIntegerList("subMenu.levelSlots." + key));
    }
}
