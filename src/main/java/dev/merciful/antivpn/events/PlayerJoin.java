package dev.merciful.antivpn.events;

import dev.merciful.antivpn.AntiVPN;
import dev.merciful.antivpn.util.PlayerManager;
import dev.merciful.antivpn.util.VPNChecker;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Collection;

public class PlayerJoin implements Listener {

    private final AntiVPN plugin;
    private Collection<? extends Player> players = Bukkit.getOnlinePlayers();

    public PlayerJoin(AntiVPN plugin) {
        this.plugin = plugin;
    }

    public void onJoin(AsyncPlayerPreLoginEvent e) {
        String kickMessage = plugin.getConfig().getString("Kick Message");
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getUniqueId());
        String address = e.getAddress().toString();
        if(!offlinePlayer.isOp() || !offlinePlayer.getPlayer().hasPermission("Antivpn.bypass") || !offlinePlayer.getPlayer().hasPermission("Antivpn.*") || !PlayerManager.getIgnoredPlayers().contains(e.getName().toLowerCase())){
        if (PlayerManager.getHashMap().containsKey(address)) {
                if (PlayerManager.getHashMap().get(address)){
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, kickMessage);
            }
        } else {
            Boolean hasVPN = VPNChecker.checkIP(address, this.plugin);
            PlayerManager.getHashMap().put(e.getAddress().toString(), hasVPN);
            if (hasVPN) {
                if (plugin.getConfig().getBoolean("Kick Player")) {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, kickMessage);
                    for (Player player : players) {
                        if (player.hasPermission("Antivpn.notify") || player.hasPermission("Antivpn.*")) {
                            player.sendMessage(e.getName() + " tried joining on a VPN/Proxy!");
                        }
                        for (Player player1 : players) {
                            if (player1.hasPermission("Antivpn.notify") || player1.hasPermission("Antivpn.*")) {
                                player1.sendMessage(e.getName() + " is on a VPN/Proxy!");
                            }
                        }
                    }
                    }
                }
            }

        }
    }
}