package fun.tbcraft.play;

import de.jeff_media.jefflib.data.ShadowPlayer;
import fun.tbcraft.play.economy.ClassType;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.util.EnumUtils;
import me.devtec.shared.API;
import me.devtec.shared.dataholder.Config;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Instance of a Player on the TBC Server. Easy to control from one class.
 */
public class TBCPlayer {
    private final Config user;
    private final PlayerResourcePackStatusEvent.@Nullable Status resourcePackStatus;
    private final Player storedPlayer;
    private final MMOPlayerData mmoPlayerData;
    private final net.Indyuce.mmocore.api.player.PlayerData corePlayerData;
    private final net.Indyuce.mmoitems.api.player.PlayerData itemsPlayerData;
    private boolean fullyLoaded = false;
    private final ClassType classType;


    //Special Ones

    private final double stelliumMultiplier = 1.0;
    private final int tokensRemaining = 0;
    private final int tokensOffered = 0;


    public static TBCPlayer get(UUID uuid){
        return new TBCPlayer(uuid);
    }
    public static TBCPlayer get(Player p){
        return new TBCPlayer(p.getUniqueId());
    }
    public void setLoaded(boolean state){
        this.fullyLoaded = state;
    }
    public TBCPlayer(UUID id){
        UUID uuid = Validate.notNull(id, "Invalid UUID Number");


        Player player = Bukkit.getPlayer(uuid);
        if (player == null){
            throw new RuntimeException("Bad Player!");
        }
        this.storedPlayer = new ShadowPlayer(player);
        this.mmoPlayerData = MMOPlayerData.get(uuid);
        this.corePlayerData = net.Indyuce.mmocore.api.player.PlayerData.get(uuid);
        this.itemsPlayerData = net.Indyuce.mmoitems.api.player.PlayerData.get(uuid);
        this.fullyLoaded = true;
        this.classType = EnumUtils.getIfPresent(ClassType.class , corePlayerData.getProfess().getId()).orElseThrow(( ) -> new RuntimeException("Bad Class Type Data"));
        this.resourcePackStatus = storedPlayer.getResourcePackStatus();
        user = API.getUser(storedPlayer.getUniqueId());


    }


    public net.Indyuce.mmoitems.api.player.PlayerData getItemsPlayerData ( ) {
        return itemsPlayerData;
    }

    public net.Indyuce.mmocore.api.player.PlayerData getCorePlayerData ( ) {
        return corePlayerData;
    }

    public MMOPlayerData getMmoPlayerData ( ) {
        return mmoPlayerData;
    }

    public PlayerClass getPlayerClass(){
        return classType.getPlayerClass();
    }
    public ClassType getPlayerClassType(){
        return classType;
    }

    public Player getStoredPlayer(){
        return storedPlayer;
     }

    public PlayerResourcePackStatusEvent.@Nullable Status getResourcePackStatus() {
        return resourcePackStatus;
    }

    //Checks if initialized already.
    public boolean isFullyLoaded() {
        return fullyLoaded;
    }

    public Config getUser() {
        return user;
    }

}
