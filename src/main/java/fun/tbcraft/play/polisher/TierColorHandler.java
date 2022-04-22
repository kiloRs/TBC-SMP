package fun.tbcraft.play.polisher;

import de.jeff_media.jefflib.CollectionUtils;
import de.jeff_media.jefflib.EnumUtils;
import fun.tbcraft.play.TBCPlugin;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.inventivetalent.glow.GlowAPI;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TierColorHandler {
    private final Plugin p;
    private final boolean enabled = Bukkit.getPluginManager().isPluginEnabled("GlowAPI");

    public TierColorHandler(TBCPlugin p){
        this.p = p;

    }

    public boolean glow(Item i){
        return glow(i,4);
    }
    boolean glow(Item i, int y){
        return glow(i,10,y,10);
    }
    public boolean glowForAll(Item item, int x, int y, int z){
        return glow(item,true,x,y,z);
    }
    public boolean glowForAll(Item i){

        return glow(i,true,10,10,10);
    }
    boolean glow(Item i, int x, int y, int z){
        return glow(i,false,x,y,z);
    }
    boolean glow(Item item, boolean forAllPlayersWithin, int x, int y, int z){
        if (!enabled){
            throw new RuntimeException("Color Parser API Missing!");
        }
        GlowAPI.Color c = GlowAPI.Color.NONE;

        NBTItem nbtItem = NBTItem.get(item.getItemStack());
        if (nbtItem.hasTag("TBC_TIER")){
            String foundWording = nbtItem.getString("TBC_IER");

            Optional<GlowAPI.Color> color = EnumUtils.getIfPresent(GlowAPI.Color.class, foundWording);

            if (color.isEmpty()){
                return false;
            }
            c = color.get();
        }
        if (c== GlowAPI.Color.NONE){
            return false;
        }

        List<Entity> nearbyEntities = item.getNearbyEntities(x, y, z);

        Predicate<? super Entity> typesToFind = entity -> entity instanceof Player p;

        List<Player> players = CollectionUtils.createList((Player) null);
        players.clear();

        nearbyEntities.forEach(entity -> {
            if (typesToFind.test(entity)) {
                players.add(((Player) entity));
            }
        });
        GlowAPI.setGlowing(item, c, players);


       return GlowAPI.isGlowing(item, players, forAllPlayersWithin);
    }
}
