package fun.tbcraft.play;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;

public enum ClassType {
    RANGED("Ranged","Marksman"),TANK("Tank","Warrior"),CASTER("Caster","Mage"),HEALER("Healer","Paladin"),SNEAK("Sneak","Rogue");

    private final String path;
    private final String name;
    private final String manaPath;
    private final String stelliumPath;
    private final String staminaPath;
    private final PlayerClass playerClass;
    ClassType(String path, String name){
        this.path = path;
        this.name = name;

        manaPath = getPath() + "." + getPath(ResourceType.MANA) + ".Multiplier";
        staminaPath = getPath() + "." + getPath(ResourceType.STAMINA) + ".Multiplier";
        stelliumPath = getPath() + "." + getPath(ResourceType.STELLIUM) + ".Multiplier";

        this.playerClass = MMOCore.plugin.classManager.getOrThrow(name);
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public String getName() {
        return name;
    }

    private String getPath(){
        return path;
    }
    public String getPath(ResourceType resourceType) {
        return resourceType.name();
    }
    public double getManaMultiplier(){
        return TBCPlugin.getMainConfig().exists(manaPath)?TBCPlugin.getMainConfig().getDouble(manaPath):1;
    }
    public double getStaminaMultiplier(){
        return TBCPlugin.getMainConfig().exists(staminaPath)?TBCPlugin.getMainConfig().getDouble(staminaPath):1;

    }
    public double getStelliumMultiplier(){
        return TBCPlugin.getMainConfig().exists(stelliumPath)?TBCPlugin.getMainConfig().getDouble(stelliumPath):1;

    }
}
