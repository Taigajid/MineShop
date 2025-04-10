package io.github.Taigajid.MineShop;

import org.bukkit.plugin.java.JavaPlugin;

public class MineShop extends JavaPlugin {
    private ShopGui shopGui;
    private CommandShop commandShop;

    @Override
    public void onEnable() {
        commandShop = new CommandShop();
        shopGui = new ShopGui(commandShop);
        commandShop = new CommandShop(shopGui, commandShop.getShopItems());

        getCommand("shop").setExecutor(commandShop);
        getCommand("shop").setTabCompleter(new ShopAutocomplete(commandShop.getShopItems()));
        getServer().getPluginManager().registerEvents(shopGui, this);

        getLogger().info("onEnable is called!");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }

}
