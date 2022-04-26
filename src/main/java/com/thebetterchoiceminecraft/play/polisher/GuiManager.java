package com.thebetterchoiceminecraft.play.polisher;

import com.thebetterchoiceminecraft.utils.BaseListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import xyz.janboerman.guilib.api.GuiInventoryHolder;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.function.Consumer;

public class GuiManager  implements BaseListener {
    @Override
    public void unregisterAfter(long timeZone, boolean offForGood) {

    }
    private final WeakHashMap<Inventory, WeakReference<GuiInventoryHolder<?>>> guiInventories = new WeakHashMap<>();

    @Override
    public boolean unregister() {
        return false;
    }

    @Override
    public void register() {

    }

    // We cannot ever make an unregisterGui method because to do that we would need to unset the GuiInventoryHolder from the Inventory.
    // So until such a method is added to bukkit's api, this is impossible to do without nms/reflection hacks.

    // ===== event stuff =====



    /**
     * Substitute for {@link Inventory#getHolder()} for gui inventories.
     * @param inventory the inventory
     * @return the holder - or null if no holder was registered with the inventory.
     */
    public GuiInventoryHolder<?> getHolder(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();
        if (holder instanceof GuiInventoryHolder) return (GuiInventoryHolder<?>) holder;

        WeakReference<GuiInventoryHolder<?>> reference = guiInventories.get(inventory);
        if (reference == null) return null;

        return reference.get(); //can still be null
    }


    private void onGuiInventoryEvent(InventoryEvent event, Consumer<GuiInventoryHolder> action) {
        GuiInventoryHolder<?> guiHolder = getHolder(event.getInventory());

        if (guiHolder == null){
            throw new RuntimeException("Invalid GUI Open Event!");
        }
        if (!guiHolder.getPlugin().isEnabled()) {
            throw new RuntimeException("GUI Plugin Not Enabled!");
        }

        action.accept(guiHolder);

        InventoryView inventoryView = event.getView();

        //Start Here

    }

    /**
     * Delegates the InventoryOpenEvent to the {@link GuiInventoryHolder} if the top inventory is held by a Gui and the event is not cancelled.
     * @param event the InventoryOpenEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryOpen(InventoryOpenEvent event) {
        onGuiInventoryEvent(event, gui -> gui.onOpen(event));
    }

    /**
     * Delegates the InventoryClickEvent to the {@link GuiInventoryHolder} if the top inventory is held by a Gui and the event is not cancelled.
     * InventoryClickEvents are cancelled before they are passed to the Gui.
     * @param event the InventoryClickEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        onGuiInventoryEvent(event, gui -> {
            event.setCancelled(true);
            gui.onClick(event);
        });
    }

    /**
     * Delegates the InventoryDragEvent to the {@link GuiInventoryHolder} if the top inventory is held by a Gui and the event is not cancelled.
     * InventoryDragEvents are cancelled before they are passed to the Gui.
     * @param event the InventoryDragEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryDrag(InventoryDragEvent event) {
        onGuiInventoryEvent(event, gui -> {
            event.setCancelled(true);
            gui.onDrag(event);
        });
    }

    /**
     * Delegates the InventoryCloseEvent to the {@link GuiInventoryHolder} if the top inventory is held by a Gui.
     * @param event InventoryCloseEvent
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        onGuiInventoryEvent(event, gui -> gui.onClose(event));
    }

}
