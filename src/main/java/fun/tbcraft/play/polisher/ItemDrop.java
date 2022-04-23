package fun.tbcraft.play.polisher;

import fun.tbcraft.play.TBCPlugin;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.event.ItemDropEvent;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    public ItemDrop(){

    }
    @EventHandler(priority = EventPriority.LOW)
    public void onDrop(ItemDropEvent e){
        return;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerDropItemEvent e){
        Item dropped = e.getItemDrop();

        Player pl = e.getPlayer();
        NBTItem nbt = NBTItem.get(dropped.getItemStack());
        if (!nbt.hasType()){
            return;
        }
        if (!nbt.hasTag("TBC_TIER")) {
            return;
        }

        int tierFound = nbt.getInteger("TBC_TIER");

        Tiers tier = TBCPlugin.itemHandler().getTier(tierFound);

        if (MMOItems.plugin.getTiers().has(tier.getDataHandler().getMmoTierOrThrow().getId())) {
            TBCPlugin.itemHandler().g
        }
    }
}
