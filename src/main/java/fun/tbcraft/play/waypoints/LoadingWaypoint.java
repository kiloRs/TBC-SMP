package fun.tbcraft.play.waypoints;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.ConfigFile;
import net.Indyuce.mmocore.waypoint.Waypoint;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public interface LoadingWaypoint {
    public static FileConfiguration waypoints = new ConfigFile("waypoints").getConfig();
    public int x();
    public int y();
    public int z();
    public World world();
    public Location loc();
    public String getName();

    public default void register(){
        if (!MMOCore.plugin.waypointManager.has(getName())) {
            MMOCore.plugin.waypointManager.register(new Waypoint(waypoints));
            return;
        }
        throw new RuntimeException("Already Registered as Waypoint! " + this.getName());
    }
    public void save();
}
