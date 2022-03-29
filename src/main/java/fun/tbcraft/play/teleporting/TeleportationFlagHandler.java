package fun.tbcraft.play.teleporting;

import fun.tbcraft.play.TBCPlugin;
import me.playajames.flagslib.FlagManager;
import me.playajames.flagslib.flagtypes.Flag;
import org.bukkit.Location;

public class TeleportationFlagHandler {
    private final Flag flag;
    private final String id;
    private int uses = 0;

    public TeleportationFlagHandler(String id, Location location, String result){
        this.id = id;
        this.flag = FlagManager.createFlag(location,this.id,result,false);

    }
    private void save(){
        this.flag.setTemp();
    }
    public void increase(int byAmount){
        this.uses = uses + byAmount;
    }
    public void decrease(int amount){
        this.uses = uses - amount;
    }
    public void delete(){
        this.flag.delete();
    }
    public String getId(){
        return this.flag.getIdentifier();
    }
    public long getNumericId(){
        return this.flag.getId();
    }
    public void setTemp(int amount){
        this.flag.setTemp(amount);
    }
    public void setValue(String newVal){
        this.flag.setValue(newVal);
    }
    public boolean isTemp(){
        return this.flag.isTemp()>0;
    }
    public String getResult(){
        return this.flag.getValue();
    }
    private void debug(){
        TBCPlugin.debug(" - TeleportationFlagHandler - " + this.flag.toString());
    }
}
