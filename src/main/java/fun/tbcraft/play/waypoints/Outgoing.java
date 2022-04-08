package fun.tbcraft.play.waypoints;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.ConfigFile;
import net.Indyuce.mmocore.waypoint.CostType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Outgoing implements LoadingWaypoint {
    private final Map<String,String> serialized = new HashMap<>();
    private static final ConfigFile waypoints = new ConfigFile("waypoints");
    private final boolean api;
    private final double radius;
    private ConfigurationSection section;
    private String id = "";
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private String world = "";
    private Location base;

    Outgoing(String i){
        this.id = i;
        this.radius = 5;
        this.api = MMOCore.plugin.waypointManager.has(i);
        this.base = null;
        section = section.isConfigurationSection(i)?section.getConfigurationSection(i):waypoints.getConfig();
        secti
        if (section.isConfigurationSection(this.id)){
            if (serialized.isEmpty()){
                Set<String> keys = section.getKeys(false);
                for (String key : keys) {
                    if (section.isConfigurationSection(key)) {
                        ConfigurationSection subSection = section.getConfigurationSection(key);

                        if (subSection == null){
                            continue;
                        }
                        Set<String> subbed = subSection.getKeys(false);


                        for (String subKeyModel : subbed) {
                            if (subSection.contains(subKeyModel)){
                                Object gottenObject = section.get(subKeyModel);
                                String determine = gottenObject == null ? null : gottenObject instanceof Number n ? String.valueOf(n.doubleValue()) : gottenObject instanceof String word ? word : gottenObject instanceof Boolean option ? String.valueOf(option) : "";
                                serialized.put(subKeyModel, determine);
                            }
                        }
                    }
                    else {
                        serialized.put(key,section.getString(key));
                    }
                }
            }
        }
        else {
            radius = 0;

            //TODO- Configure Serialization to MySQL
            serialized.put("name", this.id);
            serialized.put("loc.x", this.x + "");
            serialized.put("loc.y", this.y + "");
            serialized.put("loc.z", this.z + "");
            serialized.put("loc.world", this.world);
            serialized.put("radius", radius + "");
            serialized.put("linked", "a b c");
            for (CostType value : CostType.values()) {
                serialized.put("cost." + value.getPath(), 5.0 + "");
            }
        }

        this.id = i;
        this.api = MMOCore.plugin.waypointManager.has(this.id);
    }
    public void setLocation(Location loc){

        if ( loc == null){
            return;
        }

        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
        this.world = loc.getWorld().getName();
        this.base = loc;

    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public int z() {
        return z;
    }

    @Override
    public World world() {
        return Bukkit.getWorld(world);
    }

    @Override
    public Location loc() {
        return new Location(world(),x,y,z);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void save() {

    }

    public boolean isApi() {
        return api;
    }
}
