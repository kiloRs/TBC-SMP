package psikuvit.tbcenchant.Object;

import psikuvit.tbcenchant.Enchs.TBCEnchants;

public class EnchantObject {
    private final TBCEnchants tbcenchant;

    private int level;

    public EnchantObject(TBCEnchants tbcenchant) {
        this.tbcenchant = tbcenchant;
    }

    public TBCEnchants getTBCEnchant() {
        return this.tbcenchant;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
