package fun.tbcraft.play.waypoints;

import fun.tbcraft.play.TBCPlugin;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.ConfigFile;
import net.Indyuce.mmocore.waypoint.CostType;
import net.Indyuce.mmocore.waypoint.Waypoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nullable;
import java.util.*;

public class WaypointInstance {
    private final List<String> linked = new ArrayList<>();
    private final String id;
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private String world;
    private Map<String,String> serialized = new HashMap<>();
    private boolean usesAPI = false;
    private final ConfigFile waypoints = new ConfigFile("waypoints");
    @org.jetbrains.annotations.Nullable ConfigurationSection section;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String,WaypointInstance> mapData = new HashMap<>();
    private Waypoint waypoint;

    public WaypointInstance(String name, Location loc, @Nullable List<String> specifics, boolean newInst) {
        this.id = name;
        if (hasCoreWaypoint()) {
            waypoint = MMOCore.plugin.waypointManager.get(this.id);
            if (waypoint.getLocation().getBlockX() == getX() && waypoint.getLocation().getBlockY() == getY() && waypoint.getLocation().getBlockZ() == getZ() && waypoint.getLocation().getWorld().getName().equalsIgnoreCase(getWorld())) {
                waypoint = MMOCore.plugin.waypointManager.get(this.id);
                usesAPI = true;
            } else {
                waypoint = null;
            }
        }
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
        this.world = loc.getWorld().getName();
        section = waypoints.getConfig().isConfigurationSection(this.id) ? waypoints.getConfig().getConfigurationSection(this.id) : waypoints.getConfig().createSection(this.id, serialized);
        boolean onLoading = false;
        double radius = 0;
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
        serialized.put("api", String.valueOf(usesAPI));
        if (waypoint == null  && newInst) {
            if (section != null) {
                waypoint = new Waypoint(section);
                try {
                    MMOCore.plugin.waypointManager.register(waypoint);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (newInst) {
            usesAPI = waypoint != null;
        }
        section = waypoints.getConfig().isConfigurationSection(this.id) ? waypoints.getConfig().getConfigurationSection(this.id) : waypoints.getConfig().createSection(this.id, serialized);


        if (section == null){
            throw new RuntimeException("Section: " + this.id + " not found in " + waypoints.getConfig().getCurrentPath());
        }
        if (waypoints.exists()) {
            if (!waypoints.getConfig().getKeys(false).contains(this.id)) {
                TBCPlugin.log("Waypoint Keys Not Loaded");
            } else {
                for (String key : section.getKeys(false)) {
                    String wording = "";
                    if (section.isDouble(key)) {
                        double sectionDouble = section.getDouble(key);
                        wording = sectionDouble + "";
                    } else if (section.isString(key)) {
                        wording = section.getString(key);

                    }
                    TBCPlugin.log("Data For Source: " + this.id + " [" + wording + "]");
                }
            }
        }

        if (waypoints.getConfig().isConfigurationSection(this.id) && section.getKeys(false).size() > 0 && !serialized.isEmpty()) {
            mapData.put(this.id, this);
        } else if (serialized.isEmpty()) {
            mapData.put(this.id, this);
        }


        if (waypoints.getConfig().getKeys(false).contains(id) && onLoading) {
            radius = Double.parseDouble(serialized.get("radius"));
            this.x = Integer.parseInt(serialized.get("loc.x"));
            this.y = Integer.parseInt(serialized.get("loc.y"));
            this.z = Integer.parseInt(serialized.get("loc.z"));
            this.world = serialized.get("world");
            String parsingLinked = serialized.get("linked");

            String[] toSplit = parsingLinked.split(" ");

            linked.addAll(Arrays.asList(toSplit));
        }
    }

    public boolean hasKey(){
        return waypoints.getConfig().contains(this.id)||waypoints.getConfig().getKeys(false).contains(this.id);
    }
    public Map<String,String> loadKeys(){
        Map<String,String> subSerialized = new HashMap<>();
        if (hasKey()){
            if (waypoints.getConfig().isConfigurationSection(this.id)) {
                section = waypoints.getConfig().getConfigurationSection(this.id);

                if (section == null){
                    return new HashMap<>();
                }
                for (String key : section.getKeys(false)) {
                    if (section.isConfigurationSection(key)){
                        ConfigurationSection subKey = section.getConfigurationSection(key);
                        for (String subKeyKey : subKey.getKeys(false)) {
                            Object o = section.get(key + "." + subKeyKey);

                            if (o instanceof String s){
                                subSerialized.put(key + "." + subKeyKey,s);
                            }


                        }
                    }
                    else {
                        String dataFound = section.getString(key);
                        subSerialized.put(key,dataFound);
                    }
                }
            }
        }
        return subSerialized;
    }

    private boolean hasCoreWaypoint(){
        return MMOCore.plugin.waypointManager.has(this.id) && MMOCore.plugin.waypointManager.get(this.id) != null;
    }

    public boolean usesAPI() {
        return usesAPI;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }
    public Location get(){
        return new Location(Bukkit.getWorld(this.world),this.x,this.y,this.z);
    }
    public Waypoint getWaypoint(){
        if (!usesAPI){
            waypoint.
        }
        return MMOCore.plugin.waypointManager.get(this.id);
    }

    public Map<String, String> getSerialized() {
        return serialized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaypointInstance)) return false;
        WaypointInstance that = (WaypointInstance) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
