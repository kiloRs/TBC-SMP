package fun.tbcraft.play.home;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import de.jeff_media.jefflib.ProfileUtils;
import fun.tbcraft.play.TBCPlayer;
import fun.tbcraft.play.TBCPlugin;
import io.lumine.mythic.core.utils.UUIDUtil;
import me.devtec.shared.dataholder.Config;
import net.Indyuce.mmocore.api.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Home {


    private final TBCPlayer player;
    private final Config playerUser;
    private boolean loadedChunks = false;
    private boolean hasChunks = false;
    private Region region;
    private World world;
    public List<Chunk> chunks = new ArrayList<>();

    public Home(TBCPlayer player){
        this.player = player;
        this.playerUser = this.player.getUser();
        this.loadedChunks = false;

        if (playerUser.exists("Home.Chunks")) {
            List<String> list = playerUser.getStringList("Home.Chunks");
            loadAllChunks(player, list);
        }
        world = player.getStoredPlayer().hasMetadata("HomeWorld")? Bukkit.getWorld(player.getStoredPlayer().getMetadata("HomeWorld").get(0).asString()) :playerUser.exists("Home.World")?Bukkit.getWorld(playerUser.getString("Home.World")):Bukkit.getWorld("") ;

        if (world == null){
            throw new RuntimeException("Invalid World For Chunks of " + player.getStoredPlayer().getName());
        }
        com.sk89q.worldedit.world.World convertedWorld = BukkitAdapter.adapt(world);
        region = new CuboidRegion(convertedWorld,Bu)
    }

    private void loadAllChunks(TBCPlayer player, List<String> list) {
        for (String name : list) {
            String world = name.split(";")[0];
            String xAndY = name.split(";")[1];
            String x = xAndY.split(",")[0];
            String z = xAndY.split(",")[1];

            World w = Bukkit.getWorld(world);
            if (w == null){
                continue;
            }

            Chunk chunkAt = w.getChunkAt(Integer.parseInt(x), Integer.parseInt(z));

            String data = chunkAt.getPersistentDataContainer().getOrDefault(new NamespacedKey(TBCPlugin.getPlugin(), "home"), PersistentDataType.STRING, "");
            if (!data.isEmpty()){
                if (!ProfileUtils.isValidUUID(data)) {
                    throw new RuntimeException("Invalid UUID FOUND");
                }
                UUID uuidFromString = ProfileUtils.getUUIDFromString(data);

                if (!uuidFromString.equals(player.getStoredPlayer().getUniqueId())){
                    TBCPlugin.log("-Non Matching UUID Found for Chunk at " + x + " and " + z);
                }
                if (!chunkAt.getPluginChunkTickets().contains(TBCPlugin.getPlugin())){
                    chunkAt.addPluginChunkTicket(TBCPlugin.getPlugin());
                }
            }
            chunks.add(chunkAt);
        }
        if (chunks.size()>0){
            hasChunks = true;
        }
        loadedChunks = true;
    }

    public List<Chunk> getChunks(){
        return chunks;
    }
    public Player getPlayer(){
        return player.getStoredPlayer();
    }

    public Config getPlayerUser() {
        return playerUser;
    }

    public boolean hasChunks() {
        return hasChunks;
    }
    public boolean finishedLoadingChunks(){
        return loadedChunks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Home home)) return false;
        return getPlayer().getUniqueId().equals(home.getPlayer().getUniqueId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer());
    }
}
