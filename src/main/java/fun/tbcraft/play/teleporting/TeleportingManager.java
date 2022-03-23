package fun.tbcraft.play.teleporting;

import de.jeff_media.jefflib.AnimationUtils;
import de.jeff_media.jefflib.TextUtils;
import fun.tbcraft.play.TBCPlayer;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Location;

public class TeleportingManager {
    private final PlayerData core;
    private final Location location;
    private final String m = "You have teleported at the cost of %s stellium";
    private final Location teleportingTo;

    public TeleportingManager(TBCPlayer player, Location teleportingTo){
        this.teleportingTo = teleportingTo;
        core = player.getCorePlayerData();
        this.location= core.getPlayer().getLocation();
    }

    public void parse(){
        if (canTeleport()){
            core.getPlayer().teleport(teleportingTo);

        }
    }
    private boolean canTeleport() {

        double amount = teleportingTo.distanceSquared(location);



        double stellium = core.getStellium();

        double rate = amount * 5 + 7;

        if (stellium - rate < 0){
            return false;
        }

        spendCost(stellium, rate);
        return true;
    }

    private void spendCost(double stellium, double rate) {
        core.setStellium(stellium - rate);
    }
    private void teleport(boolean can){
        if (can){core.getPlayer().teleport(teleportingTo);}
        else {
            AnimationUtils.playTotemAnimation(core.getPlayer(),1);
            TextUtils.banner(TextUtils.color("&cFailure!"));
        }
    }

}
