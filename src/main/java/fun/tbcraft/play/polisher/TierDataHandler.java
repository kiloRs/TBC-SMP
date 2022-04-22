package fun.tbcraft.play.polisher;

import de.jeff_media.jefflib.EnumUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.manager.TierManager;
import org.inventivetalent.glow.GlowAPI;

public class TierDataHandler {
    private final String colorString;
    private final ItemTier mmoTierOrThrow;
    private String id;
    private final int number;
    private static TierManager m;
    private boolean enhancedTier = false;

    public TierDataHandler(Tiers t, int x, String pathFound, String id){
        this.number = x;
        this.colorString = pathFound;
        this.id = id;
        m = MMOItems.plugin.getTiers();
        this.mmoTierOrThrow = m.getOrThrow(id);
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
        return mmoTierOrThrow;
    }

    public boolean isEnhancedTier() {
        return enhancedTier;
    }
}
