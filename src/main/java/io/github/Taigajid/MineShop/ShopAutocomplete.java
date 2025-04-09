package io.github.Taigajid.MineShop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopAutocomplete implements TabCompleter {

    private final Map<String, Map<String, ShopItem>> shopItems;

    public ShopAutocomplete(Map<String, Map<String, ShopItem>> shopItems) {
        this.shopItems = shopItems;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(shopItems.keySet());
        } else if (args.length == 2) {

            String category = args[0].toLowerCase();

            if (shopItems.containsKey(category)) {
                completions.addAll(shopItems.get(category).keySet());
            }
        }

        return completions;
    }
}
