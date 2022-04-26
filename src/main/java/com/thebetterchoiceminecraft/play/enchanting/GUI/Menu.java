package com.thebetterchoiceminecraft.play.enchanting.GUI;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Menu implements InventoryHolder {
    protected final TBCPlugin plugin = TBCPlugin.getInstance();

    protected PlayerMenuUtility playerMenuUtility;

    protected Inventory inventory;

    protected ItemStack FILLER_GLASS = makeItem(Material.GRAY_STAINED_GLASS_PANE, " ");

    protected List<BukkitTask> runningTasks = new ArrayList<>();

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent paramInventoryClickEvent);

    public abstract void setMenuItems();

    public void open() {
        this.inventory = Bukkit.createInventory(this, getSlots(), getMenuName());
        setMenuItems();
        this.playerMenuUtility.getOwner().openInventory(this.inventory);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ItemStack makeItem(Material material, String displayName, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        List<String> newList = Arrays.asList(lore);
        itemMeta.setLore(newList);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void flushRunningTasks() {
        this.runningTasks.forEach(BukkitTask::cancel);
    }
}
