package fun.tbcraft.play.economy;

import fun.tbcraft.play.TBCPlayer;
import fun.tbcraft.play.TBCPlugin;
import org.bukkit.metadata.FixedMetadataValue;

public class TokenUser {
    private int tokens = 0;
    private final TBCPlayer player;
    private boolean extraMode ;

    public TokenUser(TBCPlayer p){
        this.player = p;
        this.tokens = player.getStoredPlayer().hasMetadata("tokens")?player.getStoredPlayer().getMetadata("tokens").get(0).asInt():p.getUser().exists("tokens.available")?p.getUser().getInt("tokens.available"):0;
        this.extraMode = player.getUser().exists("tokens.extra")&&p.getUser().getBoolean("tokens.extra");
    }

    public void setExtraMode(boolean state){
        this.player.getUser().set("tokens.extra",state);
        this.player.getUser().save();
        this.extraMode = state;
    }
    public int getTokens() {
        return tokens;
    }
    public void setTokens(int newAmount){
        this.player.getUser().set("tokens.available",newAmount);
        this.player.getStoredPlayer().setMetadata("tokens",new FixedMetadataValue(TBCPlugin.getPlugin(),newAmount));
        this.player.getUser().save();
    }

    public boolean hasEnough(int req){
        return tokens>=req;
    }

    public boolean spend(int mount){
        if (hasEnough(mount)){
            try {
                setTokens(tokens - mount);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private void save(){
        this.player.getUser().set("tokens.available",tokens);
        this.player.getStoredPlayer().setMetadata("tokens",new FixedMetadataValue(TBCPlugin.getPlugin(),tokens));

    }

    public boolean hasExtraMode() {
        return extraMode;
    }
}
