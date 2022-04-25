package com.thebetterchoiceminecraft.play.content;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import de.jeff_media.jefflib.EnumUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ConfigFile;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.manager.TierManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.inventivetalent.glow.GlowAPI;

import java.util.Locale;

public class TierDataHandler {
    private final String colorString;
    private final ItemTier mmoTierOrThrow;
    private final String id;
    private final int number;
    private static TierManager m;
    private boolean enhancedTier = false;
    private static FileConfiguration config = TBCPlugin.getSettings();
    private boolean registerNew = false;
    private ConfigFile itemTiersFile;

    public TierDataHandler(Tiers t, int x, String path, String id, boolean create){
        this(t,x,path,id);

        registerNew = create;
    }
    public TierDataHandler(Tiers t, int x, String pathFound, String id){
        this.number = x;
        this.colorString = pathFound;
        this.id = id;
        m = MMOItems.plugin.getTiers();
        this.mmoTierOrThrow = m.has(id)?m.get(id):null;
        if (mmoTierOrThrow == null || registerNew){
            try {
                register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.enhancedTier = x==5||x==6||x==7;
        //TODO Complete Tier Data Handler - Make Sure It All Works - Integrate It With Enchanting and Upgrading and Polishing
    }
    public static TierManager getM() {
        return m;
    }

    public int getBaseID() {
        return number;
    }

    public GlowAPI.Color getColor(){
        return EnumUtils.getIfPresent(GlowAPI.Color.class, colorString).orElse(GlowAPI.Color.NONE);
    }

    public String getName(){
        return id;
    }

    public ItemTier getMmoTierOrThrow() {
        if (mmoTierOrThrow == null){
            register();
        }
        return mmoTierOrThrow;
    }

    private void register() {
        String path = "Tiers." + id;
        itemTiersFile = new ConfigFile("item-tiers");
        config = itemTiersFile.getConfig();

        if (!config.contains(path)){
            config.set(path + ".name",id);
            if (getBaseID() - 1 >= 0) {
                config.set(path + ".parent", ItemTierHandler.getHandler().getTier(getBaseID() - 1).getDataHandler().id);
            }
            else {
                config.set(path + ".parent","");
            }
            config.set(path + ".item-glow.hint",true);
            config.set(path +".item-glow.color",colorString);
            config.set(path + ".generation.chance",1.0);
            config.set(path + ".generation.capacity.base",1);

            config.set(path + ".generation.capacity.scale",0.1);
            config.set(path + ".generation.capacity.spread",0.1);
            config.set(path + ".generation.capacity.max-spread",0.2);
            config.set(path + ".deconstruct-item.success.coef",1);
            config.set(path + ".deconstruct-item.success.items.MATERIAL.UNCOMMON_ESSENCE","100,1-1,0");
            config.set(path + ".deconstruct-item.lose.coef",1);
            config.set(path + ".deconstruct-item.lose.items.MATERIAL.WEAPON_POWDER","100,1-1,0");

        }
        try {
            MMOItems.plugin.getTiers().register(new ItemTier(TBCPlugin.getSettings().getAs(path, ConfigurationSection.class)));
            TBCPlugin.log("Registered New Tier: " + id.toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            e.printStackTrace();
            TBCPlugin.log("Failed to Register New Tier: " + this.id.toUpperCase(Locale.ROOT));
        }
    }

    public boolean isEnhancedTier() {
        return enhancedTier;
    }
}
