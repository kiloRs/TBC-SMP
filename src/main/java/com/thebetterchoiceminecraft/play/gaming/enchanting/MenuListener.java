package com.thebetterchoiceminecraft.play.gaming.enchanting;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.play.gaming.enchanting.GUI.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


public class MenuListener implements Listener {
    private final TBCPlugin plugin;

    public MenuListener(TBCPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (e.getClickedInventory() == null)
            return;
        String inventoryType = e.getClickedInventory().getType().toString();
        InventoryAction action = e.getAction();
        int slot = e.getRawSlot();
        boolean isTakingFromInventory = (inventoryType.equalsIgnoreCase("player") && (action.equals(InventoryAction.PICKUP_ALL) || action.equals(InventoryAction.PICKUP_ONE)));
        boolean isPlacingToEnchant = (!inventoryType.equalsIgnoreCase("player") && (action.equals(InventoryAction.PLACE_ALL) || action.equals(InventoryAction.PLACE_ONE)) && slot == 19);
        boolean isTakingFromEnchanted = (!inventoryType.equalsIgnoreCase("player") && (action.equals(InventoryAction.PICKUP_ALL) || action.equals(InventoryAction.PICKUP_ONE)) && slot == 19);
        boolean isPlacingToInventory = (inventoryType.equalsIgnoreCase("player") && (action.equals(InventoryAction.PLACE_ALL) || action.equals(InventoryAction.PLACE_ONE)));
        boolean isValidAction = (isTakingFromInventory || isPlacingToEnchant || isTakingFromEnchanted || isPlacingToInventory);
        if (holder instanceof Menu menu) {
            if (!isValidAction)
                e.setCancelled(true);
            if (slot != 19 && e.getCurrentItem() == null)
                return;
            menu.handleMenu(e);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            ItemStack itemUsed = menu.getInventory().getItem(19);
            if (itemUsed != null) {
                e.getPlayer().getInventory().addItem(itemUsed);
            }
            menu.flushRunningTasks();
        }
    }
}
