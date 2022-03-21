package fun.tbcraft.play;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;

public enum PlayerClassType {
    MAGE("Mage"),ROGUE("Rogue"),PALADIN("Paladin"),WARRIOR("Warrior"),HUNTER("Hunter");

    private final String id;
    private final Double stelliumMultiplier;
    private final double manaMultiplier;

    PlayerClassType(String id){
        final var s = TBCPlugin.getSettings();
        if ( !s.exists(id) ){
            s.setIfAbsent(id + ".Stellium",1, Arrays.stream(new String[]{"#Amount of Stellium Used By Class"}).toList());
            s.setIfAbsent(id + ".Mana",1, Arrays.stream(new String[]{"#Amount of Mana Used By Class"}).toList());
            s.save();
        }
        this.id = Validate.notNull(id,"Bad Class Name");
        this.stelliumMultiplier = s.exists(id + ".Stellium")? s.getDouble(id + ".Stellium"):1;
        this.manaMultiplier = s.exists(id + ".Mana")? s.getDouble(id + ".Mana"):1;
    }
    public boolean hasSettings(){
        return TBCPlugin.getSettings().exists(id);
    }

    public boolean isRanged(){
        return this==HUNTER||this==MAGE;
    }
    public boolean isTank(){
        return this==WARRIOR||this==PALADIN;
    }
    public boolean usesMagic(){
        return this==MAGE||this==PALADIN;
    }

    public PlayerClass get(){
        return MMOCore.plugin.classManager.getOrThrow(id);
    }

    public double getStelliumMultiplier ( ) {
        return stelliumMultiplier;
    }

    public double getManaMultiplier ( ) {
        return manaMultiplier;
    }
}