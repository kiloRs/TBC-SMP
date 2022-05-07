package com.thebetterchoiceminecraft.play.gaming.polishing;

import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.stat.data.StringListData;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
    private static List<String> useItems = new ArrayList<>();

    ItemHandler(MMOItem n){
        useItems = ((StringListData) n.getData(PolishStat.inst)).yh
    }
}
