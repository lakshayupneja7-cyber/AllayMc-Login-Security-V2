package com.allaymc.loginsecurity;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class AuthListener implements Listener {

    private final AuthService auth;

    public AuthListener(AuthService auth) {
        this.auth = auth;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (auth.isRegistered(player.getName())) {
            player.sendMessage(ChatColor.YELLOW + "Please login: /login <password>");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Please register: /register <password>");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!auth.isLoggedIn(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (auth.isLoggedIn(player)) return;

        String msg = event.getMessage().toLowerCase();

        if (!(msg.startsWith("/login") || msg.startsWith("/register"))) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You must login first.");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        auth.logout(event.getPlayer());
    }
}
