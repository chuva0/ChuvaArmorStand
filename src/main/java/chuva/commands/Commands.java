package chuva.commands;

import chuva.Chuva;
import chuva.commands.config.ArmorStandManager;
import chuva.commands.config.ConfigFile;
import chuva.commands.config.InventoryArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    ConfigFile configFile = new ConfigFile();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player player)) {
            return true;
        }
        if (command.getName().equalsIgnoreCase("chuva")) {
            if (player.hasPermission(configFile.getPermission())) {
                if (args.length==0) {
                    player.sendMessage(ChatColor.GREEN + "Use /chuva criar (nome) (nome a mostra)   " + ChatColor.GRAY + "(Criar uma ArmorStand)");
                    player.sendMessage(ChatColor.GREEN + "Use /chuva remover (nome)   "+ ChatColor.GRAY + "(Remover uma ArmorStand)");
                    player.sendMessage(ChatColor.GREEN + "Use /chuva lista (gui)   "+ ChatColor.GRAY + "(Ver a lista de ArmorStands ja criadas!)");
                    player.sendMessage(ChatColor.GREEN + "Use /chuva comando (nome) (comando *sem barra)   "+ ChatColor.GRAY + "(Adicionar comando na interacao)");
                    player.sendMessage(ChatColor.GREEN + "Use /chuva reload   " + ChatColor.GRAY + "(Reiniciar a config.yml)");
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Chuva.getInstace().reloadConfig();
                    player.sendMessage(ChatColor.GREEN + "config.yml atualizada!");
                    ArmorStandManager.setArmorStand();
                    return true;
                }
                if (args[0].equalsIgnoreCase("remover")) {
                    if (args.length == 1) {
                        player.sendMessage(ChatColor.GREEN + "Use /chuva remover (nome)");
                        player.sendMessage(ChatColor.GREEN + "Para saber suas ArmorStands criadas, use /chuva lista");
                    }
                    if (args.length == 2) {
                        String nome = args[1];
                        for (String nomes : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                           if (nome.equals(nomes)) {
                               configFile.getLocation(nome);
                               ArmorStandManager.removeArmorStand(nome);
                               player.sendMessage(ChatColor.GREEN + "Voce removeu a ArmorStand " + nome);
                               return true;
                           }
                        }
                        player.sendMessage(ChatColor.RED + "ArmorStand nao encontrada!");
                    }
                }
                if (args[0].equalsIgnoreCase("lista")) {
                    if (args.length == 1) {
                        if (Chuva.getInstace().getConfig().get("ArmorStands") != null) {
                            for (String nomes : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                                player.sendMessage(ChatColor.GREEN + "ArmorStand: '" + nomes + "' Display: '" + configFile.getDisplay(nomes));
                            }
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "Ainda sem nenhuma ArmorStand!");
                            return true;
                        }
                    }
                    if (args.length == 2) {
                        if (Chuva.getInstace().getConfig().get("ArmorStands") != null) {
                            if (args[1].equalsIgnoreCase("gui")) {
                                InventoryArmorStand.guiArmorStand(player);
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Ainda sem nenhuma ArmorStand!");
                            return true;
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("comando")) {
                    if (args.length==1) {
                        player.sendMessage(ChatColor.GREEN + "Use /chuva comando (nome) (comando)");
                        return true;
                    }
                    if (args.length < 3) {
                        player.sendMessage(ChatColor.GREEN + "Use /chuva comando (nome) (comando)");
                    }
                    String nome = args[1];

                    for (String nomes : Chuva.getInstace().getConfig().getConfigurationSection("ArmorStands").getKeys(false)) {
                        if (nome.equals(nomes)) {
                            List<String> comando = new ArrayList<>();
                            for (int i = 2; i < args.length; i++) {
                                comando.add(args[i]);
                            }
                            String cmd = String.join(" ", comando);

                            player.sendMessage(ChatColor.GREEN + "Adicionado o comando '/"+cmd+"' a ArmorStand " + nome + "!");
                            configFile.setCommand(nome, cmd);
                            return true;
                        }
                    }
                    player.sendMessage(ChatColor.RED + "ArmorStand nao encontrado!");
                }
                if (args[0].equalsIgnoreCase("criar")) {
                    if (args.length <= 2) {
                        player.sendMessage(ChatColor.GREEN + "Use /chuva criar (nome) (nome a mostra)");
                        return true;
                    }
                    if (Chuva.getInstace().getConfig().contains("ArmorStands." + args[1])) {
                        player.sendMessage(ChatColor.RED + "Ja existe um ArmorStand com esse nome!");
                        return true;
                    }
                    if (args.length == 3) {
                        ConfigFile.createArmorStand(player, args[1], args[2]);
                        player.sendMessage(ChatColor.GREEN + "ArmorStand criado! ");
                        player.sendMessage(ChatColor.GREEN + "Use /chuva comando (nome) (comando)");
                        return true;
                    }
                    List<String> mensagem = new ArrayList<>();
                    for (int i = 2; i < args.length; i++) {
                        mensagem.add(args[i]);
                    }
                    String display = String.join(" ", mensagem);

                    ConfigFile.createArmorStand(player, args[1], display);
                    player.sendMessage(ChatColor.GREEN + "ArmorStand criado! " + display);
                    return true;
                }
            } else {
                player.sendMessage(configFile.semPerm().replace("&", "§"));
            }
        }
        return false;
    }
}
