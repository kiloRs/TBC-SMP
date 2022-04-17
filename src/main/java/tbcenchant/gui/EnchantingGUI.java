package psikuvit.tbcenchant.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
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

import java.util.Map;

public class EnchantingGUI extends GUI implements Listener {
    private final GUIManager guiManager;

    private final TBCEnchant plugin;

    public EnchantingGUI(int bookShelfPower, ItemStack itemStack) {
        super((TBCEnchant.getInstance().getInventories()).mainMenuSize, (TBCEnchant.getInstance().getInventories()).mainMenuTitle, false);
        this.plugin = TBCEnchant.getInstance();
        this.guiManager = new GUIManager(getInventory(), bookShelfPower);
        if (itemStack != null)
            setItem(19, itemStack);
        this.plugin.registerListeners(new Listener[] { this });
    }

    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty())
            return;
        setItem((this.plugin.getInventories()).closeButton.slot.intValue(), InventoryUtils.makeItem((this.plugin.getInventories()).closeButton));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(getInventory()))
            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
                if (this.guiManager.getToEnchantItem() != null) {
                    e.getPlayer().getInventory().addItem(new ItemStack[] { this.guiManager.getToEnchantItem() } );
                    this.guiManager.setToEnchantItem(null);
                }
            },3L);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (!e.getInventory().equals(getInventory()))
            return;
        this.guiManager.checkEnchantmentTable();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null || (!e.getClickedInventory().equals(getInventory()) && !e.getInventory().equals(getInventory())))
            return;
        Player player = (Player)e.getWhoClicked();
        if (e.getClickedInventory().equals(getInventory()) &&
                e.getCurrentItem() != null) {
            int slot = e.getSlot();
            if (slot == 49) {
                player.closeInventory();
                e.setCancelled(true);
            } else if (slot != 19) {
                e.setCancelled(true);
                int page = this.guiManager.getBooksPage();
                if (slot == (this.plugin.getInventories()).backPageButton.slot.intValue() && page > 1) {
                    this.guiManager.setBooksPage(page - 1);
                } else if (slot == (this.plugin.getInventories()).nextPageButton.slot.intValue() && e.getCurrentItem().getType().equals(InventoryUtils.makeItem((this.plugin.getInventories()).nextPageButton).getType())) {
                    this.guiManager.setBooksPage(page + 1);
                } else {
                    Map<Integer, EnchantObject> tbcenchants = this.guiManager.getEnchantsMap();
                    if (tbcenchants.size() < 1)
                        return;
                    if (!tbcenchants.containsKey(Integer.valueOf(slot)))
                        return;
                    TBCEnchants tbcenchant = ((EnchantObject)tbcenchants.get(Integer.valueOf(slot))).getTBCEnchant();
                    if (this.guiManager.getBokShelfPower() >= tbcenchant.getRequiredBookShelf()) {
                        ItemStack newItem = this.guiManager.getToEnchantItem().clone();
                        this.guiManager.setToEnchantItem(null);
                        player.openInventory((new SecondPageGUI(this.guiManager, player.getUniqueId(), newItem, slot)).getInventory());
                        return;
                    }
                    player.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("notEnoughPower")));
                }
            }
        }
        this.guiManager.checkEnchantmentTable();
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (!e.getInventory().equals(getInventory()))
            return;
        this.guiManager.checkEnchantmentTable();
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
            if (this.guiManager.getToEnchantItem() != null)
                p.getInventory().addItem(new ItemStack[] { this.guiManager.getToEnchantItem() });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
