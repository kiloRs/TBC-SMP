package fun.tbcraft.play.polisher;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.inventivetalent.glow.GlowAPI;

public class ColorViewer {
    private final Player player;

    public ColorViewer(Player player){
        this.player = player;
    }
    private boolean isGlowing(Item item){
        return GlowAPI.isGlowing(item,player);
    }

    private void setGlowing(Item item){
        Tiers tier = ItemTierHandler.getHandler().getTier(item);
        try {
            GlowAPI.setGlowing(item,tier.getDataHandler().getColor(),player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
