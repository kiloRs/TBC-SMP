package com.thebetterchoiceminecraft.play.gaming.polishing;

import io.lumine.mythic.lib.api.item.ItemTag;
import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
import net.Indyuce.mmoitems.gui.edition.EditionInventory;
import net.Indyuce.mmoitems.stat.data.StringListData;
import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
import net.Indyuce.mmoitems.stat.data.type.StatData;
import net.Indyuce.mmoitems.stat.type.ItemStat;
import net.Indyuce.mmoitems.stat.type.StringListStat;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolishStat extends StringListStat {
    public static ItemStat inst = new PolishStat();

    public PolishStat(){
        super("Polish", Material.HONEY_BOTTLE,"Polish System",new String[]{""},new String[]{"all"});
    }

    public PolishStat(String id, Material mat, String name, String[] lore, String[] types, Material... materials) {
        super(id, mat, name, lore, types, materials);
    }

    @Override
    public StringListData whenInitialized(Object object) {
        return super.whenInitialized(object);
    }

    @Override
    public void whenApplied(@NotNull ItemStackBuilder item, @NotNull StatData data) {
        super.whenApplied(item, data);
    }

    @NotNull
    @Override
    public ArrayList<ItemTag> getAppliedNBT(@NotNull StatData data) {
        return super.getAppliedNBT(data);
    }

    @Override
    public void whenClicked(@NotNull EditionInventory inv, @NotNull InventoryClickEvent event) {
        super.whenClicked(inv, event);
    }

    @Override
    public void whenInput(@NotNull EditionInventory inv, @NotNull String message, Object... info) {
        super.whenInput(inv, message, info);
    }

    @Override
    public void whenLoaded(@NotNull ReadMMOItem mmoitem) {
        super.whenLoaded(mmoitem);
    }

    @Nullable
    @Override
    public StatData getLoadedNBT(@NotNull ArrayList<ItemTag> storedTags) {
        return super.getLoadedNBT(storedTags);
    }

    @Override
    public void whenDisplayed(List<String> lore, Optional<RandomStatData> statData) {
        super.whenDisplayed(lore, statData);
    }

    @NotNull
    @Override
    public StatData getClearStatData() {
        return super.getClearStatData();
    }
}
