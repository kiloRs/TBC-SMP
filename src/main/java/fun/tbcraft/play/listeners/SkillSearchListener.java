package fun.tbcraft.play.listeners;

import net.Indyuce.mmocore.api.event.PlayerKeyPressEvent;
import net.Indyuce.mmocore.skill.cast.PlayerKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkillSearchListener implements Listener {
    private boolean canUse = false;

    @EventHandler
    public void onUse(PlayerKeyPressEvent e){
        PlayerKey playerKey = e.getPressed();

        if (playerKey==PlayerKey.SWAP_HANDS){
            o
        }

    }
}
