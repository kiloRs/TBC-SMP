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

public class WaypointInstanceHolder {
    private static final ConfigFile waypoints = new ConfigFile("waypoints");
    private ConfigurationSection section;
    private String id = "";
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private String world = "";
    private final Map<String,String> serialized = new HashMap<>();
    private Location bellAir = null;
    private boolean forceLoad = false;
    private boolean useAPI = false;
    private final List<String> destinations = new ArrayList<>();
    private Object radius;

    public WaypointInstanceHolder(String freshPrince) {
        this.id = freshPrince.startsWith("-")?freshPrince.split("-")[1]:freshPrince;
        if (freshPrince.startsWith("-")){
            forceLoad = true;
        }
        section = waypoints.getConfig().isConfigurationSection(this.id) ? waypoints.getConfig().getConfigurationSection(this.id) :null;
        if (section == null){
            serialize(forceLoad);
        }

        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.useAPI = isStoredInMMOCore();

        //TODO Complete Loader
    }

    public void setLocation(Location bellAir, boolean override){
        if (this.bellAir != null && override){
            this.z = this.bellAir.getBlockZ();
            this.y = this.bellAir.getBlockY();
            this.x =  this.bellAir.getBlockX();
            this.world = this.bellAir.getWorld().getName();
            this.radius = serialized.containsKey("radius")?Integer.parseInt(serialized.get("radius")):5;
            return;
        }
        this.z = bellAir.getBlockZ();
        this.x = bellAir.getBlockX();
        this.y = bellAir.getBlockY();
        this.world=bellAir.getWorld().getName();
        this.radius = serialized.containsKey("radius")?Integer.parseInt(serialized.get("radius")):5;


    }
    private Location getSavedLocation(){
        if (section == null){
            return null;
        }
        if (!serialized.isEmpty()){
            TBCPlugin.log("Saved Location Failure!");
        }

        if (section !=null && !section.getKeys(false).isEmpty()) {
            String worldString = section.getString("loc.world");
            String xS = section.getString("loc.x");
            String yS = section.getString("loc.y");
            String zS = section.getString("loc.z");

            if (xS == null || yS == null || zS == null || worldString == null){
                throw new RuntimeException("Invalid Arguments!");
            }
            return new Location(Bukkit.getWorld(worldString), Double.parseDouble(xS), Double.parseDouble(yS), Double.parseDouble(zS));
        }
        return null;
    }
    private Location getSerializedLocation(){return new Location(Bukkit.getWorld(serialized.get("loc.world")),Double.parseDouble(serialized.get("loc.x")),Double.parseDouble(serialized.get("loc.y")),Double.parseDouble(serialized.get("loc.z")));}
    private boolean isStoredInMMOCore(){
        return MMOCore.plugin.waypointManager.has(this.id);
    }
    private boolean hasDestination(String i){
        return destinations.contains(i);
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public Waypoint getWaypoint(){
        return getWaypoint(section.contains(this.id + "." + "name")&&section.isConfigurationSection("loc")&&section.isConfigurationSection("linked")&& !forceLoad);
    }
    private @Nullable Waypoint getWaypoint(boolean create){
        if (isStoredInMMOCore()){
            return MMOCore.plugin.waypointManager.get(this.id);
        }
        if (create){
            if (!isStoredInMMOCore()){
                try {
                    MMOCore.plugin.waypointManager.register(new Waypoint(section));
                    return MMOCore.plugin.waypointManager.get(this.id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
    private void serialize(boolean force){
        int load = 0;
        boolean onLoading = force;
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

        if (section == null){
            section = waypoints.getConfig().isConfigurationSection(this.id)?waypoints.getConfig().getConfigurationSection(this.id):waypoints.getConfig().createSection(this.id,serialized);
        }
        if (section == null){
            throw new RuntimeException("Bad Config for Waypoint");
        }
        if (forceLoad){
            onLoading = true;

            for (String key : section.getKeys(false)) {
                 if (!serialized.containsKey(key)){
                     serialized.put(key,section.getString(key));
                 }


                 if (key.equalsIgnoreCase("linked")){

                     String string = section.getString(key, "test testtwo");

                     if (string.contains(" ")){
                         String[] o = string.split(" ");
                         if (!destinations.isEmpty()){
                             try {
                                 destinations.clear();
                                 TBCPlugin.log("Destination Update: " + key);
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                         }
                         destinations.addAll(Arrays.asList(o));
                     }
                 }

                 if (section.isConfigurationSection(key)){
                     ConfigurationSection locOrOther = section.getConfigurationSection(key);

                     if ( locOrOther == null){
                         throw new RuntimeException("Bad Sub Key");
                     }
                     Set<String> subKeys = locOrOther.getKeys(false);

                     if (subKeys.size()>0){
                         for (String sub : subKeys) {
                             if (!serialized.containsKey(key + "." + sub)) {
                                 serialized.put(key + "." + sub,section.getString(key + "." + sub));
                             }
                         }
                     }
                 }
            }
        }

        if (onLoading){
            String xString = section.getString("loc.x");
            this.x = Integer.parseInt(xString!=null?xString:"0");
            String yString = section.getString("loc.y");
            String zString = section.getString("loc.z");
            this.y = Integer.parseInt(yString!=null?yString:"0");
            this.z = Integer.parseInt(zString!=null?zString:"0");
            this.world = section.getString("loc.world");


        }
    }
}
