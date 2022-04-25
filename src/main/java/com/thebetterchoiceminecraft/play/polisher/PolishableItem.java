package com.thebetterchoiceminecraft.play.polisher;

import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import de.jeff_media.jefflib.TextUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.interaction.UseItem;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PolishableItem extends UseItem implements Listener {

    private @Nullable Gui gui;

    public PolishableItem(Player player, NBTItem nbtItem) {
        super(player, nbtItem);
    }

    public PolishableItem(PlayerData playerData, NBTItem nbtItem) {
        super(playerData, nbtItem);
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onUse(PlayerSwapHandItemsEvent e){
        if (!e.getPlayer().getUniqueId().equals(player.getUniqueId())){
            return;
        }
        if (!CitizensAPI.getNPCRegistry().isNPC(e.getPlayer().getTargetEntity(10))) {
            return;
        }

        try {
            e.setCancelled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Inventory gui = Bukkit.createInventory(e.getPlayer(), InventoryType.CHEST);

        this.gui = Gui.getGui(gui);

        if (this.gui != null) {
            this.gui.setOnClose(inventoryCloseEvent -> ((Player) inventoryCloseEvent.getPlayer()).sendRawMessage(TextUtils.color("&cClosing Polishing GUI - Thank You")));
            this.gui.setOnGlobalClick(inventoryClickEvent -> {
                ItemStack currentItem = inventoryClickEvent.getCurrentItem();
                if (currentItem.getType()== Material.BLAZE_POWDER) {
                    Player whoClicked = (Player) inventoryClickEvent.getWhoClicked();

                    if (whoClicked.getInventory().isEmpty()){
                        return;
                    }

                    ItemStack main = whoClicked.getInventory().getItemInMainHand();

                    if (main.getType()==Material.AIR){
                        return;
                    }


                }
            });
        }
    }
}
