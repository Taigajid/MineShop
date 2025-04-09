package io.github.Taigajid.MineShop;
import org.bukkit.plugin.java.JavaPlugin;

public class MineShop extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandShop commandShop = new CommandShop();
        getCommand("shop").setExecutor(commandShop);
        getCommand("shop").setTabCompleter(new ShopAutocomplete(commandShop.getShopItems()));

        getLogger().info("onEnable is called!");
        this.getCommand("shop").setExecutor(new CommandShop());
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }

}
