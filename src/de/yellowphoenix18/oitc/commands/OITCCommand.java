package de.yellowphoenix18.oitc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.yellowphoenix18.oitc.utils.Message;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class OITCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmdLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 0) {
				if(p.hasPermission("oitc.help")) {
					p.sendMessage(Message.PREFIX.getText() + "§cPlease use /oitc help for help");
				} else {
					p.sendMessage(Message.PREFIX.getText() + Message.NO_PERMISSION.getText());
				}
			} else {
				if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("oitc.help")) {
						p.sendMessage("§7» §6§lOITC §7«");
						p.sendMessage("§e/oitc §7- §bShows help-info");
						p.sendMessage("§e/oitc help §7- §bShows help");
						p.sendMessage("§e/oitc setLobby §7- §bSet Lobby-Location");
						p.sendMessage("§e/oitc setSpawn <ID> §7- §bSet Game-Spawn with given ID");
						p.sendMessage("§7» §6§lOITC §7«");
					} else {
						p.sendMessage(Message.PREFIX.getText() + Message.NO_PERMISSION.getText());
					}
				} else if(args[0].equalsIgnoreCase("setLobby")) {
					if(p.hasPermission("oitc.locations")) {
						if(args.length == 1) {
							PluginUtils.locConfig.setLocation("lobby", p.getLocation());
							p.sendMessage(Message.PREFIX.getText() + "§eLobby-Location has been §aset");
						} else {
							p.sendMessage(Message.PREFIX.getText() + "§cPlease use /oitc setLobby for help");
						}
					} else {
						p.sendMessage(Message.PREFIX.getText() + Message.NO_PERMISSION.getText());
					}
				} else if(args[0].equalsIgnoreCase("setSpawn")) {
					if(p.hasPermission("oitc.locations")) {
						if(args.length == 2) {
							PluginUtils.locConfig.setLocation("map.spawn." + args[1], p.getLocation());
							p.sendMessage(Message.PREFIX.getText() + "§eSpawn-Location with ID §6" + args[1] + " §ehas been §aset");
						} else {
							p.sendMessage(Message.PREFIX.getText() + "§cPlease use /oitc setSpawn <ID> for help");
						}
					} else {
						p.sendMessage(Message.PREFIX.getText() + Message.NO_PERMISSION.getText());
					}
				} else {
					p.sendMessage(Message.PREFIX.getText() + Message.COMMAND_UNKNOWN.getText());
				}
			}
		}
		
		
		return true;
	}

}
