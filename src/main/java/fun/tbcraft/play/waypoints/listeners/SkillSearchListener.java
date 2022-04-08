package fun.tbcraft.play.waypoints.listeners;

import de.jeff_media.jefflib.EnumUtils;
import de.jeff_media.jefflib.TextUtils;
import fun.tbcraft.play.TBCPlugin;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.skill.SkillMetadata;
import io.lumine.mythic.lib.skill.result.SkillResult;
import io.lumine.mythic.utils.prompts.chat.ChatPrompt;
import io.lumine.mythic.utils.prompts.chat.ChatResponse;
import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import me.devtec.theapi.bukkit.gui.GUI;
import me.devtec.theapi.bukkit.gui.HolderGUI;
import me.devtec.theapi.bukkit.gui.ItemGUI;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.event.PlayerKeyPressEvent;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.skill.CastableSkill;
import net.Indyuce.mmocore.skill.ClassSkill;
import net.Indyuce.mmocore.skill.cast.PlayerKey;
import net.Indyuce.mmoitems.gui.edition.CommandListEdition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SkillSearchListener implements Listener {
    private final String activation = TBCPlugin.getMainConfig().getString("Skill.Activation");
    private final PlayerKey neededKey = EnumUtils.getIfPresent(PlayerKey.class,activation).orElse(PlayerKey.SWAP_HANDS);
    private static final int[] requiredSlots = new int[]{7,8,9};
    private final boolean enabled = true;
    private GUI skill_selection;

    @EventHandler
    public void onUse(PlayerKeyPressEvent e){
        PlayerKey playerKey = e.getPressed();

        if (playerKey==PlayerKey.SWAP_HANDS){
            TBCPlugin.log("Swap Hands");
        }
        else if (playerKey==PlayerKey.DROP){
            TBCPlugin.log("Drop With Hand");
        }
        else if (playerKey==PlayerKey.CROUCH){
            TBCPlugin.log("Crouch");
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerType(AsyncChatEvent e)  {
        Player p = e.getPlayer();
        Component message = e.message();
        TextComponent textComponent = ((TextComponent) message);
        String knownContent = textComponent.toBuilder().content();

        if (!knownContent.isEmpty()) {
            String side = null;
            if (knownContent.startsWith("-")) {
                side = knownContent.split("-")[1];
            }
            if (side==null){
                side=knownContent;
            }
            String amount = side.split(" ")[0];
            String skillName = side.split(" ")[1];
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(InventoryClickEvent e){

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onUseSlot(PlayerArmSwingEvent e){
        Player player = e.getPlayer();

        int slot = player.getInventory().getHeldItemSlot();

        if (!player.isOp()){
            return;
        }
        if (slot ==7 || slot ==8 || slot ==9){
            e.setCancelled(true);
        }
        PlayerData playerData = MMOCore.plugin.dataProvider.getDataManager().get(player);
        ClassSkill classSkill = playerData.getBoundSkills().get(slot);
        if (classSkill!=null){
            CastableSkill castable = classSkill.toCastable(playerData);
            SkillResult skillResult = TBCPlugin.getMmoCoreAPI().cast(playerData, classSkill);

            //TODO Finish Coding This - Using to Test Cast Method, However We Also Need More GUI Work!
            boolean result = skillResult.isSuccessful(new SkillMetadata(castable, MMOPlayerData.get(player)));
            if (result){
                player.sendRawMessage(TextUtils.color("&aExecuted " + classSkill.getSkill().getName()));
            }
            else {
                player.sendRawMessage(TextUtils.color("&4Failed " + classSkill.getSkill().getName()));
            }

            TBCPlugin.debug("Te");
        }
    }
}
