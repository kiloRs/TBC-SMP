package psikuvit.tbcenchant.cms;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.Enchs.TBCEnchants;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnchantItemCommand extends Command {
    public EnchantItemCommand() {
        super(Collections.singletonList("enchant"), "To enchant item in your hand", "tbcenchant.enchant", true, "/tbcenchant enchant <ID> <Level>");
    }

    public void execute(CommandSender sender, String[] args) {
        Player p = (Player)sender;
        if (args.length == 3) {
            try {
                ItemStack result = p.getItemInHand();
                if (result != null && result.getType() != Material.AIR) {
                    String name = args[1];
                    int level = Integer.parseInt(args[2]);
                    if ((TBCEnchant.getInstance().getHyperEnchantments()).enchantments.containsKey(name)) {
                        TBCEnchants tbcenchant = (TBCEnchants)(TBCEnchant.getInstance().getHyperEnchantments()).enchantments.get(name);
                        if (!result.getType().toString().contains("BOOK") && !tbcenchant.itemCanBeEnchanted(result)) {
                            p.sendMessage(Utils.color((TBCEnchant.getInstance().getConfiguration()).prefix + "&7Incompatible enchantment!"));
                            return;
                        }
                        if (level > tbcenchant.getMaxLevel()) {
                            p.sendMessage(Utils.color((TBCEnchant.getInstance().getConfiguration()).prefix + "&7Level can't be higher than enchantment max level!"));
                        } else {
                            p.getInventory().setItemInHand(TBCEnchant.getInstance().getApi().enchantItem(result, tbcenchant, level, false));
                        }
                    } else {
                        p.sendMessage(Utils.color((TBCEnchant.getInstance().getConfiguration()).prefix + "&7Invalid Enchantment!"));
                    }
                } else {
                    p.sendMessage(Utils.color((TBCEnchant.getInstance().getConfiguration()).prefix + "&7You must have an Item in your hands!"));
                }
            } catch (NumberFormatException e) {
                p.sendMessage(Utils.color((TBCEnchant.getInstance().getConfiguration()).prefix + "&7Invalid Level!"));
            }
        } else {
            p.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("invalidArguments").replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
        }
    }

    public List<String> onTabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        if (args.length == 2)
            return new ArrayList<>((TBCEnchant.getInstance().getHyperEnchantments()).enchantments.keySet());
        return null;
    }
}
