package psikuvit.tbcenchant.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.Object.EnchantObject;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.InventoryUtils;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.managers.GUIManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SecondPageGUI extends GUI implements Listener {
    private final TBCEnchant plugin;

    private ItemStack itemStack;

    private final UUID uuid;

    private final GUIManager guiManager;

    private Map<Integer, Integer> slots;

    private final EnchantObject enchantObject;

    public SecondPageGUI(GUIManager guiManager, UUID uuid, ItemStack itemStack, int enchant) {
        super((TBCEnchant.getInstance().getInventories()).mainMenuSize, (TBCEnchant.getInstance().getInventories()).mainMenuTitle, true);
        this.plugin = TBCEnchant.getInstance();
        this.itemStack = itemStack;
        this.uuid = uuid;
        this.guiManager = guiManager;
        this.slots = new HashMap<>();
        this.enchantObject = (EnchantObject)guiManager.getEnchantsMap().get(Integer.valueOf(enchant));
        this.plugin.registerListeners(new Listener[] { this });
    }

    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty())
            return;
        if (this.itemStack != null) {
            Map<Integer, List<Integer>> levelSlots = (this.plugin.getInventories()).levelSlots;
            Map<Integer, Integer> toClickSlots = new HashMap<>();
            TBCEnchants tbcenchant = this.enchantObject.getTBCEnchant();
            List<Integer> slots = (tbcenchant.getMaxLevel() > levelSlots.size()) ? levelSlots.get(Integer.valueOf(levelSlots.size())) : levelSlots.get(Integer.valueOf(tbcenchant.getMaxLevel()));
            for (int i = 1; i <= tbcenchant.getMaxLevel(); i++) {
                try {
                    int slot = ((Integer)slots.get(i - 1)).intValue();
                    if (tbcenchant.isUseMoney()) {
                        setItem(slot,
                                InventoryUtils.makeItemHidden((this.plugin.getInventories()).moneyEnchantBook,
                                        Utils.getMoneyPlaceholders(this.uuid, tbcenchant, i, this.itemStack), tbcenchant, this.itemStack));
                    } else {
                        setItem(slot,
                                InventoryUtils.makeItemHidden((this.plugin.getInventories()).levelEnchantBook,
                                        Utils.getEnchantPlaceholders(this.uuid, tbcenchant, i, this.itemStack), tbcenchant, this.itemStack));
                    }
                    toClickSlots.put(Integer.valueOf(slot), Integer.valueOf(i));
                } catch (IndexOutOfBoundsException e) {
                    Bukkit.getLogger().warning("[TBCEnchant] Enchantment " + tbcenchant.getEnchantmentName() + " bypass 'levelSlots' option in inventories.yml, add more values to show all levels.");
                }
            }
            this.slots = toClickSlots;
        }
        setItem((this.plugin.getInventories()).previousPage.slot.intValue(), InventoryUtils.makeItem((this.plugin.getInventories()).previousPage));
        setItem((this.plugin.getInventories()).closeButton.slot.intValue(), InventoryUtils.makeItem((this.plugin.getInventories()).closeButton));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(getInventory()))
            Bukkit.getScheduler().runTaskLater((Plugin)TBCEnchant.getInstance(), () -> {
                if (this.itemStack != null)
                    e.getPlayer().getInventory().addItem(new ItemStack[] { this.itemStack } );
            },3L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null || (!e.getClickedInventory().equals(getInventory()) && !e.getInventory().equals(getInventory())))
            return;
        Player player = (Player)e.getWhoClicked();
        if (e.getClickedInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                int slot = e.getSlot();
                if (slot == 49) {
                    player.closeInventory();
                } else if (e.getSlot() == (this.plugin.getInventories()).previousPage.slot.intValue()) {
                    ItemStack newItem = this.itemStack.clone();
                    this.itemStack = null;
                    player.openInventory((new EnchantingGUI(this.guiManager.getBokShelfPower(), newItem)).getInventory());
                } else if (e.getSlot() == (TBCEnchant.getInstance().getInventories()).previousPage.slot.intValue()) {
                    player.openInventory((new EnchantingGUI(this.guiManager.getBokShelfPower(), this.itemStack)).getInventory());
                } else if (this.slots.containsKey(Integer.valueOf(e.getSlot()))) {
                    this.enchantObject.setLevel(((Integer)this.slots.get(Integer.valueOf(slot))).intValue());
                    ItemStack newItem = this.guiManager.enchantItem(player, this.itemStack.clone(), this.enchantObject);
                    if (newItem != null) {
                        this.itemStack = null;
                        player.openInventory((new EnchantingGUI(this.guiManager.getBokShelfPower(), newItem)).getInventory());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        try {
            if (getInventory() == null || this.guiManager == null)
                return;
            Player p = event.getPlayer();
            Inventory inventory = getInventory();
            if (!p.getOpenInventory().getTopInventory().equals(inventory))
                return;
            if (this.itemStack != null)
                p.getInventory().addItem(new ItemStack[] { this.itemStack });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
