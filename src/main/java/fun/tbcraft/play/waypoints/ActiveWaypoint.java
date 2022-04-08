package fun.tbcraft.play.waypoints;

import me.devtec.shared.API;
import me.devtec.shared.dataholder.Config;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.waypoint.CostType;
import org.bukkit.entity.Player;

public class ActiveWaypoint {
    private final WaypointInstance instance;
    private final Player player;
    private final Config user;

    public ActiveWaypoint(Player player, WaypointInstance instance){
        this.instance = instance;
        this.player = player;
        this.user = API.getUser(player.getUniqueId());
    }

    public Config getUser() {
        return user;
    }

    public Player getPlayer() {
        return player;
    }

    public WaypointInstance getInstance() {
        return instance;
    }
    private boolean teleport(){
        double squared = player.getLocation().distanceSquared(instance.get());
        if (squared>5){
            if (instance.usesAPI()){
                if (PlayerData.get(player).hasWaypoint(instance.getWaypoint())) {
                    PlayerData.get(player).warp(instance.getWaypoint(), CostType.NORMAL_USE);
                }
            }
            return player.teleport(instance.get());
        }
        return false;
    }
}
