package fun.tbcraft.play;

import java.util.Locale;

public enum ResourceType {
    MANA, STAMINA, STELLIUM;

    private String getName(){
        return this.name().toUpperCase(Locale.ROOT);
    }
    public String getMultiplierPath(ClassType classType){
        return classType.getName() + "." + getName() + "." + "Multiplier";}

}
