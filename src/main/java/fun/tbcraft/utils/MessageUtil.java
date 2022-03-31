package fun.tbcraft.utils;

import de.jeff_media.jefflib.EnumUtils;
import de.jeff_media.jefflib.TextUtils;
import fun.tbcraft.play.TBCPlugin;
import jdk.jfr.Label;
import jdk.jfr.Name;
import me.devtec.shared.dataholder.Config;
import me.devtec.shared.placeholders.PlaceholderAPI;
import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

    public Message getMessage(String key) {
        return new Message(key);
    }

    public boolean canRead() {
        return canRead;
    }

    @NotNull
    @Name("Message Parser")
    public static class Message{
        private final String idKey;
        private final boolean containsPlaceholders;
        private final String foundString;
        private final String checkedString;
        private TBCPlugin.Constants constant;

        public Message(String key){
            this.idKey = key;
            this.constant = EnumUtils.getIfPresent(TBCPlugin.Constants.class,idKey).orElse(TBCPlugin.Constants.ERROR_LIGHT);
            this.foundString = data.exists(constant.getPath())?data.getString(constant.getPath()):"";
            this.containsPlaceholders = this.foundString.contains("%");
            this.checkedString = Validate.notEmpty(this.foundString, "The string location provided is empty for " + idKey);

            if (checkedString.isEmpty()){
                TBCPlugin.log("- You are trying to parse a String with the key of " + key + " and it has no result!");

                throw new RuntimeException("Invalid Key");
            }

        }
        public void parse(Player player){
            String parsedString = getParsed(player);
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

    }
}
