package chuva.events;

import chuva.Chuva;
import chuva.commands.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Events implements Listener {

    private ConfigFile configFile = new ConfigFile();

    @EventHandler
    void interact(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ArmorStand as) {
            for (String nome : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                if (as.getLocation().equals(configFile.getLocation(nome))) {
                    if (configFile.hasCommand(nome)) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                configFile.getCommand(nome).replace("&player", player.getName()));
                    }
                }
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    void interact2(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ArmorStand as) {
            for (String nome : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                if (as.getLocation().equals(configFile.getLocation(nome))) {
                    if (configFile.hasCommand(nome)) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                configFile.getCommand(nome).replace("&player", player.getName()));
                    }
                }
            }
            event.setCancelled(true);
        }
    }

}
