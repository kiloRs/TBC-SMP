package com.thebetterchoiceminecraft.play.enchanting.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Page extends PaginatedMenu {
    private List<ItemStack> placeHolderBooks;

    private List<ItemStack> filteredBooks;

    private ItemStack currentItem;

    public Page(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        List<ItemStack> placeHolderBooks = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            placeHolderBooks.add(makeItem(Material.RED_STAINED_GLASS_PANE, " ", new String[0]));
        this.placeHolderBooks = new ArrayList<>(placeHolderBooks);
        this.filteredBooks = new ArrayList<>(placeHolderBooks);
    }

    public String getMenuName() {
        return "Enchanting";
    }

    public int getSlots() {
        return 54;
    }

    public void handleMenu(InventoryClickEvent e) {
        ItemStack newItem;
        ItemMeta meta;
        Player p = (Player)e.getWhoClicked();
        List<ItemStack> books = this.filteredBooks;
        int slot = e.getSlot();
        if (slot == 19 && (e.getAction().equals(InventoryAction.PICKUP_ALL) || e.getAction().equals(InventoryAction.PICKUP_ONE))) {
            List<ItemStack> placeHolderBooks = new ArrayList<>();
            for (int i = 0; i < 15; i++)
                placeHolderBooks.add(makeItem(Material.RED_STAINED_GLASS_PANE, " ", new String[0]));
            this.placeHolderBooks = new ArrayList<>(placeHolderBooks);
            this.filteredBooks = new ArrayList<>(placeHolderBooks);
            this.page = 0;
            setMenuItems();
        }
        if (slot == 19 && (e.getAction().equals(InventoryAction.PLACE_ALL) || e.getAction().equals(InventoryAction.PLACE_ONE)))
            if ((e.getCursor().getType().equals(Material.WOODEN_SWORD) || e
                    .getCursor().getType().equals(Material.STONE_SWORD) || e
                    .getCursor().getType().equals(Material.IRON_SWORD) || e
                    .getCursor().getType().equals(Material.GOLDEN_SWORD) || e
                    .getCursor().getType().equals(Material.DIAMOND_SWORD) || e
                    .getCursor().getType().equals(Material.NETHERITE_SWORD)) && p.hasPermission("tbcenchant.enchants.sword")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getSwordEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.BOW) && p.hasPermission("tbcenchant.enchants.bow")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getBowEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.CROSSBOW) && p.hasPermission("tbcenchant.enchants.crossbow")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getCrossbowEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.WOODEN_AXE) || e
                    .getCursor().getType().equals(Material.STONE_AXE) || e
                    .getCursor().getType().equals(Material.IRON_AXE) || e
                    .getCursor().getType().equals(Material.GOLDEN_AXE) || e
                    .getCursor().getType().equals(Material.DIAMOND_AXE) || e
                    .getCursor().getType().equals(Material.NETHERITE_AXE)) && p.hasPermission("tbcenchant.enchants.axe")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getAxeEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.WOODEN_PICKAXE) || e
                    .getCursor().getType().equals(Material.STONE_PICKAXE) || e
                    .getCursor().getType().equals(Material.IRON_PICKAXE) || e
                    .getCursor().getType().equals(Material.GOLDEN_PICKAXE) || e
                    .getCursor().getType().equals(Material.DIAMOND_PICKAXE) || e
                    .getCursor().getType().equals(Material.NETHERITE_PICKAXE)) && p.hasPermission("tbcenchant.enchants.pickaxe")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getPickaxeEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.WOODEN_HOE) || e
                    .getCursor().getType().equals(Material.STONE_HOE) || e
                    .getCursor().getType().equals(Material.IRON_HOE) || e
                    .getCursor().getType().equals(Material.GOLDEN_HOE) || e
                    .getCursor().getType().equals(Material.DIAMOND_HOE) || (e
                    .getCursor().getType().equals(Material.NETHERITE_HOE) && p.hasPermission("tbcenchant.enchants.hoe"))) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getHoeEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.WOODEN_SHOVEL) || e
                    .getCursor().getType().equals(Material.STONE_SHOVEL) || e
                    .getCursor().getType().equals(Material.IRON_SHOVEL) || e
                    .getCursor().getType().equals(Material.GOLDEN_SHOVEL) || e
                    .getCursor().getType().equals(Material.DIAMOND_SHOVEL) || e
                    .getCursor().getType().equals(Material.NETHERITE_SHOVEL)) && p.hasPermission("tbcenchant.enchants.shovel")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getShovelEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.TRIDENT) && p.hasPermission("tbcenchant.enchants.tident")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getTridentEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.FISHING_ROD) && p.hasPermission("tbcenchant.enchants.fishingrod")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getFishingRodEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.SHEARS) && p.hasPermission("tbcenchant.enchants.shears")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getShearsEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.SHIELD) && p.hasPermission("tbcenchant.enchants.shield")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getShieldEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if (e.getCursor().getType().equals(Material.ELYTRA) && p.hasPermission("tbcenchant.enchants.elytra")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getElytraEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.LEATHER_HELMET) || e
                    .getCursor().getType().equals(Material.CHAINMAIL_HELMET) || e
                    .getCursor().getType().equals(Material.IRON_HELMET) || e
                    .getCursor().getType().equals(Material.GOLDEN_HELMET) || e
                    .getCursor().getType().equals(Material.DIAMOND_HELMET) || e
                    .getCursor().getType().equals(Material.NETHERITE_HELMET)) && p.hasPermission("tbcenchant.enchants.helmet")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getHelmetEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.LEATHER_CHESTPLATE) || e
                    .getCursor().getType().equals(Material.CHAINMAIL_CHESTPLATE) || e
                    .getCursor().getType().equals(Material.IRON_CHESTPLATE) || e
                    .getCursor().getType().equals(Material.GOLDEN_CHESTPLATE) || e
                    .getCursor().getType().equals(Material.DIAMOND_CHESTPLATE) || e
                    .getCursor().getType().equals(Material.NETHERITE_CHESTPLATE)) && p.hasPermission("tbcenchant.enchants.chestplate")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getChestplateEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.LEATHER_LEGGINGS) || e
                    .getCursor().getType().equals(Material.CHAINMAIL_LEGGINGS) || e
                    .getCursor().getType().equals(Material.IRON_LEGGINGS) || e
                    .getCursor().getType().equals(Material.GOLDEN_LEGGINGS) || e
                    .getCursor().getType().equals(Material.DIAMOND_LEGGINGS) || e
                    .getCursor().getType().equals(Material.NETHERITE_LEGGINGS)) && p.hasPermission("tbcenchant.enchants.leggings")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getLeggingsEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else if ((e.getCursor().getType().equals(Material.LEATHER_BOOTS) || e
                    .getCursor().getType().equals(Material.CHAINMAIL_BOOTS) || e
                    .getCursor().getType().equals(Material.IRON_BOOTS) || e
                    .getCursor().getType().equals(Material.GOLDEN_BOOTS) || e
                    .getCursor().getType().equals(Material.DIAMOND_BOOTS) || e
                    .getCursor().getType().equals(Material.NETHERITE_BOOTS)) && p.hasPermission("tbcenchant.enchants.leggings")) {
                this.currentItem = e.getCursor().clone();
                List<ItemStack> temp = new ArrayList<>();
                Objects.requireNonNull(temp);
                getBootsEnchantmentBooksList().forEach(temp::addAll);
                this.filteredBooks = new ArrayList<>(temp);
                clearPlaceholders();
                setMenuItems();
            } else {
                p.closeInventory();
                p.sendMessage(ChatColor.RED + "You do not have enough permissions to do that.");
            }
        if (e.getCurrentItem() == null)
            return;
        switch (e.getCurrentItem().getType()) {
            case BARRIER:
                p.closeInventory();
                break;
            case DARK_OAK_BUTTON:
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Up")) {
                    if (this.page == 0) {
                        p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                        break;
                    }
                    this.page--;
                    this.firstFreeSpotSlot = 11;
                    this.index = 0;
                    this.inventory.clear();
                    if (this.currentItem != null)
                        this.inventory.setItem(19, this.currentItem);
                    setMenuItems();
                    break;
                }
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Down")) {
                    if (this.index + 1 < books.size()) {
                        this.page++;
                        this.firstFreeSpotSlot = 11;
                        this.index = 0;
                        this.inventory.clear();
                        if (this.currentItem != null)
                            this.inventory.setItem(19, this.currentItem);
                        setMenuItems();
                        break;
                    }
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
                break;
            case ENCHANTED_BOOK:
                newItem = this.currentItem.clone();
                meta = newItem.getItemMeta();
                if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DAMAGE_ALL)) {
                        meta.addEnchant(Enchantment.DAMAGE_ALL, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
                        meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ARTHROPODS), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DAMAGE_UNDEAD)) {
                        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DAMAGE_UNDEAD), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.VANISHING_CURSE)) {
                        meta.addEnchant(Enchantment.VANISHING_CURSE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.VANISHING_CURSE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.FIRE_ASPECT)) {
                        meta.addEnchant(Enchantment.FIRE_ASPECT, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.FIRE_ASPECT), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.KNOCKBACK)) {
                        meta.addEnchant(Enchantment.KNOCKBACK, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.KNOCKBACK), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.LOOT_BONUS_MOBS)) {
                        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.MENDING)) {
                        meta.addEnchant(Enchantment.MENDING, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.MENDING), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.SWEEPING_EDGE)) {
                        meta.addEnchant(Enchantment.SWEEPING_EDGE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.SWEEPING_EDGE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DURABILITY)) {
                        meta.addEnchant(Enchantment.DURABILITY, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DURABILITY), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.CHANNELING)) {
                        meta.addEnchant(Enchantment.CHANNELING, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.CHANNELING), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.IMPALING)) {
                        meta.addEnchant(Enchantment.IMPALING, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.IMPALING), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.LOYALTY)) {
                        meta.addEnchant(Enchantment.LOYALTY, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LOYALTY), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.RIPTIDE)) {
                        meta.addEnchant(Enchantment.RIPTIDE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.RIPTIDE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.MULTISHOT)) {
                        meta.addEnchant(Enchantment.MULTISHOT, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.MULTISHOT), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PIERCING)) {
                        meta.addEnchant(Enchantment.PIERCING, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PIERCING), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.QUICK_CHARGE)) {
                        meta.addEnchant(Enchantment.QUICK_CHARGE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.QUICK_CHARGE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_FIRE)) {
                        meta.addEnchant(Enchantment.ARROW_FIRE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.ARROW_FIRE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_INFINITE)) {
                        meta.addEnchant(Enchantment.ARROW_INFINITE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.ARROW_INFINITE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_DAMAGE)) {
                        meta.addEnchant(Enchantment.ARROW_DAMAGE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.ARROW_DAMAGE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_KNOCKBACK)) {
                        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        meta.addEnchant(Enchantment.BINDING_CURSE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.BINDING_CURSE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DIG_SPEED)) {
                        meta.addEnchant(Enchantment.DIG_SPEED, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.SILK_TOUCH)) {
                        meta.addEnchant(Enchantment.SILK_TOUCH, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.OXYGEN)) {
                        meta.addEnchant(Enchantment.OXYGEN, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.OXYGEN), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.WATER_WORKER)) {
                        meta.addEnchant(Enchantment.WATER_WORKER, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.WATER_WORKER), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PROTECTION_EXPLOSIONS)) {
                        meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_EXPLOSIONS), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PROTECTION_FIRE)) {
                        meta.addEnchant(Enchantment.PROTECTION_FIRE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_FIRE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PROTECTION_FALL)) {
                        meta.addEnchant(Enchantment.PROTECTION_FALL, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_FALL), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PROTECTION_PROJECTILE)) {
                        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_PROJECTILE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.THORNS)) {
                        meta.addEnchant(Enchantment.THORNS, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.THORNS), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.DEPTH_STRIDER)) {
                        meta.addEnchant(Enchantment.DEPTH_STRIDER, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.DEPTH_STRIDER), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.LURE)) {
                        meta.addEnchant(Enchantment.LURE, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LURE), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.LUCK)) {
                        meta.addEnchant(Enchantment.LUCK, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LUCK), true);
                    } else if (e.getCurrentItem().getItemMeta().getEnchants().containsKey(Enchantment.FROST_WALKER)) {
                        meta.addEnchant(Enchantment.FROST_WALKER, e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.FROST_WALKER), true);
                    }
                newItem.setItemMeta(meta);
                this.currentItem = newItem.clone();
                this.inventory.setItem(19, this.currentItem);
                break;
        }
    }

    public void setMenuItems() {
        if (this.filteredBooks == null)
            this.filteredBooks = new ArrayList<>(this.placeHolderBooks);
        addMenuWithBorder();
        int[] availableSlots = {
                12, 13, 14, 15, 16, 21, 22, 23, 24, 25,
                30, 31, 32, 33, 34 };
        int currentSlot = 0;
        if (!this.filteredBooks.isEmpty())
            for (int i = 0; i < getMaxItemsPerPageWithBorder(); i++) {
                this.index = getMaxItemsPerPageWithBorder() * this.page + i;
                if (this.index >= this.filteredBooks.size())
                    break;
                if (this.filteredBooks.get(this.index) != null) {
                    ItemStack book = this.filteredBooks.get(this.index);
                    this.inventory.setItem(availableSlots[currentSlot], book);
                }
                currentSlot++;
            }
    }

    private void clearPlaceholders() {
        int[] availableSlots = {
                12, 13, 14, 15, 16, 21, 22, 23, 24, 25,
                30, 31, 32, 33, 34 };
        for (int availableSlot : availableSlots)
            this.inventory.setItem(availableSlot, new ItemStack(Material.AIR));
    }

    private List<List<ItemStack>> getSwordEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> baneOfArthropodsBooks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, i, true);
            item.setItemMeta(meta);
            baneOfArthropodsBooks.add(item);
        }
        books.add(baneOfArthropodsBooks);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> fireAspectBooks = new ArrayList<>();
        for (int k = 1; k <= 2; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.FIRE_ASPECT, k, true);
            item.setItemMeta(meta);
            fireAspectBooks.add(item);
        }
        books.add(fireAspectBooks);
        List<ItemStack> knockbackBooks = new ArrayList<>();
        for (int m = 1; m <= 2; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.KNOCKBACK, m, true);
            item.setItemMeta(meta);
            knockbackBooks.add(item);
        }
        books.add(knockbackBooks);
        List<ItemStack> lootingBooks = new ArrayList<>();
        for (int n = 1; n <= 3; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, n, true);
            item.setItemMeta(meta);
            lootingBooks.add(item);
        }
        books.add(lootingBooks);
        List<ItemStack> mendingBooks = new ArrayList<>();
        for (int i1 = 1; i1 <= 1; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, i1, true);
            item.setItemMeta(meta);
            mendingBooks.add(item);
        }
        books.add(mendingBooks);
        List<ItemStack> sharpnessBooks = new ArrayList<>();
        for (int i2 = 1; i2 <= 5; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, i2, true);
            item.setItemMeta(meta);
            sharpnessBooks.add(item);
        }
        books.add(sharpnessBooks);
        List<ItemStack> smiteBooks = new ArrayList<>();
        for (int i3 = 1; i3 <= 5; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_UNDEAD, i3, true);
            item.setItemMeta(meta);
            smiteBooks.add(item);
        }
        books.add(smiteBooks);
        List<ItemStack> sweepingBooks = new ArrayList<>();
        for (int i4 = 1; i4 <= 3; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SWEEPING_EDGE, i4, true);
            item.setItemMeta(meta);
            sweepingBooks.add(item);
        }
        books.add(sweepingBooks);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i5 = 1; i5 <= 3; i5++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i5, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getBowEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> flame = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_FIRE, j, true);
            item.setItemMeta(meta);
            flame.add(item);
        }
        books.add(flame);
        List<ItemStack> infinity = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_INFINITE, k, true);
            item.setItemMeta(meta);
            infinity.add(item);
        }
        books.add(infinity);
        List<ItemStack> mending = new ArrayList<>();
        for (int m = 1; m <= 1; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, m, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> power = new ArrayList<>();
        for (int n = 1; n <= 5; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_DAMAGE, n, true);
            item.setItemMeta(meta);
            power.add(item);
        }
        books.add(power);
        List<ItemStack> punch = new ArrayList<>();
        for (int i1 = 1; i1 <= 2; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, i1, true);
            item.setItemMeta(meta);
            punch.add(item);
        }
        books.add(punch);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i2 = 1; i2 <= 3; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i2, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getCrossbowEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> mending = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, j, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> multishot = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MULTISHOT, k, true);
            item.setItemMeta(meta);
            multishot.add(item);
        }
        books.add(multishot);
        List<ItemStack> piercing = new ArrayList<>();
        for (int m = 1; m <= 4; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PIERCING, m, true);
            item.setItemMeta(meta);
            piercing.add(item);
        }
        books.add(piercing);
        List<ItemStack> quickCharge = new ArrayList<>();
        for (int n = 1; n <= 3; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.QUICK_CHARGE, n, true);
            item.setItemMeta(meta);
            quickCharge.add(item);
        }
        books.add(quickCharge);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i1 = 1; i1 <= 3; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i1, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getTridentEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> channeling = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.CHANNELING, i, true);
            item.setItemMeta(meta);
            channeling.add(item);
        }
        books.add(channeling);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> impaling = new ArrayList<>();
        for (int k = 1; k <= 5; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.IMPALING, k, true);
            item.setItemMeta(meta);
            impaling.add(item);
        }
        books.add(impaling);
        List<ItemStack> loyalty = new ArrayList<>();
        for (int m = 1; m <= 3; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.LOYALTY, m, true);
            item.setItemMeta(meta);
            loyalty.add(item);
        }
        books.add(loyalty);
        List<ItemStack> mending = new ArrayList<>();
        for (int n = 1; n <= 1; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, n, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> riptide = new ArrayList<>();
        for (int i1 = 1; i1 <= 3; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.RIPTIDE, i1, true);
            item.setItemMeta(meta);
            riptide.add(item);
        }
        books.add(riptide);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i2 = 1; i2 <= 3; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i2, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getShieldEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> mending = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, j, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int k = 1; k <= 3; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, k, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getElytraEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfBinding = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfBinding.add(item);
        }
        books.add(curseOfBinding);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> mending = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, k, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int m = 1; m <= 3; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, m, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getShearsEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> efficiency = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, i, true);
            item.setItemMeta(meta);
            efficiency.add(item);
        }
        books.add(efficiency);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> mending = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, k, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int m = 1; m <= 3; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, m, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getHoeEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> mending = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, j, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int k = 1; k <= 3; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, k, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getShovelEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> efficiency = new ArrayList<>();
        for (int j = 1; j <= 5; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, j, true);
            item.setItemMeta(meta);
            efficiency.add(item);
        }
        books.add(efficiency);
        List<ItemStack> fortune = new ArrayList<>();
        for (int k = 1; k <= 3; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, k, true);
            item.setItemMeta(meta);
            fortune.add(item);
        }
        books.add(fortune);
        List<ItemStack> mending = new ArrayList<>();
        for (int m = 1; m <= 1; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, m, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> silkTouch = new ArrayList<>();
        for (int n = 1; n <= 3; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SILK_TOUCH, n, true);
            item.setItemMeta(meta);
            silkTouch.add(item);
        }
        books.add(silkTouch);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i1 = 1; i1 <= 3; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i1, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getPickaxeEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> efficiency = new ArrayList<>();
        for (int j = 1; j <= 5; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, j, true);
            item.setItemMeta(meta);
            efficiency.add(item);
        }
        books.add(efficiency);
        List<ItemStack> fortune = new ArrayList<>();
        for (int k = 1; k <= 3; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, k, true);
            item.setItemMeta(meta);
            fortune.add(item);
        }
        books.add(fortune);
        List<ItemStack> mending = new ArrayList<>();
        for (int m = 1; m <= 1; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, m, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> silkTouch = new ArrayList<>();
        for (int n = 1; n <= 3; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SILK_TOUCH, n, true);
            item.setItemMeta(meta);
            silkTouch.add(item);
        }
        books.add(silkTouch);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i1 = 1; i1 <= 3; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i1, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getAxeEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> baneOfArthropods = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, i, true);
            item.setItemMeta(meta);
            baneOfArthropods.add(item);
        }
        books.add(baneOfArthropods);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> efficiency = new ArrayList<>();
        for (int k = 1; k <= 5; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, k, true);
            item.setItemMeta(meta);
            efficiency.add(item);
        }
        books.add(efficiency);
        List<ItemStack> fortune = new ArrayList<>();
        for (int m = 1; m <= 3; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, m, true);
            item.setItemMeta(meta);
            fortune.add(item);
        }
        books.add(fortune);
        List<ItemStack> mending = new ArrayList<>();
        for (int n = 1; n <= 1; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, n, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> sharpness = new ArrayList<>();
        for (int i1 = 1; i1 <= 5; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, i1, true);
            item.setItemMeta(meta);
            sharpness.add(item);
        }
        books.add(sharpness);
        List<ItemStack> silkTouch = new ArrayList<>();
        for (int i2 = 1; i2 <= 3; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SILK_TOUCH, i2, true);
            item.setItemMeta(meta);
            silkTouch.add(item);
        }
        books.add(silkTouch);
        List<ItemStack> smite = new ArrayList<>();
        for (int i3 = 1; i3 <= 5; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_UNDEAD, i3, true);
            item.setItemMeta(meta);
            smite.add(item);
        }
        books.add(smite);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i4 = 1; i4 <= 3; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i4, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getFishingRodEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, i, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> luckOfTheSea = new ArrayList<>();
        for (int j = 1; j <= 3; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.LUCK, j, true);
            item.setItemMeta(meta);
            luckOfTheSea.add(item);
        }
        books.add(luckOfTheSea);
        List<ItemStack> lure = new ArrayList<>();
        for (int k = 1; k <= 3; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.LURE, k, true);
            item.setItemMeta(meta);
            lure.add(item);
        }
        books.add(lure);
        List<ItemStack> mending = new ArrayList<>();
        for (int m = 1; m <= 1; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, m, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int n = 1; n <= 3; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, n, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getBootsEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> blastProtection = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, i, true);
            item.setItemMeta(meta);
            blastProtection.add(item);
        }
        books.add(blastProtection);
        List<ItemStack> curseOfBinding = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfBinding.add(item);
        }
        books.add(curseOfBinding);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, k, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> depthStrider = new ArrayList<>();
        for (int m = 1; m <= 3; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DEPTH_STRIDER, m, true);
            item.setItemMeta(meta);
            depthStrider.add(item);
        }
        books.add(depthStrider);
        List<ItemStack> featherFalling = new ArrayList<>();
        for (int n = 1; n <= 4; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_FALL, n, true);
            item.setItemMeta(meta);
            featherFalling.add(item);
        }
        books.add(featherFalling);
        List<ItemStack> fireProtection = new ArrayList<>();
        for (int i1 = 1; i1 <= 4; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_FIRE, i1, true);
            item.setItemMeta(meta);
            fireProtection.add(item);
        }
        books.add(fireProtection);
        List<ItemStack> frostWalker = new ArrayList<>();
        for (int i2 = 1; i2 <= 2; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.FROST_WALKER, i2, true);
            item.setItemMeta(meta);
            frostWalker.add(item);
        }
        books.add(frostWalker);
        List<ItemStack> mending = new ArrayList<>();
        for (int i3 = 1; i3 <= 1; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, i3, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> projectileProtection = new ArrayList<>();
        for (int i4 = 1; i4 <= 4; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, i4, true);
            item.setItemMeta(meta);
            projectileProtection.add(item);
        }
        books.add(projectileProtection);
        List<ItemStack> protection = new ArrayList<>();
        for (int i5 = 1; i5 <= 4; i5++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, i5, true);
            item.setItemMeta(meta);
            protection.add(item);
        }
        books.add(protection);
        List<ItemStack> soulSpeed = new ArrayList<>();
        for (int i6 = 1; i6 <= 3; i6++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SOUL_SPEED, i6, true);
            item.setItemMeta(meta);
            soulSpeed.add(item);
        }
        books.add(soulSpeed);
        List<ItemStack> thorns = new ArrayList<>();
        for (int i7 = 1; i7 <= 3; i7++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.THORNS, i7, true);
            item.setItemMeta(meta);
            thorns.add(item);
        }
        books.add(thorns);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i8 = 1; i8 <= 3; i8++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i8, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getLeggingsEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> blastProtection = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, i, true);
            item.setItemMeta(meta);
            blastProtection.add(item);
        }
        books.add(blastProtection);
        List<ItemStack> curseOfBinding = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfBinding.add(item);
        }
        books.add(curseOfBinding);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, k, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> fireProtection = new ArrayList<>();
        for (int m = 1; m <= 4; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_FIRE, m, true);
            item.setItemMeta(meta);
            fireProtection.add(item);
        }
        books.add(fireProtection);
        List<ItemStack> mending = new ArrayList<>();
        for (int n = 1; n <= 1; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, n, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> projectileProtection = new ArrayList<>();
        for (int i1 = 1; i1 <= 4; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, i1, true);
            item.setItemMeta(meta);
            projectileProtection.add(item);
        }
        books.add(projectileProtection);
        List<ItemStack> protection = new ArrayList<>();
        for (int i2 = 1; i2 <= 4; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, i2, true);
            item.setItemMeta(meta);
            protection.add(item);
        }
        books.add(protection);
        List<ItemStack> thorns = new ArrayList<>();
        for (int i3 = 1; i3 <= 3; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.THORNS, i3, true);
            item.setItemMeta(meta);
            thorns.add(item);
        }
        books.add(thorns);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i4 = 1; i4 <= 3; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i4, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getChestplateEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> blastProtection = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, i, true);
            item.setItemMeta(meta);
            blastProtection.add(item);
        }
        books.add(blastProtection);
        List<ItemStack> curseOfBinding = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, j, true);
            item.setItemMeta(meta);
            curseOfBinding.add(item);
        }
        books.add(curseOfBinding);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int k = 1; k <= 1; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, k, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> fireProtection = new ArrayList<>();
        for (int m = 1; m <= 4; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_FIRE, m, true);
            item.setItemMeta(meta);
            fireProtection.add(item);
        }
        books.add(fireProtection);
        List<ItemStack> mending = new ArrayList<>();
        for (int n = 1; n <= 1; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, n, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> projectileProtection = new ArrayList<>();
        for (int i1 = 1; i1 <= 4; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, i1, true);
            item.setItemMeta(meta);
            projectileProtection.add(item);
        }
        books.add(projectileProtection);
        List<ItemStack> protection = new ArrayList<>();
        for (int i2 = 1; i2 <= 4; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, i2, true);
            item.setItemMeta(meta);
            protection.add(item);
        }
        books.add(protection);
        List<ItemStack> thorns = new ArrayList<>();
        for (int i3 = 1; i3 <= 3; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.THORNS, i3, true);
            item.setItemMeta(meta);
            thorns.add(item);
        }
        books.add(thorns);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i4 = 1; i4 <= 3; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i4, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }

    private List<List<ItemStack>> getHelmetEnchantmentBooksList() {
        List<List<ItemStack>> books = new ArrayList<>();
        List<ItemStack> respiration = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.OXYGEN, i, true);
            item.setItemMeta(meta);
            respiration.add(item);
        }
        books.add(respiration);
        List<ItemStack> aquaInfinity = new ArrayList<>();
        for (int j = 1; j <= 1; j++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.WATER_WORKER, j, true);
            item.setItemMeta(meta);
            aquaInfinity.add(item);
        }
        books.add(aquaInfinity);
        List<ItemStack> blastProtection = new ArrayList<>();
        for (int k = 1; k <= 4; k++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, k, true);
            item.setItemMeta(meta);
            blastProtection.add(item);
        }
        books.add(blastProtection);
        List<ItemStack> curseOfBinding = new ArrayList<>();
        for (int m = 1; m <= 1; m++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.BINDING_CURSE, m, true);
            item.setItemMeta(meta);
            curseOfBinding.add(item);
        }
        books.add(curseOfBinding);
        List<ItemStack> curseOfVanishingBooks = new ArrayList<>();
        for (int n = 1; n <= 1; n++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.VANISHING_CURSE, n, true);
            item.setItemMeta(meta);
            curseOfVanishingBooks.add(item);
        }
        books.add(curseOfVanishingBooks);
        List<ItemStack> fireProtection = new ArrayList<>();
        for (int i1 = 1; i1 <= 4; i1++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_FIRE, i1, true);
            item.setItemMeta(meta);
            fireProtection.add(item);
        }
        books.add(fireProtection);
        List<ItemStack> mending = new ArrayList<>();
        for (int i2 = 1; i2 <= 1; i2++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.MENDING, i2, true);
            item.setItemMeta(meta);
            mending.add(item);
        }
        books.add(mending);
        List<ItemStack> projectileProtection = new ArrayList<>();
        for (int i3 = 1; i3 <= 4; i3++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, i3, true);
            item.setItemMeta(meta);
            projectileProtection.add(item);
        }
        books.add(projectileProtection);
        List<ItemStack> protection = new ArrayList<>();
        for (int i4 = 1; i4 <= 4; i4++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, i4, true);
            item.setItemMeta(meta);
            protection.add(item);
        }
        books.add(protection);
        List<ItemStack> thorns = new ArrayList<>();
        for (int i5 = 1; i5 <= 3; i5++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.THORNS, i5, true);
            item.setItemMeta(meta);
            thorns.add(item);
        }
        books.add(thorns);
        List<ItemStack> unbreakingBooks = new ArrayList<>();
        for (int i6 = 1; i6 <= 3; i6++) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DURABILITY, i6, true);
            item.setItemMeta(meta);
            unbreakingBooks.add(item);
        }
        books.add(unbreakingBooks);
        return books;
    }
}
