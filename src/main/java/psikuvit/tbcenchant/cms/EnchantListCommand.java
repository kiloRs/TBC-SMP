package psikuvit.tbcenchant.cms;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.Collections;
import java.util.List;

public class EnchantListCommand extends Command {
    public EnchantListCommand() {
        super(Collections.singletonList("list"), "Shows enchantments list", "tbcenchant.list", true, "/tbcenchant list");
    }

    public void execute(CommandSender sender, String[] args) {
        Player p = (Player)sender;
        p.sendMessage(Utils.color("&6&lEnchantments List"));
        for (TBCEnchants tbcenchant : (TBCEnchant.getInstance().getHyperEnchantments()).enchantments.values())
            p.sendMessage(Utils.color("&6&eID: " + tbcenchant.getEnchantmentName()));
        p.sendMessage(Utils.color("&6============================="));
    }


    public List<String> onTabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
