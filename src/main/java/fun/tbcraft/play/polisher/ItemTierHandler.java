package fun.tbcraft.play.polisher;

import io.lumine.mythic.lib.api.item.ItemTag;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

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
    public boolean hasTier(Tiers specific, ItemStack itemStack){
        NBTItem nbt = NBTItem.get(itemStack);
        return nbt.hasTag("TBC_TIER") && getTier(itemStack) == specific;
    }
}
