package com.thebetterchoiceminecraft.play.polisher;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmocore.comp.citizens.CitizenInteractEvent;
import net.Indyuce.mmoitems.api.interaction.UseItem;
import net.Indyuce.mmoitems.api.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PolishableItem extends UseItem implements Listener {

    public PolishableItem(Player player, NBTItem nbtItem) {
        super(player, nbtItem);
    }

    public PolishableItem(PlayerData playerData, NBTItem nbtItem) {
        super(playerData, nbtItem);
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
    }

    public void onUse(CitizenInteractEvent e){
        net.citizensnpcs.api.npc.NPC npc = e.getNPC();

        if (npc.)
    }
}
