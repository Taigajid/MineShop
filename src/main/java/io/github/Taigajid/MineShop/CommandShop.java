package io.github.Taigajid.MineShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.HashMap;

public class CommandShop implements CommandExecutor {

    private final Map<String, Map<String, ShopItem>> shopItems;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String category = args[0].toLowerCase();
        String itemKey = args[1].toLowerCase();

        Map<String, ShopItem> validItems = shopItems.get(category);

        ShopItem shopItem = validItems.get(itemKey);


        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (shopItems.containsKey(category)) {

                if (validItems.containsKey(itemKey)) {
                    ItemStack purchasedItem = new ItemStack(shopItem.getMaterial(), shopItem.getAmount());

                    player.getInventory().addItem(purchasedItem);
                } else {
                    sender.sendMessage("Invalid Item!");
                }

            } else {
                sender.sendMessage("Invalid Category!");
            }

        }

        return true;
    }

    public Map<String, Map<String, ShopItem>> getShopItems() {
        return this.shopItems;
    }
}
