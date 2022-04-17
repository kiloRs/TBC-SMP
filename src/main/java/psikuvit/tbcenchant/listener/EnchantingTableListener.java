package psikuvit.tbcenchant.listener;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.libs.XMaterial;
import psikuvit.tbcenchant.playerdata.User;

public class EnchantingTableListener implements Listener {
    @EventHandler
    public void onOpenEnchantmentTable(PlayerInteractEvent e) {
        if (!(TBCEnchant.getInstance().getConfiguration()).setAsDefaultEnchantingTable)
            return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Block bl = e.getClickedBlock();
        if (bl == null || !bl.getType().equals(XMaterial.ENCHANTING_TABLE.parseMaterial()))
            return;
        String world = e.getPlayer().getWorld().getName();
        if ((TBCEnchant.getInstance().getConfiguration()).disabledWorlds.contains(world))
            return;
        e.setCancelled(true);
        Player player = e.getPlayer();
        User user = User.getUser((OfflinePlayer)player);
        player.openInventory(user.getMainMenu(Utils.getBookShelfPower(bl), true, null).getInventory());
    }
}
