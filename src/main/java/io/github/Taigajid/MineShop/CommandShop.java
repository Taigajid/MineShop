package io.github.Taigajid.MineShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.HashMap;

public class CommandShop implements CommandExecutor {

    private final Map<String, Map<String, ShopItem>> shopItems;
    private ShopGui shopGui;


    public CommandShop() {
        shopItems = new HashMap<>();

        Map<String, ShopItem> diamondItems = new HashMap<>();
        diamondItems.put("oak_log", new ShopItem(Material.OAK_LOG, 256));
        diamondItems.put("spruce_log", new ShopItem(Material.SPRUCE_LOG, 256));

        Map<String, ShopItem> netheriteItems = new HashMap<>();
        netheriteItems.put("elytra", new ShopItem(Material.ELYTRA, 1));
        netheriteItems.put("mace", new ShopItem(Material.MACE, 1));


        shopItems.put("netherite", netheriteItems);
        shopItems.put("diamond", diamondItems);
    }

    public CommandShop(ShopGui shopGui, Map<String, Map<String, ShopItem>> shopItems) {
        this.shopGui = shopGui;
        this.shopItems = shopItems;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @Nullable Command command, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if(args.length == 0) {
            shopGui.openInventory(player);
            return true;

        }
        if (args.length == 2) {
            String category = args[0].toLowerCase();
            String itemKey = args[1].toLowerCase();

            Map<String, ShopItem> validCategory = shopItems.get(category);

            ShopItem validItems = validCategory.get(itemKey);

            if (!shopItems.containsKey(category)) {
                sender.sendMessage("Invalid Category!");
                return true;
            }

            if (!validCategory.containsKey(itemKey)) {
                sender.sendMessage("Invalid Item!");
                return true;
            }

            ItemStack purchasedItem = new ItemStack(validItems.getMaterial(), validItems.getAmount());

            switch (category) {
                case "diamond":
                    if (player.getInventory().contains(Material.DIAMOND_BLOCK, 1)) {
                        player.getInventory().remove(new ItemStack(Material.DIAMOND_BLOCK, 1));
                        player.getInventory().addItem(purchasedItem);
                    } else {
                        sender.sendMessage("Du hast keinen Diamantblock!");
                        return true;
                    }
                    break;

                case "netherite":
                    if (player.getInventory().contains(Material.NETHERITE_INGOT, 1)) {
                        player.getInventory().remove(new ItemStack(Material.NETHERITE_INGOT, 1));
                        player.getInventory().addItem(purchasedItem);
                    } else {
                        sender.sendMessage("Du hast keinen Netherite Ingot!");
                        return true;
                    }
                    break;
            }

            return true;
        }
        return false;
    }

    public Map<String, Map<String, ShopItem>> getShopItems() {
        return this.shopItems;
    }

}
