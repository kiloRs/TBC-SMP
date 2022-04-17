package psikuvit.tbcenchant.Object;

import java.util.List;

public class LoreSettings {
    private final LoreAddition loreAddition;

    private final int line;

    public LoreSettings(LoreAddition loreAddition, int line) {
        this.loreAddition = loreAddition;
        this.line = line;
    }

    public List<String> getToAddLore(List<String> itemLore, List<String> toAddLore) {
        if (itemLore == null)
            return toAddLore;
        if (this.loreAddition == LoreAddition.BOTTOM && !itemLore.isEmpty()) {
            itemLore.addAll(toAddLore);
        } else if (this.loreAddition == LoreAddition.TOP && !itemLore.isEmpty()) {
            itemLore.addAll(0, toAddLore);
        } else if (this.loreAddition == LoreAddition.SPECIFIC_LINE && this.line < itemLore.size()) {
            itemLore.addAll(this.line, toAddLore);
        } else {
            return toAddLore;
        }
        return itemLore;
    }
}
