package dev.merciful.antivpn.commands;

import dev.merciful.antivpn.AntiVPN;
import dev.merciful.antivpn.util.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VPNCommand implements CommandExecutor {
    private AntiVPN plugin;
    private Player player;

    public VPNCommand(AntiVPN plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        this.player = (Player) commandSender;
        if (commandSender.hasPermission("Antivpn.command") || commandSender.hasPermission("Antivpn.*")){
            if (args.length == 0){
                if (player.hasPermission("Antivpn.reload") || player.hasPermission("Antivpn.*")) {
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    player.sendMessage("AntiVPN: Config.yml has been reloaded!");
                }else {
                    noPermission();
                }
                }else {
                switch (args[1].toLowerCase()) {
                    case "ignore": ignore(args[2]);
                    case "unignore":unignore(args[2]);
                    default: player.sendMessage("AntiVPN: Unknown Subcommand!");
                }
            }
        }else {
            noPermission();
        }



        return true;
    }
    private void noPermission(){
        this.player.sendMessage("You do not have permission to perform this command!");
    }
    private void ignore(String name) {
        if (this.player.hasPermission("antivpn.ignore") || this.player.hasPermission("antivpn.*")) {
            if (name == null) {
                this.player.sendMessage("Please give a player to exclude!");
            } else {
                PlayerManager.getIgnoredPlayers().add(name.toLowerCase());
            }
        }else {
            noPermission();
        }
    }
    private void unignore(String name){
        if (this.player.hasPermission("antivpn.ignore") || this.player.hasPermission("antivpn.*")) {
            if (name == null) {
                this.player.sendMessage("Please give a player to unignore!");
            } else {
                PlayerManager.getIgnoredPlayers().remove(name.toLowerCase());
            }
        }else {
            noPermission();
        }
    }
}