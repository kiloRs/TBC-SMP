package com.thebetterchoiceminecraft.play.utils;

import java.util.Locale;

public enum ResourceType {
    MANA, STAMINA, STELLIUM, FLIGHT,TOKENS;

    private String getName(){
        return this.name().toUpperCase(Locale.ROOT);
    }
    public String getMultiplierPath(ClassType classType){
        return classType.getName() + "." + getName() + "." + "Multiplier";}

}
