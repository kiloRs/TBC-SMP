package com.thebetterchoiceminecraft.play.gaming.polishing;

import com.thebetterchoiceminecraft.play.tiers.Tiers;
import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythic.lib.api.util.EnumUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolishLootTable {
    private static final Map<Tiers,List<String>> tierData = new HashMap<>();
    private final Tiers tiers;
    private final List<String> resulting;

    PolishLootTable(Tiers tiers, List<String> resulting){
        this.tiers = tiers;
        this.resulting = resulting;
        tierData.put(tiers,resulting);
    }

    public static Map<Tiers, List<String>> getTierData() {
        return tierData;
    }

    public List<String> get(){
        return tierData.get(tiers);
    }
    public Tiers getTier(){
        return tiers;
    }



    //Searching for....
    private boolean containsItem(NBTItem i){
    return containsItem(MMOItems.getType(i).getId(),MMOItems.getID(i));}

    private boolean containsItem(String type, String id){
        return containsItem(MMOItems.plugin.getMMOItem(Type.get(type),id));
    }
    private boolean containsItem(MMOItem i) {

        if (i == null) {
            return false;
        }
        return tierData.get(EnumUtils.getIfPresent(Tiers.class, i.getTier().getId()).orElse(Tiers.NONE)).contains(i.getType().getId() + " " + i.getId());

    }
    private int getSize(){
        return tierData.get(tiers).size();
    }
}
