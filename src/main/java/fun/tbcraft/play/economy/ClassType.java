package fun.tbcraft.play.economy;

import fun.tbcraft.play.TBCPlugin;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;

/**
 * Each class listed, with title and name.
 * Controls mana, stamina and stellium multiplier paths (if any)
 */
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


    /**
     * @return Mana Multiplier Amount
     */
    public double getManaMultiplier(){
        return TBCPlugin.getMainConfig().exists(manaPath)?TBCPlugin.getMainConfig().getDouble(manaPath):1;
    }

    /**
     * @return Stamina Multiplier Amount
     */
    public double getStaminaMultiplier(){
        return TBCPlugin.getMainConfig().exists(staminaPath)?TBCPlugin.getMainConfig().getDouble(staminaPath):1;

    }

    /**
     * @return Stellium Multiplier Amount (Base COnfig)
     */
    public double getStelliumMultiplier(){
        return TBCPlugin.getMainConfig().exists(stelliumPath)?TBCPlugin.getMainConfig().getDouble(stelliumPath):1;

    }
}
