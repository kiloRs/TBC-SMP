package psikuvit.tbcenchant.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.InventoryUtils;
import psikuvit.tbcenchant.Utils.Utils;

public abstract class GUI {
    public int scheduler;

    private final Inventory inventory;

    private final boolean decoration;

    public GUI(int size, String name, boolean decoration) {
        this.inventory = Bukkit.createInventory(null, size, Utils.color(name));
        this.decoration = decoration;
        this.scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin) TBCEnchant.getInstance(), this::addContent, 0L, 2L);
    }

    public void addContent() {
        if (this.inventory.getViewers().isEmpty())
            return;
        if (this.decoration) {
            for (int i = 0; i < this.inventory.getSize(); i++) {
                if (this.inventory.getItem(i) == null || this.inventory.getItem(i).getType().equals(Material.AIR))
                    setItem(i, InventoryUtils.makeItemHidden((TBCEnchant.getInstance().getInventories()).background));
            }
        } else {
            for (int i = 0; i < this.inventory.getSize(); i++) {
                if (i != 19 && (
                        this.inventory.getItem(i) == null || this.inventory.getItem(i).getType().equals(Material.AIR)))
                    setItem(i, InventoryUtils.makeItemHidden((TBCEnchant.getInstance().getInventories()).background));
            }
        }
    }

    public void setItem(int i, ItemStack itemStack) {
        if (i >= this.inventory.getSize())
            return;
        if (this.inventory.getItem(i) == null || !this.inventory.getItem(i).isSimilar(itemStack))
            this.inventory.setItem(i, itemStack);
    }

    public void setItem(int i, ItemStack itemStack, int quantity) {
        if (i >= this.inventory.getSize())
            return;
        if (getInventory().getItem(i) == null || !getInventory().getItem(i).isSimilar(itemStack)) {
            itemStack.setAmount(quantity);
            getInventory().setItem(i, itemStack);
        }
    }

    public abstract void onInventoryClick(InventoryClickEvent paramInventoryClickEvent);

    public Inventory getInventory() {
        return this.inventory;
    }
}
