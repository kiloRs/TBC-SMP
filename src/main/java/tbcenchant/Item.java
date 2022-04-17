package psikuvit.tbcenchant;

import psikuvit.tbcenchant.libs.XMaterial;

import java.util.List;

public class Item {
    public XMaterial material;

    public int amount;

    public String title;

    public String headData;

    public String headOwner;

    public List<String> lore;

    public Integer slot;

    public String command;

    public Item() {}

    public Item(Item item) {
        this.material = item.material;
        this.amount = item.amount;
        this.lore = item.lore;
        this.title = item.title;
        this.slot = item.slot;
    }

    public Item(XMaterial material, int amount, String title, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.title = title;
    }

    public Item(XMaterial material, int slot, int amount, String title, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.title = title;
        this.slot = Integer.valueOf(slot);
    }

    public Item(XMaterial material, int slot, String headData, int amount, String title, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.title = title;
        this.slot = Integer.valueOf(slot);
        this.headData = headData;
    }

    public Item(XMaterial material, int slot, int amount, String title, String headOwner, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.title = title;
        this.headOwner = headOwner;
        this.slot = Integer.valueOf(slot);
    }

    public Item(XMaterial material, int amount, String title, String headOwner, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.title = title;
        this.headOwner = headOwner;
    }
}
