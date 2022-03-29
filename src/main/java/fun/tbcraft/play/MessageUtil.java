package fun.tbcraft.play;

import de.jeff_media.jefflib.TextUtils;
import fun.tbcraft.play.TBCPlugin;
import me.devtec.shared.dataholder.Config;
import me.devtec.shared.placeholders.PlaceholderAPI;
import org.bukkit.entity.Player;

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

    public static class Message{
        private final String idKey;
        private final boolean containsPlaceholders;
        private final String foundString;

        public Message(String key){
            this.idKey = key;
            this.foundString = data.exists(key)?data.getString(key):"";
            this.containsPlaceholders = this.foundString.contains("%");

            if (foundString.isEmpty() || !data.exists(key)){
                TBCPlugin.log("- You are trying to parse a String with the key of " + key + " and it has no result!");

                throw new RuntimeException("Invalid Key");
            }
        }
        public void parse(Player player){
            String parsedString = getParsed(player);
            if (containsPlaceholders || !idKey.isEmpty()&&idKey.contains("%")){
                parsedString = getParsed(player);
            }
            else {
                parsedString = this.foundString;
            }
            player.sendRawMessage(TextUtils.color("&a" + parsedString + "&r"));
        }
        private String getParsed(Player player){
            return containsPlaceholders?PlaceholderAPI.apply(foundString,player.getUniqueId()):this.foundString;
        }
        public String getIdKey(){
            return this.idKey;
        }
        public String getMessage(Player player){
            return  getParsed(player);
        }
    }
}
