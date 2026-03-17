package com.allaymc.loginsecurity;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthService {

    private final Set<String> loggedIn = new HashSet<>();
    private final Map<String, String> passwords = new HashMap<>();

    public boolean isRegistered(String name) {
        return passwords.containsKey(name.toLowerCase());
    }

    public void register(Player player, String password) {
        passwords.put(player.getName().toLowerCase(), password);
        loggedIn.add(player.getName().toLowerCase());
    }

    public boolean login(Player player, String password) {
        String stored = passwords.get(player.getName().toLowerCase());
        if (stored != null && stored.equals(password)) {
            loggedIn.add(player.getName().toLowerCase());
            return true;
        }
        return false;
    }

    public boolean isLoggedIn(Player player) {
        return loggedIn.contains(player.getName().toLowerCase());
    }

    public void logout(Player player) {
        loggedIn.remove(player.getName().toLowerCase());
    }
}
