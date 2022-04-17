package psikuvit.tbcenchant.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.gui.EnchantingGUI;

import java.util.HashMap;

public class User {
    public String player;

    public String name;

    private EnchantingGUI enchantingGUI;

    public User(OfflinePlayer p) {
        this.player = p.getUniqueId().toString();
        this.name = p.getName();
        (TBCEnchant.getInstance().getPlayersData()).users.put(this.player, this);
    }

    public static User getUser(OfflinePlayer p) {
        if (p == null)
            return null;
        if ((TBCEnchant.getInstance().getPlayersData()).users == null)
            (TBCEnchant.getInstance().getPlayersData()).users = new HashMap<>();
        return (TBCEnchant.getInstance().getPlayersData()).users.containsKey(p.getUniqueId().toString()) ? (TBCEnchant.getInstance().getPlayersData()).users.get(p.getUniqueId().toString()) : new User(p);
    }

    public EnchantingGUI getMainMenu(int bookShelfPower, boolean createNew, ItemStack itemStack) {
        Player player = Bukkit.getPlayer(this.name);
        if (player == null)
            return null;
        if (this.enchantingGUI == null || createNew)
            this.enchantingGUI = new EnchantingGUI(bookShelfPower, itemStack);
        return this.enchantingGUI;
    }
}
