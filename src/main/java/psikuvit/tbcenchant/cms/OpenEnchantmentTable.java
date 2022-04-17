package psikuvit.tbcenchant.cms;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;
import psikuvit.tbcenchant.playerdata.User;

import java.util.Collections;
import java.util.List;

public class OpenEnchantmentTable extends Command {
    public OpenEnchantmentTable() {
        super(Collections.singletonList("mainmenu"), "Opens the enchantments main menu", "tbcenchant.mainmenu", true, "/tbcenchant mainmenu");
    }

    public void execute(CommandSender sender, String[] args) {
        Player p = (Player)sender;
        String world = p.getWorld().getName();
        if ((TBCEnchant.getInstance().getConfiguration()).disabledWorlds.contains(world))
            return;
        User user = User.getUser((OfflinePlayer)p);
        p.openInventory(user.getMainMenu(Utils.getBookShelfPower(p), true, null).getInventory());
    }


    public List<String> onTabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
