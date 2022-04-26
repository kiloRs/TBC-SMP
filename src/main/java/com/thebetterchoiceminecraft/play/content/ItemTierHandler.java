package com.thebetterchoiceminecraft.play.content;

import io.lumine.mythic.lib.api.item.ItemTag;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class ItemTierHandler {
    private static ItemTierHandler handler;
    private String tier_tag = "MMOITEMS_TIER";

    public ItemTierHandler(String tierTag) {
        handler = this;
        this.tier_tag = tierTag;
    }

    public static ItemTierHandler getHandler() {
        return handler;
    }

    public boolean has(Item item) {
        return NBTItem.get(item.getItemStack()).hasTag(tier_tag);
    }

    public void set(Item i, Tiers tiers) {
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