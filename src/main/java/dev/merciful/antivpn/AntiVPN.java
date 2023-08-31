package dev.merciful.antivpn;

import dev.merciful.antivpn.commands.VPNCommand;
import dev.merciful.antivpn.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiVPN extends JavaPlugin {

    public void onEnable() {
        getCommand("antivpn").setExecutor(new VPNCommand(this));
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);
        Bukkit.getServer().getConsoleSender().sendMessage("AntiVPN: has started with no issues!<3");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage("AntiVPN: has stopped.");
    }
}
