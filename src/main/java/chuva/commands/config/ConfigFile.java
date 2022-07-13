package chuva.commands.config;

import chuva.Chuva;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigFile {

    public static List<ArmorStand> asList = new ArrayList<>();

    public String getPermission() {
        return Chuva.getInstace().getConfig().getString("Permissao_comando");
    }
    public String getWorld(String nome) {
        return Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".World");
    }
    public String semPerm() {
        return Chuva.getInstace().getConfig().getString("Mensagem_sem_permissao");
    }
    public String getDisplay(String nome) {
        return Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Display");
    }

    public Material getHelmet(String nome) {
        return Material.getMaterial(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Helmet"));
    }
    public String getCommand(String nome) {
        return Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Comando");
    }
    public void setCommand(String nome, String cmd) {
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Comando", cmd);
        Chuva.getInstace().saveConfig();
    }
    public boolean hasCommand(String nome) {
        return Chuva.getInstace().getConfig().contains("ArmorStands." + nome + ".Comando");
    }
    public Material getChestplate(String nome) {
        return Material.getMaterial(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Chestplate"));
    }
    public Material getLeggings(String nome) {
        return Material.getMaterial(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Leggings"));
    }
    public Material getBoots(String nome) {
        return Material.getMaterial(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Boots"));
    }
    public Material getItemInMainHand(String nome) {
        return Material.getMaterial(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Hand"));
    }
    public Location getLocation(String nome) {
        World world = Bukkit.getWorld(Objects.requireNonNull(Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".World")));
        double x = Chuva.getInstace().getConfig().getDouble("ArmorStands." + nome + ".X");
        double y = Chuva.getInstace().getConfig().getDouble("ArmorStands." + nome + ".Y");
        double z = Chuva.getInstace().getConfig().getDouble("ArmorStands." + nome + ".Z");
        double yaw = Chuva.getInstace().getConfig().getDouble("ArmorStands." + nome + ".Yaw");
        double pitch = Chuva.getInstace().getConfig().getDouble("ArmorStands." + nome + ".Pitch");
        return new Location(world, x, y, z);
    }
    public String getDisplayName(String nome) {
        return Chuva.getInstace().getConfig().getString("ArmorStands." + nome + ".Display").replace("&", "§");
    }
    public static void createArmorStand(Player player, String nome, String display) {
        Location loc = player.getLocation();
        ArmorStand as = (ArmorStand) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.ARMOR_STAND);
        as.setCustomNameVisible(true);
        as.setCustomName(display.replace("&", "§"));
        as.setVisible(false);
        as.setBasePlate(false);
        as.setGravity(false);
        as.setArms(true);
        //
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".World", loc.getWorld().getName());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".X", loc.getX());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Y", loc.getY());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Z", loc.getZ());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Pitch", loc.getPitch());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Yaw", loc.getYaw());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Display", display);
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Helmet", Material.AIR.toString());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Chestplate", Material.AIR.toString());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Leggings", Material.AIR.toString());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Boots", Material.AIR.toString());
        Chuva.getInstace().getConfig().set("ArmorStands." + nome + ".Hand", Material.AIR.toString());
        Chuva.getInstace().saveConfig();
    }
}
