package com.thebetterchoiceminecraft.play.gaming.economy;

import com.thebetterchoiceminecraft.play.TBCPlayer;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.utils.TBCConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TokenHolder {
    private static final int bonusAmount = 1;
    private FileConfiguration configuration = TBCPlugin.getPlugin().getConfig();
    private Player p;
    private boolean admin;
    private boolean bonus;
    private int tokenAmounts = 0;
    private final TBCConfigFile file;

    public TokenHolder(UUID uuid){
        this(Bukkit.getPlayer(uuid));
    }
    public TokenHolder(Player player) {
       file = new TBCConfigFile(TBCPlayer.get(player));
        TBCConfigFile file = new TBCConfigFile(TBCPlayer.get(player));

        this.configuration = file.getConfig();

        saveFirst(file, !configuration.contains("Tokens.Amount"));

    }

    private void saveFirst(TBCConfigFile file, boolean needed) {
        if (!configuration.isConfigurationSection("Tokens") || needed){
            ConfigurationSection section = configuration.createSection("Tokens");
            section.addDefault("Amount",tokenAmounts);
            section.addDefault("Bonus",p.isOp());

            file.save();
        }
    }

    private void load(){
        this.admin = p.isOp();
        this.tokenAmounts = configuration.getInt("Tokens.Amount");
        this.bonus = configuration.getBoolean("Tokens.Bonus");

    }
    private void save(){
        configuration.set("Tokens.Amount",tokenAmounts);
        configuration.set("Tokens.Bonus",bonus||admin);
        file.save();
    }

}
