package io.github.Taigajid.MineShop;

import org.bukkit.Material;

public class ShopItem {
    private final Material material;
    private final int amount;

    public ShopItem(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }
}
