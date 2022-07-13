package chuva.commands.config;

import chuva.Chuva;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryArmorStand implements Listener {

    static ConfigFile configFile = new ConfigFile();
    static int i = 0;

    public static void guiArmorStand(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "ArmorStands");

        i = 10;

        Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false).forEach(nome->{
            ItemStack as = new ItemStack(Material.ARMOR_STAND);
            ItemMeta zas = as.getItemMeta();
            zas.setDisplayName(nome);
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.GREEN + "   Clique com o esquerdo para ir ate a ArmorStand!   ");
            lore.add("");
            lore.add("");
            lore.add(ChatColor.GRAY + "  Display: " + ChatColor.WHITE + configFile.getDisplay(nome).replace("&", "§"));
            lore.add("");
            zas.setLore(lore);
            as.setItemMeta(zas);

            inv.setItem(i, as);
            i++;

            if (i==17) {
                i=19;
            }
            if (i==26) {
                i=28;
            }
            if (i==35) {
                i=37;
            }
            if (i==44) {
                i=46;
            }

        });

        player.openInventory(inv);
    }

    @EventHandler
    void inv(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.GREEN + "ArmorStands")) {
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getClickedInventory() == null) {
                return;
            }
            if (event.getCurrentItem().hasItemMeta()) {
                String nome = event.getCurrentItem().getItemMeta().getDisplayName();
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(nome)) {
                    player.teleport(configFile.getLocation(nome));
                }
            }
            event.setCancelled(true);
        }
    }
}
