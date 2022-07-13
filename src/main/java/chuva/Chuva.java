package chuva;

import chuva.commands.Commands;
import chuva.commands.config.ArmorStandManager;
import chuva.commands.config.InventoryArmorStand;
import chuva.events.Events;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Chuva extends JavaPlugin {

    private static Chuva instace;

    @Override
    public void onEnable() {
        instace = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(false);
        ArmorStandManager.setArmorStand();
        register();
    }

    @Override
    public void onDisable() {

    }

    public static Chuva getInstace() {
        return instace;
    }
    private void register() {
        Objects.requireNonNull(getCommand("chuva")).setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new InventoryArmorStand(), this);
    }
}
