package com.thebetterchoiceminecraft.play.polisher;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import com.thebetterchoiceminecraft.play.content.Tiers;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.api.util.RandomAmount;

import java.util.*;

public class PolishDatabase {
    private static final Map<Tiers,List<String>> polishMap = new HashMap<>();
     private static final List<Tiers> allPossibleTiers = Arrays.stream(Tiers.values()).toList();



    public PolishDatabase get(boolean debug){
        if (debug){
            try {
                TBCPlugin.log("Loading Polish Types!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new PolishDatabase();
    }

    public PolishDatabase(){
        initiate();
    }

    private void initiate() {
        if (polishMap.isEmpty()){
            for (Tiers each : Tiers.values()) {
                ArrayList<String> l = new ArrayList<>();
                l.add("Default.Text");
                polishMap.put(each, l);
            }
        }
    }
    public List<String> getPossibleResults(Tiers t){
        return polishMap.get(t);
    }
    public boolean hasAny(Tiers t){
        return polishMap.containsKey(t);
    }

    public MMOItem getParsed(Tiers t){
        List<String> results = getPossibleResults(t);

        RandomAmount m = new RandomAmount(0, results.size());
        String resulting = results.get(m.getRandomAmount());

        String ty = resulting.split(" ")[0];
        String mmo = resulting.split(" ")[1];

        return MMOItems.plugin.getMMOItem(Type.get(ty),mmo);
    }

    /**
     * @param t Type to Assign
     * @param type Must match or be a child of this type
     * @return base mmoitem from list randomly.
     */
    public MMOItem getParsed(Tiers t, Type type){
        List<String> res = getPossibleResults(t);

        for (String stringWord : res) {
            String namedType = stringWord.split(" ")[0];

            String found = res.get(res.indexOf(stringWord));
            if (found == null){
                throw new RuntimeException("Bad Found Name" );
            }
            Type typeFound = Type.get(namedType);

            if (typeFound==null){
                throw new RuntimeException("Invalid Type Exception!");
            }
            if (!typeFound.corresponds(type) || !typeFound.equals(type)) {
                res.remove(stringWord);
            }
        }

        int x = new RandomAmount(0, res.size()).getRandomAmount();

        return MMOItems.plugin.getMMOItem(Type.get(res.get(x).split(" ")[0]), res.get(x).split(" ")[1]);

    }
}
