package com.thebetterchoiceminecraft.play.gaming.enchanting.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public abstract class PaginatedMenu extends Menu {
    protected int page = 0;

    protected int maxItemsPerPageWithBorder = 15;

    protected int maxItemsPerPageWithoutBorder = 54;

    protected int index = 0;

    protected int firstFreeSpotSlot = 11;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public void addMenuWithBorder() {
        this.inventory.setItem(49, makeItem(Material.BARRIER, ChatColor.DARK_RED + "Close"));
        this.inventory.setItem(28, makeItem(Material.ENCHANTING_TABLE, ChatColor.GRAY + "Enchant", ChatColor.GRAY + "Accepted items:", ChatColor.GRAY + "Sword, Pickaxe, Axe, Shovel, Hoe", ChatColor.GRAY + "Bow, Crossbow", ChatColor.GRAY + "Helmet, Chestplate, Leggings, Boots", ChatColor.GRAY + "Elytra, Trident, Fishing rod, Shield", ChatColor.GRAY + "", ChatColor.GRAY + "Coming soon - Books."));
        int i;
        for (i = 0; i < 10; i++) {
            if (this.inventory.getItem(i) == null)
                this.inventory.setItem(i, this.FILLER_GLASS);
        }
        this.inventory.setItem(17, makeItem(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "Up"));
        this.inventory.setItem(10, this.FILLER_GLASS);
        this.inventory.setItem(11, this.FILLER_GLASS);
        this.inventory.setItem(20, this.FILLER_GLASS);
        this.inventory.setItem(18, this.FILLER_GLASS);
        this.inventory.setItem(26, this.FILLER_GLASS);
        this.inventory.setItem(27, this.FILLER_GLASS);
        this.inventory.setItem(29, this.FILLER_GLASS);
        this.inventory.setItem(37, this.FILLER_GLASS);
        this.inventory.setItem(38, this.FILLER_GLASS);
        this.inventory.setItem(39, this.FILLER_GLASS);
        this.inventory.setItem(40, this.FILLER_GLASS);
        this.inventory.setItem(41, this.FILLER_GLASS);
        this.inventory.setItem(42, this.FILLER_GLASS);
        this.inventory.setItem(43, this.FILLER_GLASS);
        this.inventory.setItem(35, makeItem(Material.DARK_OAK_BUTTON, ChatColor.GREEN + "Down"));
        this.inventory.setItem(36, this.FILLER_GLASS);
        this.inventory.setItem(48, this.FILLER_GLASS);
        this.inventory.setItem(50, this.FILLER_GLASS);
        for (i = 44; i < 54; i++) {
            if (this.inventory.getItem(i) == null)
                this.inventory.setItem(i, this.FILLER_GLASS);
        }
    }

    public int getMaxItemsPerPageWithBorder() {
        return this.maxItemsPerPageWithBorder;
    }
}
