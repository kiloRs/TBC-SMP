package fun.tbcraft.play.hotspot;

import fun.tbcraft.play.TBCPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WaypointInstance extends Serializable implements BasicWaypoint{

    private @Nullable ConfigurationSection configuration = TBCPlugin.getPlugin().getConfig();
    private String id;
    private  String name = "";
    private Location loc;
    private double rate = 1.0;
    private boolean stored = false;
    private HashMap<String, String> map = new HashMap<>();
    private String perm = "";
    public WaypointInstance(String id, Location loc){
        if (id.contains("-")){
            this.id = id.split("-")[0];
            this.rate = Double.parseDouble(id.split("-")[1]);
        }
        else {
            this.id = id;
        }
        this.loc = loc;
        this.name = id;

        String path = "Waypoint." + this.id;
        if (!configuration.isConfigurationSection(path)){
            add(loc);
            return;
        }
        else {
            configuration = configuration.isConfigurationSection(path)?configuration.getConfigurationSection(path):null;
            stored = true;

            if (getSection() == null || getSection().getName().equalsIgnoreCase(TBCPlugin.getPlugin().getConfig().getName())){
                add(loc);
                configuration = TBCPlugin.getPlugin().getConfig();
                configuration = configuration.createSection("Waypoint." + this.id,getSerialized());
                return;
            }
            Set<String> keys = configuration.getKeys(false);

            for (String key : keys) {g
                if (getSection().isConfigurationSection(key)) {
                    ConfigurationSection sub = getSection().getConfigurationSection(key);

                    Set<String> subKeys = sub != null ? sub.getKeys(false) : null;

                    if (subKeys == null){
                        continue;
                    }
                    for (String subKey : subKeys) {
                        if (sub.isConfigurationSection(subKey)) {
                            ConfigurationSection small = sub.getConfigurationSection(subKey);

                            if (small == null){
                                continue;
                            }
                            if (small.getKeys(false).isEmpty()){
                                continue;
                            }

                            //End Sub Sub Check
                        }
                        getSerialized().put(key + "." + subKey,sub.getString(key + "." + subKey));

                        //End Serialized Sub Check
                    }

                    //End Configuration Check
                }
                String found = getSection().getString(key);
                getSerialized().put(key,found);
            }
            return;
        }


    }

    private void add(Location loc) {
        getSerialized().clear();
        getSerialized().put("name",name);
        getSerialized().put("rate",String.valueOf(rate));
        getSerialized().put("loc.x",String.valueOf(loc.getBlockX()));
        getSerialized().put("loc.y",String.valueOf(loc.getBlockY()));
        getSerialized().put("loc.z",String.valueOf(loc.getBlockZ()));
        getSerialized().put("loc.world",String.valueOf(loc.getWorld().getName()));
        getSerialized().put("perm.required","false");
        getSerialized().put("perm.value","");
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRate() {
        return 0;
    }

    @Override
    public boolean needsPermission() {
        return !perm.equalsIgnoreCase("");
    }

    @Override
    public Map<String, String> getSerialized() {
        return map;
    }

    @Override
    public void serialize() {
        if (configuration == null){
            getSerialized().put("name",name);
            configuration = TBCPlugin.getPlugin().getConfig().createSection("Waypoint." + this.id,getSerialized());
        }
        map.forEach((s, serializable) -> configuration.set(s,serializable));
    }

    @Override
    public boolean canSerialize() {
        return !map.isEmpty();
    }

    @Override
    public ConfigurationSection getSection() {
        return configuration==null?TBCPlugin.getPlugin().getConfig():configuration;
    }

    @Override
    public boolean inFile() {
        if (configuration == null){
            throw new RuntimeException("Invalid Configuration For Infile");
        }
        return configuration.isConfigurationSection("Waypoint." + this.id);
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Location getLoc() {
        return loc;
    }
}
