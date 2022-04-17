package psikuvit.tbcenchant.Files;

import psikuvit.tbcenchant.TBCEnchant;
import psikuvit.tbcenchant.Utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages extends YAMLFile {
    private Map<String, String> messages;

    private List<String> incompatibilityError;

    public List<String> getIncompatibilityError() {
        return this.incompatibilityError;
    }

    public Messages(TBCEnchant TBCEnchant, String name) {
        super(TBCEnchant, name);
    }

    public void enable() {
        super.enable();
        loadMessages();
    }

    public void reload() {
        super.reload();
        loadMessages();
    }

    private void loadMessages() {
        this.messages = new HashMap<>();
        for (String key : getConfig().get().getConfigurationSection("messages").getKeys(false))
            this.messages.put(key, Utils.color(getConfig().get().getString("messages." + key)));
        this.incompatibilityError = getConfig().get().getStringList("incompatibilityError");
    }

    public String getMessage(String key) {
        return this.messages.get(key);
    }
}
