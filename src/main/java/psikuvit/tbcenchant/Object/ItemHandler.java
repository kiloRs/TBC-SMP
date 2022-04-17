package psikuvit.tbcenchant.Object;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.TBCEnchant;

import java.util.Map;

public class ItemHandler {
    private BukkitTask bukkitTask;

    public void start(Player player) {}

    private boolean check(Player player) {
        if (player == null || !player.isOnline())
            return false;
        ItemStack itemStack = player.getItemInHand();
        if (itemStack == null || itemStack.getType() == Material.AIR);
        Map<TBCEnchants, Integer> enchants = TBCEnchant.getInstance().getHyperEnchantments().getItemEnchantments(itemStack);
        if (enchants.size() < 1)
            return true;
        ItemStack newItem = itemStack.clone();
        for (TBCEnchants tbcenchant : enchants.keySet())
            newItem = TBCEnchant.getInstance().getEnchantsHandler().addEnchantment(itemStack, ((Integer)enchants.get(tbcenchant)).intValue(), false, tbcenchant);
        player.setItemInHand(newItem);
        return true;
    }

    public void stop() {}
}
