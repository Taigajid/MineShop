package io.github.Taigajid.MineShop;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class ShopGui implements Listener {
    private final Inventory inv;
    private final CommandShop commandShop;

    public ShopGui(CommandShop commandShop) {
        this.commandShop = commandShop;
        inv = Bukkit.createInventory(null, 27, "Shop");

        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(10, createGuiItem(Material.ELYTRA, "Elytra", "§aPrice: 1 Netherite Ingot","§bYou can use this item to fly through the air!"));
        inv.setItem(11, createGuiItem(Material.MACE, "Mace", "§aPrice: 1 Netherite Ingot", "§bYou can use this item to fight monsters!"));
        inv.setItem(15, createGuiItem(Material.OAK_LOG, "Oak Log", "§aPrice: 1 Diamond Block", "§bYou can use this item to build!"));
        inv.setItem(16, createGuiItem(Material.SPRUCE_LOG, "Spruce Log", "§aPrice: 1 Diamond Block", "§bYou can use this item to build!"));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;

    }

    public void openInventory(final HumanEntity player) {
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if(!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if(clickedItem == null || clickedItem.getType().isAir()) return;

        final Player player = (Player) e.getWhoClicked();

        String itemName = clickedItem.getItemMeta().getDisplayName().toLowerCase().replace(" ", "_");
        String category = "";
        if (itemName.equals("elytra") || itemName.equals("mace")) {
            category = "netherite";
        } else if (itemName.equals("oak_log") || itemName.equals("spruce_log")) {
            category = "diamond";
        }

        if (category.isEmpty()) {
            player.sendMessage("This item is not available!");
            return;
        }

        commandShop.onCommand(player, null, "shop", new String[]{category, itemName});

    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}
