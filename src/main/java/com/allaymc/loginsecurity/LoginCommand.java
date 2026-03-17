package com.allaymc.loginsecurity;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {

    private final AuthService auth;

    public RegisterCommand(AuthService auth) {
        this.auth = auth;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (auth.isRegistered(player.getName())) {
            player.sendMessage(ChatColor.RED + "You are already registered.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.YELLOW + "Usage: /register <password>");
            return true;
        }

        auth.register(player, args[0]);
        player.sendMessage(ChatColor.GREEN + "Registered and logged in!");

        return true;
    }
}
