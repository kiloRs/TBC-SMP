package fun.tbcraft.play.enchanting;

import io.lumine.mythic.lib.api.util.EnumUtils;
import io.lumine.mythic.utils.numbers.RandomInt;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnchantmentRandomizer {
    private RandomInt random;
    private static final List<Enchantment> allKnown = new ArrayList<>();

    public EnchantmentRandomizer(){
        if (!allKnown.isEmpty()){
            allKnown.clear();
        }

        allKnown.addAll(Arrays.asList(Enchantment.values()));
        random = new RandomInt(0,allKnown.size() - 1);
    }
    public Enchantment getRandom(){
        return allKnown.get(random.get());
    }
    public Enchantment getSpecific(String id){
        Enchantment enchantment = EnumUtils.getIfPresent(Enchantment.class,id).orElseThrow(() -> new IllegalArgumentException("Invalid Enchantment!"));
        if (enchantment == null || !allKnown.contains(enchantment)){
            return null;
        }
        return allKnown.get(allKnown.indexOf(enchantment));
    }
}
