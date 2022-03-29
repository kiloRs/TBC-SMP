package fun.tbcraft.play.teleporting;

import de.jeff_media.jefflib.AnimationUtils;
import de.jeff_media.jefflib.TextUtils;
import fun.tbcraft.play.TBCPlayer;
import me.playajames.flagslib.FlagManager;
import me.playajames.flagslib.flagtypes.Flag;
import me.playajames.flagslib.flagtypes.LocationFlag;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Location;

public class TeleportingManager {
    private final TBCPlayer tbcPlayer;
    private final PlayerData core;
    private final String m = "You have teleported at the cost of %s stellium";
    private Location teleportingTo;

    public TeleportingManager(TBCPlayer player, Location teleportingTo){
        this.teleportingTo = teleportingTo;
        this.tbcPlayer = player;
        this.core = player.getCorePlayerData();
    }

    private boolean spendCost(double stellium, double rate) {
        core.setStellium(stellium - rate);
        if (core.getStellium()>0){
            return true;
        }
        else {
            core.setStellium(stellium);
            return false;
        }
    }

    private boolean hasFlag(){
        return FlagManager.hasFlag(tbcPlayer.getStoredPlayer().getLocation(),"cheap-travel");

    }
    public Flag getFlag(){
        return FlagManager.getFlag(tbcPlayer.getStoredPlayer().getLocation(), "cheap-travel");
    }

    public boolean teleport(Location result){
        this.teleportingTo = result;
        double rating = (tbcPlayer.getStoredPlayer().getLocation().distanceSquared(result) * 5 - 7);
        if (spendCost(core.getStellium(), rating)) {
            tbcPlayer.getStoredPlayer().teleport(result);
            return true;
        }
        return false;
    }

    public Location getTeleportingTo() {
        return teleportingTo;
    }
}
