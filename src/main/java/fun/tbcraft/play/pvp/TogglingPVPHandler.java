package fun.tbcraft.play.pvp;

import de.jeff_media.jefflib.EnumUtils;
import fun.tbcraft.play.TBCPlayer;
import fun.tbcraft.play.pvp.util.PVP;
import me.devtec.shared.dataholder.Config;
import net.kyori.adventure.util.TriState;
import org.bukkit.entity.Player;

import java.util.Optional;

public class TogglingPVPHandler {
    private final TBCPlayer tbcPlayer;
    private final Config savedDataOfPlayer;
    private static final String path = "PVP.Enabled";
    private static final String perm = "PVP.Toggle";
    private boolean enabled = true;
    private PVP pvpStatus = PVP.ENABLED;
    private DamageCauseHandler handler = null;

    public TogglingPVPHandler(Player player){
        this.tbcPlayer = TBCPlayer.get(player);
        this.savedDataOfPlayer = tbcPlayer.getUser();
        Optional<PVP> pvp;
        pvp = EnumUtils.getIfPresent(PVP.class,savedDataOfPlayer.getString(path));
        this.pvpStatus = pvp.orElse(PVP.ENABLED);
    }


    private boolean hasLimits(){
        if (pvpStatus==PVP.LIMITED){
            handler = DamageCauseHandler.getLimitedHandler();
            return true;
        }
        if (pvpStatus == PVP.VERY_LIMITED){
            handler = DamageCauseHandler.getVeryLimitedHandler();
            return true;
        }
        return false;
    }
    private void load(){
        PVP found = savedDataOfPlayer.getAs(path, PVP.class);

        if (found == null){
            throw new IllegalArgumentException("Invalid Argument for PVP State");
        }
        pvpStatus = found;
    }
    private void save(){
        savedDataOfPlayer.set(path,pvpStatus.name().toString());
        savedDataOfPlayer.save();
    }
    public void setPVP(PVP state){
        this.pvpStatus = state;
    }
    public boolean canChange(){
        return tbcPlayer.getStoredPlayer().hasPermission(perm) && tbcPlayer.getStoredPlayer().permissionValue(perm)== TriState.TRUE;
    }
}
