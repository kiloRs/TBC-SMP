package fun.tbcraft.play;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import de.jeff_media.jefflib.data.ShadowPlayer;
import fun.tbcraft.play.utils.BodyPartsAPI;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.util.EnumUtils;
import me.devtec.shared.API;
import me.devtec.shared.dataholder.Config;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

/**
 * Instance of a Player on the TBC Server. Easy to control from one class.
 */
public class TBCPlayer {
    private final Config user;
    private PlayerResourcePackStatusEvent.@Nullable Status resourcePackStatus;
    private Player storedPlayer;
    private MMOPlayerData mmoPlayerData;
    private net.Indyuce.mmocore.api.player.PlayerData corePlayerData;
    private net.Indyuce.mmoitems.api.player.PlayerData itemsPlayerData;
    private boolean fullyLoaded = false;
    private PlayerClassType classType;
    private int score = 0;

    public static TBCPlayer get(UUID uuid){
        return new TBCPlayer(uuid);
    }
    public static TBCPlayer get(Player p){
        return new TBCPlayer(p.getUniqueId());
    }
    public TBCPlayer(UUID id){
        UUID uuid = Validate.notNull(id, "Invalid UUID Number");

        this.storedPlayer = new ShadowPlayer(Bukkit.getPlayer(uuid)!=null?Bukkit.getPlayer(uuid):null);
        this.mmoPlayerData = MMOPlayerData.get(uuid);
        this.corePlayerData = net.Indyuce.mmocore.api.player.PlayerData.get(uuid);
        this.itemsPlayerData = net.Indyuce.mmoitems.api.player.PlayerData.get(uuid);
        this.fullyLoaded = true;
        this.classType = EnumUtils.getIfPresent(PlayerClassType.class , corePlayerData.getProfess().getId()).orElseThrow(( ) -> new RuntimeException("Bad Class Type Data"));
        this.resourcePackStatus = storedPlayer.getResourcePackStatus();
        user = API.getUser(storedPlayer.getUniqueId());
        fullyLoaded = true;
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
        return classType.get();
    }
    public PlayerClassType getPlayerClassType(){
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

    private boolean hasHome(){
        return false;
    }
    private void setHome(){}

    public Config getUser() {
        return user;
    }
}
