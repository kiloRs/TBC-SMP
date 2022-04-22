package fun.tbcraft.play.polisher;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.api.interaction.GemStone;
import org.bukkit.inventory.ItemStack;

public abstract class Polishable {
    private final ItemTierHandler tierHandler = new ItemTierHandler();
    private NBTItem nbt;
    private ItemTier tier;
    private ItemStack i;
    private GemStone item;
    private static final String polishKey = "polishable";

    public Polishable(ItemStack i){
        this.i = i;
        nbt = NBTItem.get(this.i);
        tier = MMOItems.plugin.getTiers().getOrThrow(tierHandler.getTier(i).name());


    }
}
