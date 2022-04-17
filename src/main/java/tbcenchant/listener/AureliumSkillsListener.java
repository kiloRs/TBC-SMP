package psikuvit.tbcenchant.listener;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.configuration.OptionL;
import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.skills.Skills;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import psikuvit.tbcenchant.API.Events.Enchant;
import psikuvit.tbcenchant.TBCEnchant;

public class AureliumSkillsListener implements Listener {
    private final TBCEnchant plugin;

    public AureliumSkillsListener(TBCEnchant plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBrew(Enchant event) {
        Player player = event.getPlayer();
        if (player == null)
            return;
        if (!OptionL.isEnabled((Skill) Skills.ENCHANTING))
            return;
        if ((this.plugin.getConfiguration()).aurelliumXP < 1.0D)
            return;
        AureliumAPI.addXp(player, (Skill)Skills.valueOf((this.plugin.getConfiguration()).aurelliumSkill), (this.plugin.getConfiguration()).aurelliumXP);
    }
}
