package de.yellowphoenix18.oitc.listener;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.yellowphoenix18.oitc.utils.Message;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class JoinListener implements Listener {
	
	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20.0);
		player.getInventory().clear();
		player.teleport(PluginUtils.locConfig.getLocation("lobby"));
		
		HashMap<String, String> replacements = new HashMap<String, String>();
		replacements.put("%Player%", player.getDisplayName());
		
		e.setJoinMessage(Message.PREFIX.getText() + Message.PLAYER_JOIN.getText(replacements));
	}

}
