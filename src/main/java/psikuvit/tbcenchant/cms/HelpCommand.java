package psikuvit.tbcenchant.cms;

import net.md_5.bungee.api.chat.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.Collections;
import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(Collections.singletonList("help"), "Displays the plugin's command", "", true, "/tbcenchant help");
    }

    public void execute(CommandSender cs, String[] args) {
        Player p = (Player)cs;
        int page = 1;
        if (args.length == 2) {
            if (!StringUtils.isNumeric(args[1]))
                return;
            page = Integer.parseInt(args[1]);
        }
        int maxpage = (int)Math.ceil((TBCEnchant.getInstance().getCommandManager()).commands.size() / 18.0D);
        int current = 0;
        p.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("helpHeader")));
        for (Command command : (TBCEnchant.getInstance().getCommandManager()).commands) {
            if ((p.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("") || command.getPermission().equalsIgnoreCase("tbcenchant.")) && command.isEnabled()) {
                if (current >= (page - 1) * 18 && current < page * 18)
                    p.sendMessage(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("helpMessage").replace("%command%", command.getAliases().get(0)).replace("%description%", command.getDescription())));
                current++;
            }
        }
        BaseComponent[] components = TextComponent.fromLegacyText(Utils.color(TBCEnchant.getInstance().getMessages().getMessage("helpfooter").replace("%maxpage%", maxpage + "").replace("%page%", page + "")));
        for (BaseComponent component : components) {
            if (ChatColor.stripColor(component.toLegacyText()).contains(TBCEnchant.getInstance().getMessages().getMessage("nextPage"))) {
                if (page < maxpage) {
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tbcenchant help " + (page + 1)));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(TBCEnchant.getInstance().getMessages().getMessage("helpPageHoverMessage").replace("%page%", "" + (page + 1)))).create()));
                }
            } else if (ChatColor.stripColor(component.toLegacyText()).contains(TBCEnchant.getInstance().getMessages().getMessage("previousPage")) &&
                    page > 1) {
                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tbcenchant help " + (page - 1)));
                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(TBCEnchant.getInstance().getMessages().getMessage("helpPageHoverMessage").replace("%page%", "" + (page - 1)))).create()));
            }
        }
    }


    public List<String> onTabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
