package com.thebetterchoiceminecraft.play.gaming.tiers;

import io.lumine.mythic.lib.api.item.ItemTag;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ItemTierHandler {
    private static ItemTierHandler handler;
    private String tier_tag = ItemStats.TIER.getNBTPath();

    public ItemTierHandler(@Nullable String tierTag) {
        handler = this;
        if (tierTag == null){
            this.tier_tag = ItemStats.TIER.getNBTPath();
            return;
        }
        this.tier_tag = tierTag;
    }

    public static ItemTierHandler getHandler() {
        return handler;
    }

    public boolean has(Item item) {
        return NBTItem.get(item.getItemStack()).hasTag(tier_tag);
    }
    public boolean has(ItemStack itemStack){
        return NBTItem.get(itemStack).hasTag(tier_tag);
    }
    public boolean has(MMOItem mmoItem){
        return mmoItem.hasData(ItemStats.TIER)||NBTItem.get(MMOItems.plugin.getItem(mmoItem.getType(), mmoItem.getId())).hasTag(tier_tag);
    }

    public void addTierTag(Item i, Tiers tiers) {
        NBTItem.get(i.getItemStack()).addTag(new ItemTag(tier_tag, tiers.getDataHandler().getBaseID()));
    }

    public void remove(Item i, String tag) {
        NBTItem.get(i.getItemStack()).removeTag(tag);
    }

    private int get(Item i, String tag) {
        if (!has(i)) {
            return 0;
        }
        return NBTItem.get(i.getItemStack()).getInteger(tag);
    }

    public Tiers getTier(Item i) {
        return switch (get(i,tier_tag)) {
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

    public Tiers getTier(ItemStack i) {
        return switch (NBTItem.get(i).hasTag(tier_tag) ? NBTItem.get(i).getInteger(tier_tag) : 0) {
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

    public Tiers getTier(int level) {
        return switch (level) {
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

}