package com.thebetterchoiceminecraft.play.utils;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.utils.TBCConfigFile;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import org.bukkit.configuration.file.FileConfiguration;

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
    private FileConfiguration config;

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
        return TBCPlugin.getMainConfig().getConfig().contains(manaPath)?TBCPlugin.getMainConfig().getConfig().getDouble(manaPath):1;
    }

    /**
     * @return Stamina Multiplier Amount
     */
    public double getStaminaMultiplier(){
        return TBCPlugin.getMainConfig().getConfig().contains(staminaPath)?TBCPlugin.getMainConfig().getConfig().getInt(staminaPath):1;

    }

    /**
     * @return Stellium Multiplier Amount (Base COnfig)
     */
    public double getStelliumMultiplier(){
        TBCConfigFile c = TBCPlugin.getMainConfig();
        config = c.getConfig();
        return c.getConfig().contains(stelliumPath)? config.getInt(stelliumPath):1;

    }
}
