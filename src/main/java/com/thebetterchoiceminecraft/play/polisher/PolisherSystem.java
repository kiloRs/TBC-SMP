package com.thebetterchoiceminecraft.play.polisher;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.play.content.Tiers;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public class PolisherSystem {
    private final NBTItem nbt;
    private final ItemStack itemStack;
    private MMOItem mmoItem;
    private final String id;
    private static final PolishDatabase database = TBCPlugin.getPolishDatabase();

    public PolisherSystem(NBTItem i){
        this.nbt = i;
        this.itemStack = nbt.getItem();
        this.mmoItem = MMOItems.plugin.getMMOItem(MMOItems.getType(nbt),MMOItems.getID(nbt));

        if (this.mmoItem == null){
            this.mmoItem = new MMOItem(MMOItems.getType(i),"" + i.getDisplayNameComponent().insertion())
        }
        this.id = mmoItem.getId();

        if (!nbt.hasTag("TBC_TIER")) {
            return;
        }
        int numb = nbt.getInteger("TBC_TIER");

        Tiers tier = TBCPlugin.itemHandler().getTier(numb);
        ItemTier mmoTier = tier.getDataHandler().getMmoTierOrThrow();

        if (mmoTier == null) {
            mmoTier = new ItemTier(Objects.requireNonNull(TBCPlugin.getPlugin().getConfig().getConfigurationSection(id)));
            MMOItems.plugin.getTiers().register(mmoTier);
        }


        //TODO Finish Tier Loading - I am a bit confused here.



    }

    public void polish(){

    }
}
