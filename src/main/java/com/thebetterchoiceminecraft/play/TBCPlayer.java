package com.thebetterchoiceminecraft.play;

import com.thebetterchoiceminecraft.play.utils.ClassType;
import com.thebetterchoiceminecraft.utils.TBCConfigFile;
import de.jeff_media.jefflib.data.ShadowPlayer;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.util.EnumUtils;
import lombok.Getter;
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
    @Getter
    private final Config user;
    private final PlayerResourcePackStatusEvent.@Nullable Status resourcePackStatus;
    private final Player storedPlayer;
    private final MMOPlayerData mmoPlayerData;
    private final net.Indyuce.mmocore.api.player.PlayerData corePlayerData;
    private final net.Indyuce.mmoitems.api.player.PlayerData itemsPlayerData;
    private boolean fullyLoaded = false;
    private final ClassType classType;


    //Special Ones
    private final TBCConfigFile tbcConfigFile;


    public static TBCPlayer get(UUID uuid){
        return new TBCPlayer(uuid);
    }
    public static TBCPlayer get(Player p){
        return new TBCPlayer(p.getUniqueId());
    }
    public static TBCPlayer get(String name){
        return new TBCPlayer(Bukkit.getPlayerUniqueId(name));
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
        this.tbcConfigFile = new TBCConfigFile(this);
        //TODO Phase out this user, in replacement of our own user system. [This uses THEAPI]
        user = API.getUser(storedPlayer.getUniqueId());



        }



    public TBCConfigFile getUserSavedData() {
        return tbcConfigFile;
    }
    public net.Indyuce.mmoitems.api.player.PlayerData getMMOItemsPlayerData ( ) {
        return itemsPlayerData;
    }

    public net.Indyuce.mmocore.api.player.PlayerData getMMOCorePlayerData ( ) {
        return corePlayerData;
    }

    public MMOPlayerData getMythicLibPlayerData ( ) {
        return mmoPlayerData;
    }

    /**
     * @return The MMOCore PlayerClass class
     */
    public PlayerClass getPlayerClass(){
        return classType.getPlayerClass();
    }

    /**
     * @return Our ENUM of Classes
     */
    public ClassType getPlayerClassType(){
        return classType;
    }

    /**
     * @return Basic Player
     */
    public Player getStoredPlayer(){
        return storedPlayer;
     }

    /**
     * @return Resource Pack Status
     */
    public PlayerResourcePackStatusEvent.@Nullable Status getResourcePackStatus() {
        return resourcePackStatus;
    }

    //Checks if initialized already.
    public boolean isFullyLoaded() {
        return fullyLoaded;
    }

}
