package com.nikolaipatrick.MinigamesManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class tabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabCompleteList = new ArrayList<>();
        if (args.length == 1) {
            tabCompleteList.add("help");
            tabCompleteList.add("create");
            tabCompleteList.add("list");
            tabCompleteList.add("add");
            tabCompleteList.add("set");
            tabCompleteList.add("join");
            tabCompleteList.add("reload");
            return tabCompleteList;
        }
        if (args.length == 2) {
            if (args[1].equals("help")) {
                tabCompleteList.add("1");
                tabCompleteList.add("2");
                tabCompleteList.add("3");
                return tabCompleteList;
            }
            if (args[1].equals("list")) {
                tabCompleteList.add("minigames");
                tabCompleteList.add("lobbies");
                tabCompleteList.add("arenas");
                return tabCompleteList;
            }
            if (args[1].equals("create")) {
                tabCompleteList.add("<name>");
                return tabCompleteList;
            }
            }
        return null;
    }
}
