
package com.nikolaipatrick.MinigamesManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MinigamesManager extends JavaPlugin implements CommandExecutor {

    String prefix = this.getConfig().getString("command-prefix");
    String minigameName;
    String worldType;
    String minigame;
    String worldName;
    String[] existingWorlds;


    @Override
    public void onEnable() {
        getLogger().info("MinigamesManager 1.0.0 is loading!");


        this.saveDefaultConfig();
        //this.getCommand("minigames").setExecutor(new commandMinigame());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        getLogger().info("Goodbye!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length > 0) {
            if (args[0].equals("create")) {
                if (args.length < 2) {
                    player.sendMessage(prefix + "§cYou have to give the minigame a name. (Usage: /minigames create <name>)");
                    return true;
                }
                minigameName = args[1];
                this.getConfig().set(minigameName + ".name", minigameName);
                this.saveConfig();
                player.sendMessage(prefix + "§aMinigame created! Add worlds with /minigames add");
                return true;
            }
            if (args[0].equals("add")) {
                if (args.length < 4) {
                    player.sendMessage(prefix + "§cNot enough args! (usage: /minigames add <lobby/arena> <minigame> <worldName>)");
                }
                worldType = args[1];
                minigameName = args[2];
                worldName = args[3];
                if (worldType.equals("lobby")) {
                    //Arrays.fill(worldNames, null);
                    this.getConfig().set(minigameName + ".lobbies", worldName);
                    player.sendMessage(prefix + "§aWorld/s added to" + minigameName + "!");
                    this.saveConfig();
                    return true;
                } else if (worldType.equals("arena")) {
                    //Arrays.fill(worldNames, null);
                    this.getConfig().set(minigameName + ".arenas", worldName);
                    player.sendMessage(prefix + "§aWorld/s added to" + minigameName + "!");
                    this.saveConfig();
                    return true;
                }
                else   {
                    player.sendMessage(prefix + "§cInvalid world type! Please choose lobby or arena");
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




