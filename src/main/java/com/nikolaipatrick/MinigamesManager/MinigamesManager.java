
package com.nikolaipatrick.MinigamesManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class MinigamesManager extends JavaPlugin implements CommandExecutor {



    String prefix = this.getConfig().getString("command-prefix");
    String minigameName;
    String worldType;
    String worldName;
    //String[] existingWorlds;
    String[] allWorlds;
    String test;

    HashMap<String, Integer> isInProgress = new HashMap<String, Integer>();


    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "  __  __" + ChatColor.GREEN + " __  __ ");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + " |  \\/  |" + ChatColor.GREEN + "  \\/  |");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + " | \\  / |" + ChatColor.GREEN + " \\  / |" + ChatColor.RED + " Minigames"  + ChatColor.GREEN + "Manager");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + " | |\\/| |" + ChatColor.GREEN + " |\\/| | V1.0.0-SNAPHOT");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + " |_|  |_|" + ChatColor.GREEN + "_|  |_|");
        this.saveDefaultConfig();
        //this.getCommand("minigames").setExecutor(new commandMinigame());
        /*for (String configSection : getConfig().getConfigurationSection("minigames").getKeys(false)) {
            onlinePlayers.put(configSection, 0);
        }
        */
        getCommand("minigames").setTabCompleter(new tabComplete());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        getLogger().info("Goodbye!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //existingWorlds = new String[255];
        Player player = (Player) sender;
        if (args.length > 0) {
            if (args[0].equals("create")) {
                if (args.length < 2) {
                    player.sendMessage(prefix + "§cYou have to give the minigame a name. (Usage: /minigames create <name>)");
                    return true;
                }
                minigameName = args[1];
                this.getConfig().set("minigames." + minigameName + ".name", minigameName);
                this.saveConfig();
                player.sendMessage(prefix + "§aMinigame created! Add worlds with /minigames add");
                return true;
            }
            if (args[0].equals("set")) {
                if (args.length < 3) {
                    player.sendMessage(prefix + " §cNot enough args! §e(usage: /minigames set maxPlayers <minigame> <amount>)");
                    return true;
                }
                else {
                    if (args[2] == "maxplayers") {
                        this.getConfig().set(args[1] + ".maxPlayers", args[3]);
                        this.saveConfig();
                        this.reloadConfig();
                        return true;

                    }
                }
            }
            if (args[0].equals("add")) {
                if (args.length < 4) {
                    player.sendMessage(prefix + "§cNot enough args! §e(usage: /minigames add <lobby/arena> <minigame> <worldName>)");
                    return true;
                }
                worldType = args[1];
                minigameName = args[2];
                worldName = args[3];
                if (worldType.equals("lobby")) {
                    List<String> existingWorldsList = getConfig().getStringList("minigames." + minigameName + ".lobbies");
                    existingWorldsList.add(worldName);
                    getConfig().set("minigames." + minigameName + ".lobbies", existingWorldsList);
                    player.sendMessage(prefix + "§aWorld added to §b" + minigameName + "§a successfully!");
                    this.saveConfig();
                    this.reloadConfig();
                    return true;
                } else if (worldType.equals("arena")) {
                    List<String> existingWorldsList = getConfig().getStringList(minigameName + ".arenas");
                    existingWorldsList.add(worldName);
                    getConfig().set(minigameName + ".arenas", existingWorldsList);
                    player.sendMessage(prefix + "§aWorld added to §b" + minigameName + "§a successfully!");
                    this.saveConfig();
                    this.reloadConfig();
                    return true;
                }
            }
            if (args[0].equals("join")) {
                if (args.length < 2) {
                    player.sendMessage(prefix + "§cNot enough args! §e(usage: /minigames join <minigame>)");
                }
                else {
                }

            }
            if (args[0].equals("help")) {
                player.sendMessage("§e---Help page 1/1---");
                player.sendMessage("§e/minigames help - §aDisplays this help page");
                player.sendMessage("§e/minigames join <minigame> - §aJoins you to a minigame");
                player.sendMessage("§e/minigames create <minigame-name> - §aCreates a new minigame");
                player.sendMessage("§e/minigames add <lobby/arena> <minigame> <worldName> - §aAdds a world to a minigame");
                player.sendMessage("§e/minigames list <worlds/lobbies> <minigame> - §aLists worlds in a minigame");
                player.sendMessage("§e/minigames reload - §aReloads all configuration files");
                return true;
            }
            if (args[0].equals("list")) {
                player.sendMessage("§e/minigames list <worlds/lobbies> <minigame> - §aLists worlds in a minigame");
                return true;
            }
            if (args[0].equals("moo")) {
                player.sendMessage(prefix + " §cThere are no easter eggs in this plugin.");
                return true;
            }
            if (args[0].equals("reload")) {
                this.reloadConfig();
                player.sendMessage(prefix + "§aConfiguration files reloaded!");
                return true;
            } else {
                player.sendMessage(prefix + "§cInvalid command! Type /help for a list of commands");
                return true;
            }
        }
        player.sendMessage(prefix + "§cNot enough args! Type /minigames help for a list of commands");
        return true;
    }
}




