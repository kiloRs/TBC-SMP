package psikuvit.tbcenchant;

public enum EnchantState {
    READY_TO_ENCHANT(null),
    NO_ENCHANTS_AVAILABLE(TBCEnchant.getInstance().getMessages().getMessage("placeholderCannot")),
    REACH_STACK_SIZE(TBCEnchant.getInstance().getMessages().getMessage("placeholderStackError"));

    public final String error;

    EnchantState(String error) {
        this.error = error;
    }
}
