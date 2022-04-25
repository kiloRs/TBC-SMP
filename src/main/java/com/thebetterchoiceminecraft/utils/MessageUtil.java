package com.thebetterchoiceminecraft.utils;

import de.jeff_media.jefflib.EnumUtils;
import de.jeff_media.jefflib.TextUtils;
import com.thebetterchoiceminecraft.play.TBCPlugin;
import jdk.jfr.Name;
import me.devtec.shared.dataholder.Config;
import me.devtec.shared.placeholders.PlaceholderAPI;
import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * This class is used for reading messages out of config files, in a nice parsed placeholder format.
 */
public class MessageUtil {
    private static Config data;
    private boolean canRead = true;

    public MessageUtil(){
        this(TBCPlugin.getMainConfig());
    }
    public MessageUtil(Config config) {
        data = config;

        if (config.getKeys().isEmpty()) {
            canRead = false;
        }

    }

    public static void send(String message, Player player){
        player.sendRawMessage(TextUtils.color(message));
    }
    public static MessageLoader getLoader(){
        return new MessageLoader();
    }
    public Message getForcedMessage(String key){
        return new Message(key);
    }
    public Message getMessage(String key) {
        if (canRead){
            return new Message(key);
        }
        return null;
    }

    public boolean canRead() {
        return canRead;
    }

    @NotNull
    @Name("Message Parser")
    public static class Message{
        private final String idKey;
        private final boolean containsPlaceholders;
        private final String checkedString;
        private TBCPlugin.Constants constant = null;

        public Message(String key){
            this.idKey = key;
            Optional<TBCPlugin.Constants> constant = EnumUtils.getIfPresent(TBCPlugin.Constants.class, this.idKey);
            String foundString = data.exists(key) ? data.getString(key) : "";
            this.checkedString = Validate.notEmpty(foundString, "The string location provided is empty for " + idKey);

            this.constant = constant.orElse(null);
            if (checkedString.isEmpty()){
                TBCPlugin.log("- You are trying to parse a String with the key of " + key + " and it has no result!");
                throw new RuntimeException("Invalid Key");
            }
            this.containsPlaceholders = this.checkedString.contains("%");


        }
        public void parse(Player player){
            String parsedString;

            if (!containsPlaceholders){
                parsedString = checkedString;
                player.sendRawMessage(TextUtils.color("&a " + parsedString));
                return;
            }
            parsedString = getParsed(player);
            player.sendRawMessage(TextUtils.color("&a" + parsedString + "&r"));
        }

        private String getParsed(Player player){
            return containsPlaceholders?PlaceholderAPI.apply(checkedString,player.getUniqueId()):this.checkedString;
        }
        public String getIdKey(){
            return this.idKey;
        }
        public String getMessage(Player player){
            return  getParsed(player);
        }
        public String getMessageNatural(){
            return this.checkedString;
        }

        public TBCPlugin.Constants getConstant() {
            return constant;
        }
    }
    public static class MessageLoader{
        private final String c;

        public MessageLoader(){
            this("Messages");
        }
        private MessageLoader(String coreWord){
            this.c = coreWord;
            init();
        }
        public void init(){
            if (loadAll()){
                TBCPlugin.log("Loaded All Starter Messages - Configure AWAY!");
                return;
            }
            TBCPlugin.log("Failed to Load Messages for Starter");
        }
        private boolean loadAll(){
            int changes = 0;
            for (TBCPlugin.Constants value : TBCPlugin.Constants.values()) {
                ++changes;
                if (TBCPlugin.getMainConfig().exists(c + "." + value.getPath()) && TBCPlugin.getMainConfig().get(c + "." + value.getPath()) != null){
                    TBCPlugin.log("SKipping Message: " + value.getPath());

                    continue;
                }
                TBCPlugin.log("Adding Message: " + value.getPath());
                TBCPlugin.getMainConfig().setIfAbsent(c + "." + value.getPath(),"Default Message Loaded!");
            }

            if (TBCPlugin.getMainConfig().getKeys().contains(c) || TBCPlugin.getMainConfig().exists(c)){
                return TBCPlugin.getMainConfig().getKeys(c).size() == TBCPlugin.Constants.values().length;
            }
            return changes==TBCPlugin.Constants.values().length;
        }
    }
}
