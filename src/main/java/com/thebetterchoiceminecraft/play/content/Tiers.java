package com.thebetterchoiceminecraft.play.content;

public enum Tiers {
    NONE(0, "", "NONE"),
    COMMON(1, "Common.Color", "COMMON"),
    UNCOMMON(2, "Uncommon.Color", "UNCOMMON"),
    RARE(3, "Rare.Color", "RARE"),
    VERY_RARE(4, "VeryRare.Color", "VERY_RARE"),
    EPIC(5, "Epic.Color", "EPIC"),
    MYTHICAL(6, "Mythical.Color", "MYTHICAL"),
    LEGENDARY(7, "Legendary.Color", "LEGENDARY");

    private final TierDataHandler dataHandler;


    Tiers(int b, String colorString, String id) {
        this.dataHandler = new TierDataHandler(this, b, colorString, id);
    }

    public TierDataHandler getDataHandler() {
        return dataHandler;
    }

    pr


}
