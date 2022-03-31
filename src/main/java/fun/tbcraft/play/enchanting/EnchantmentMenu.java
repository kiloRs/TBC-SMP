package fun.tbcraft.play.enchanting;

import fun.tbcraft.play.TBCPlayer;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.HolderGUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.util.MMOCoreUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantmentMenu extends GUI {
    public EnchantmentMenu(String title, int size, Player... p) {
        super("enchanting", size, p);
    }

    @Override
    public boolean onIteractItem(Player player, ItemStack item, ClickType type, int slot, boolean gui) {
        ItemGUI slotSelection = getItemGUI(slot);

        if (slotSelection.isUnstealable()){
            return false;
        }

        if (this.getItem(slot).getType()== Material.BEDROCK){
            TBCPlayer tbc = TBCPlayer.get(player);
        }
        slotSelection.onClick(player, this,type);

        return super.onIteractItem(player, item, type, slot, gui);
    }
}
