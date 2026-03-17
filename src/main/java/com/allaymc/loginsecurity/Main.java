package com.allaymc.loginsecurity;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        AuthService auth = new AuthService();

        getServer().getPluginManager().registerEvents(new AuthListener(auth), this);

        getCommand("register").setExecutor(new RegisterCommand(auth));
        getCommand("login").setExecutor(new LoginCommand(auth));

        getLogger().info("AllayMc LoginSecurity enabled!");
    }
}
