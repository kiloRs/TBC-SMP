package psikuvit.tbcenchant.cms;

import org.bukkit.command.CommandSender;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super(Collections.singletonList("reload"), "Reload all config files", "", false, "/tbcenchant reload");
    }

    public void execute(CommandSender cs, String[] args) {
        if (args.length == 1) {
            TBCEnchant.getInstance().reloadConfigs();
            cs.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("reloaded").replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
        } else {
            cs.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("invalidArguments").replace("%prefix%", (TBCEnchant.getInstance().getConfiguration()).prefix)));
        }
    }

    public List<String> onTabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
