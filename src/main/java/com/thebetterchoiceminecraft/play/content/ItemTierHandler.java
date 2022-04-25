package com.thebetterchoiceminecraft.play.content;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import io.lumine.mythic.lib.api.item.ItemTag;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.inventivetalent.glow.GlowAPI;

import java.util.ArrayList;
import java.util.List;

public class ItemTierHandler {
    private static ItemTierHandler handler;

    public ItemTierHandler() {
        handler = this;
    }

    public static ItemTierHandler getHandler() {
        return handler;
    }


    public boolean has(Item item){
        return NBTItem.get(item.getItemStack()).hasTag("TBC_TIER");
    }
    public void set(Item i, Tiers tiers){
        NBTItem.get(i.getItemStack()).addTag(new ItemTag("TBC_TIER",tiers.getDataHandler().getBaseID()));
    }
    public void remove(Item i){
        NBTItem.get(i.getItemStack()).removeTag("TBC_TIER");
    }
    private int get(Item i){
        if (!has(i)){
            return 0;
        }
        return NBTItem.get(i.getItemStack()).getInteger("TBC_TIER");
    }
    public Tiers getTier(Item i){
        return switch (get(i)) {
            case 0 -> Tiers.NONE;
            case 1 -> Tiers.COMMON;
            case 2 -> Tiers.UNCOMMON;
            case 3 -> Tiers.RARE;
            case 4 -> Tiers.VERY_RARE;
            case 5 -> Tiers.EPIC;
            case 6 -> Tiers.MYTHICAL;
            case 7 -> Tiers.LEGENDARY;
            default -> Tiers.NONE;
        };

    }
    public Tiers getTier(ItemStack i){
        return switch (NBTItem.get(i).hasTag("TBC_TIER")?NBTItem.get(i).getInteger("TBC_TIER"):0) {
            case 0 -> Tiers.NONE;
            case 1 -> Tiers.COMMON;
            case 2 -> Tiers.UNCOMMON;
            case 3 -> Tiers.RARE;
            case 4 -> Tiers.VERY_RARE;
            case 5 -> Tiers.EPIC;
            case 6 -> Tiers.MYTHICAL;
            case 7 -> Tiers.LEGENDARY;
            default -> Tiers.NONE;
        };

    }
    public Tiers getTier(int level){
        return switch (level) {
            case 0 -> Tiers.NONE;
            case 1 -> Tiers.COMMON;
            case 2 -> Tiers.UNCOMMON;
            case 3 -> Tiers.RARE;
            case 4 -> Tiers.VERY_RARE;
            case 5 -> Tiers.EPIC;
            case 6 -> Tiers.MYTHICAL;
            case 7 -> Tiers.LEGENDARY;
            default -> Tiers.NONE;
        };

    }
    public boolean hasTier(ItemStack stack){
        return NBTItem.get(stack).hasTag("TBC_TIER")&&NBTItem.get(stack).getInteger("TBC_TIER")>0;
    }
    public boolean hasTier(Tiers specific, ItemStack itemStack){
        NBTItem nbt = NBTItem.get(itemStack);
        return nbt.hasTag("TBC_TIER") && getTier(itemStack) == specific;
    }

    /**
     * @return Opens the color handler!
     */
    public static TierColorHandler getColorHandler(){
        return new TierColorHandler((TBCPlugin) TBCPlugin.getPlugin());
    }

    /**
     * Handles the glowing of the items, based on our TIER enums only, not MMOItems tier.
     */
    public static class TierColorHandler {
        private final Plugin p;
        private final boolean enabled = Bukkit.getPluginManager().isPluginEnabled("GlowAPI");

        public TierColorHandler(TBCPlugin p){
            this.p = p;

        }

        public void activate(PlayerDropItemEvent e){
            if (!ItemTierHandler.handler.hasTier(e.getItemDrop().getItemStack())) {
                return;
            }
            Tiers tier = ItemTierHandler.handler.getTier(e.getItemDrop());

            GlowAPI.setGlowing(e.getItemDrop(),tier.getDataHandler().getColor(),e.getPlayer());

            List<Entity> list = e.getItemDrop().getNearbyEntities(15, 2, 15);
            if (!list.isEmpty()){
                List<Entity> playerEntities = list.stream().filter(entity -> entity instanceof Player player).toList();
                List<Player> result = new ArrayList<>();


                playerEntities.forEach(entity -> result.add(((Player) entity)));

                GlowAPI.setGlowing(e.getItemDrop(),tier.getDataHandler().getColor(),result);
            }
        }

        public boolean isActive(Player player, Item item){
            return GlowAPI.isGlowing(item,player);
        }

        public void assignColor(Player player, Item item){
            if (ItemTierHandler.handler.has(item)) {
                Tiers tier = ItemTierHandler.getHandler().getTier(item);

                if (tier != Tiers.NONE){
                    try {
                        GlowAPI.setGlowing(item,tier.getDataHandler().getColor(),player);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

}
}
