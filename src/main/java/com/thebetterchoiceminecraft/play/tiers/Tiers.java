package com.thebetterchoiceminecraft.play.tiers;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ConfigFile;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public enum Tiers {
    NONE(0, "", "NONE"),
    COMMON(1, "Common.Color", "COMMON"),
    UNCOMMON(2, "Uncommon.Color", "UNCOMMON"),
    RARE(3, "Rare.Color", "RARE"),
    VERY_RARE(4, "VeryRare.Color", "VERY_RARE"),
    EPIC(5, "Epic.Color", "EPIC"),
    MYTHICAL(6, "Mythical.Color", "MYTHICAL"),
    LEGENDARY(7, "Legendary.Color", "LEGENDARY");

    private final TierDataHandler dataHandler;


    Tiers(int b, String colorString, String id) {
        this.dataHandler = new TierDataHandler(this, b, colorString, id);

        ConfigFile tiers = new ConfigFile("item-tiers");
        if (tiers.exists()){
                createDefaultMMOItemsTier((!tiers.getConfig().getKeys(false).contains(id)));

                //Creates if not found in MMOItems/ folder!
        }
        }


    public TierDataHandler getDataHandler() {
        return dataHandler;
    }

    public boolean hasMMOTier(){
        return MMOItems.plugin.getTiers().has(this.dataHandler.getName());
    }
    private void createDefaultMMOItemsTier(boolean force){
        if (!hasMMOTier()){
            String id = this.dataHandler.getName();
            int baseID = getDataHandler().getBaseID();
            ConfigFile itemTiersFile = new ConfigFile("item-tiers");

                if (!itemTiersFile.exists()) {
                    throw new RuntimeException("No Item Tiers File Found in MMOItems!");
                }
                FileConfiguration config = itemTiersFile.getConfig();

                if (!config.contains(id) || force){
                    config.set(id + ".name",id);
                    if (baseID - 1 >= 0) {
                        config.set(id + ".parent", ItemTierHandler.getHandler().getTier(baseID - 1).dataHandler.getName());
                    }
                    else {
                        config.set(id + ".parent","");
                    }
                    config.set(id + ".item-glow.hint",true);
                    config.set(id +".item-glow.color",dataHandler.getColorString());
                    config.set(id + ".generation.chance",1.0);
                    config.set(id + ".generation.capacity.base",1);

                    config.set(id + ".generation.capacity.scale",0.1);
                    config.set(id + ".generation.capacity.spread",0.1);
                    config.set(id + ".generation.capacity.max-spread",0.2);
                    config.set(id + ".deconstruct-item.success.coef",1);
                    config.set(id + ".deconstruct-item.success.items.MATERIAL.UNCOMMON_ESSENCE","100,1-1,0");
                    config.set(id + ".deconstruct-item.lose.coef",1);
                    config.set(id + ".deconstruct-item.lose.items.MATERIAL.WEAPON_POWDER","100,1-1,0");
                    itemTiersFile.save();
                }
                try {
                    ConfigurationSection section = config.getConfigurationSection(id);
                    if (!config.isConfigurationSection(id) || section == null){
                        throw new RuntimeException("COnfiguration Section in Tier File [" + id + "] not found.");
                    }
                    MMOItems.plugin.getTiers().register(new ItemTier(section));
                    TBCPlugin.log("Registered New Tier: " + id.toUpperCase(Locale.ROOT));
                } catch (Exception e) {
                    e.printStackTrace();
                    TBCPlugin.log("Failed to Register New Tier: " + id.toUpperCase(Locale.ROOT));
                }
            }
        }

    private ItemTier getItemTier(ItemStack itemStack){
        NBTItem nbt = NBTItem.get(itemStack);
        if (!nbt.hasType()){
            return null;
        }
        MMOItem mmoItem = Validate.notNull(MMOItems.plugin.getMMOItem(MMOItems.getType(nbt), MMOItems.getID(nbt)),"Item Not Found for Tier Finder");
        return MMOItems.plugin.getTiers().findTier(mmoItem);
    }
    private void loadAllTiers(){
        for (Tiers eachTier : Tiers.values()) {
            createDefaultMMOItemsTier(TBCPlugin.getTierConfig().exists()&&!TBCPlugin.getTierConfig().getConfig().contains(eachTier.dataHandler.getName() + "." + "name"));


            //Loading tier files....
        }
    }


}
