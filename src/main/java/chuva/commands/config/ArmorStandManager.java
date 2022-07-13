package chuva.commands.config;

import chuva.Chuva;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class ArmorStandManager {

    public static ConfigFile configFile = new ConfigFile();

    public static void setArmorStand() {
        if (Chuva.getInstace().getConfig().get("ArmorStands")!=null) {
            for (String e : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                for (ArmorStand as : Bukkit.getWorld(configFile.getWorld(e)).getEntitiesByClass(ArmorStand.class)) {
                    as.remove();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorStand "+e+" removida!");
                }
            }
            for (String e : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                ArmorStand as = (ArmorStand) Bukkit.getWorld(configFile.getWorld(e)).spawnEntity(configFile.getLocation(e), EntityType.ARMOR_STAND);
                as.setCustomNameVisible(true);
                as.setVisible(false);
                as.setBasePlate(false);
                as.setCustomName(configFile.getDisplayName(e));
                as.setGravity(false);
                as.setArms(true);
                as.getEquipment().setHelmet(new ItemStack(Material.getMaterial(configFile.getHelmet(e).toString())));
                as.getEquipment().setChestplate(new ItemStack(Material.getMaterial(configFile.getChestplate(e).toString())));
                as.getEquipment().setLeggings(new ItemStack(Material.getMaterial(configFile.getLeggings(e).toString())));
                as.getEquipment().setBoots(new ItemStack(Material.getMaterial(configFile.getBoots(e).toString())));
                as.getEquipment().setItemInMainHand(new ItemStack(Material.getMaterial(configFile.getItemInMainHand(e).toString())));
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ArmorStands "+e+" re-colocada!");
            }
        }
    }
    public static void removeArmorStand(String nome) {
        if (Chuva.getInstace().getConfig().get("ArmorStands")!=null) {
            for (String e : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                if (Chuva.getInstace().getConfig().contains("ArmorStands." + nome)) {
                    for (ArmorStand as : Bukkit.getWorld(configFile.getWorld(nome)).getEntitiesByClass(ArmorStand.class)) {
                        as.remove();
                    }
                    Chuva.getInstace().getConfig().set("ArmorStands." + nome, null);
                    Chuva.getInstace().saveConfig();
                    setArmorStand();
                }
            }
        }
    }
}
