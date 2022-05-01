package com.thebetterchoiceminecraft.play.commands;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import de.jeff_media.jefflib.TextUtils;
import io.lumine.mythic.lib.MythicLib;
import io.lumine.mythic.lib.comp.anticheat.CheatType;
import me.devtec.shared.API;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.MMOCoreAPI;
import net.Indyuce.mmocore.experience.Profession;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class TBCCommand implements CoreCommand {

    private static final HashMap<CheatType, Integer> antiCheat = new HashMap<>();
    static {
        for (CheatType value : CheatType.values()) {
            antiCheat.put(value,40);
        }
    }

    @Override
    public String getID() {
        return "TBC";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!label.equalsIgnoreCase(getID()) && !command.getName().equalsIgnoreCase(getID())) {
            return false;
        }
        if (!(sender instanceof Player player)) {
            return false;
        }


        String first = getArg(args, "reload", 1);

        if (first != null){
            Bukkit.reload();
            player.sendRawMessage(TextUtils.format("&aReloaded Plugin",player));
            return true;
        }
        String start = getArg(args, "start", 1);
        String deploy = getArg(args, "deploy", 2);
        String core = getArg(args, "core", 2);

        if (start != null){

            if (core != null){
                if (!player.isOp()){
                    return false;
                }

                MMOCoreAPI coreAPI = new MMOCoreAPI(((JavaPlugin) TBCPlugin.getPlugin()));


                Profession smelting = MMOCore.plugin.professionManager.get("smelting");
                Profession hunter = MMOCore.plugin.professionManager.get("hunter");

                if (hunter != null){
                    player.sendRawMessage(TextUtils.format("&fHunter Level: " + coreAPI.getPlayerData(player).getCollectionSkills().getLevel(hunter) + " / " + hunter.getMaxLevel()));
                }

                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
                    player.sendRawMessage(TextUtils.format("&aFixing Night Vision Perk!"));

                    String client = player.getClientBrandName();
                    if (client != null){
                        player.sendRawMessage(TextUtils.format("&a" + client));
                    }
                    else {
                        player.sendRawMessage(TextUtils.format("&cCould Not Find Client"));
                    }
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,10000,1,true,false,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,10000,2,true,false,false));

                return true;
            }
            if (deploy != null){
                player.sendRawMessage(TextUtils.format("&aAttempting a Deploy!"));
                try {
                    MythicLib.inst().getAntiCheat().disableAntiCheat(player, antiCheat);
                    player.sendRawMessage(TextUtils.format("&eGhost Mode - Time: 40"));
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            try {
                API.library.downloadFileFromUrl("htttps://www.thebetterchoiceminecraft.com/", TBCPlugin.getInstance().getDataFolder() + "/downloaded.yml");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    private boolean arg(String needs, String provided){
        return needs.equalsIgnoreCase(provided);
    }
    private String getArg( String[] possible,String req, int space){
        return possible.length>=space&&arg(req,possible[space-1])?possible[space-1]:null;
    }
}
