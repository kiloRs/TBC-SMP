package com.thebetterchoiceminecraft.play.gaming.polishing;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.play.tiers.Tiers;
import com.thebetterchoiceminecraft.utils.TBCConfigFile;
import io.lumine.mythic.lib.api.util.EnumUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LootSetup {
    private List<PolishLootTable> allKnownLoot = new ArrayList<>();
    private TBCConfigFile loot = TBCPlugin.getTierConfig();

    public LootSetup(){

        allKnownLoot = new ArrayList<>();
        FileConfiguration x = TBCPlugin.getTierConfig().getConfig();
        if (x.contains("loot")) {
            List<String> loot = x.getStringList("loot");

            for (String key : loot) {
                String type = key.split(" ")[0];
                String i = key.split(" ")[1];
                String tier = key.split(" ")[2];
                String percentage = key.split(" ")[3];

                if (EnumUtils.getIfPresent(Tiers.class,tier).orElse(Tiers.NONE)==Tiers.NONE){
                    return;
                }

                allKnownLoot.add(new PolishLootTable(EnumUtils.getIfPresent(Tiers.class,tier).orElse(Tiers.NONE),x.getStringList("loot")));
            }
        }
    }
}
