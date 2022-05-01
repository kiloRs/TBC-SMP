package com.thebetterchoiceminecraft.play.gaming.economy;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class CurrencyManager {
    private static Economy basicEconomy;
    private final Economy econ;
    private final String economyType;

    public CurrencyManager(String economyType){
        this.economyType = economyType;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            throw new RuntimeException("You have registered an Invalid Economy Class!");
        }

        econ = rsp.getProvider();
        basicEconomy = econ;

        if (!hasEconomy()){
            TBCPlugin.log("Error with the Vault Economy!");
        }
    }

    public String getNamed() {
        return economyType;
    }

    public Economy getThisEcon(){
        return econ;
    }
    public static Economy getBasicEconomy() {
        return basicEconomy;
    }

    private boolean hasEconomy(){
        return econ.isEnabled();
    }
}
