package fun.tbcraft.play.hotspot;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public abstract class Serializable {
    public Serializable(){

    }
    public abstract Map<String,String> getSerialized();
    public abstract void serialize();
    public abstract boolean canSerialize();
    public abstract ConfigurationSection getSection();
    public abstract boolean inFile();
}
