package fun.tbcraft.utils;

import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
import io.lumine.mythic.utils.holograms.BukkitHologramFactory;
import io.lumine.mythic.utils.holograms.Hologram;
import io.lumine.mythic.utils.numbers.RandomDouble;
import io.lumine.mythic.utils.serialize.Position;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HologramControl {
    private final Hologram hologram;
    private final BukkitHologramFactory hologramFactory = new BukkitHologramFactory();
    private final List<String> data = new ArrayList<>();
    private final Location location;

    public HologramControl(Location location){
        if (data.isEmpty()){
            addData();
        }
        this.location = location;
        hologram = hologramFactory.newHologram(Position.of(location), data);
    }

    public Hologram getHologram() {
        hologramFactory.newHologram(Position.of(location),data);
        return hologram;
    }

    public void setLines(String... words){
        data.clear();
        data.addAll(Arrays.stream(words).toList());
    }
    public void clearLines(){
        data.clear();
    }
    public List<String> getLines(){
        return data;
    }
    private void addData() {

        data.add("&aTesting");
        data.add("&eHologram");
        data.add("&cFactory");
    }
}
