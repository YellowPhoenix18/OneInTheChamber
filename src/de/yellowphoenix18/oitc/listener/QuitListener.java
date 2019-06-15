package de.yellowphoenix18.oitc.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.yellowphoenix18.oitc.utils.GameStatus;
import de.yellowphoenix18.oitc.utils.Message;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class QuitListener implements Listener {
	
	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		HashMap<String, String> replacements = new HashMap<String, String>();
		replacements.put("%Player%", player.getDisplayName());
		
		e.setQuitMessage(Message.PREFIX.getText() + Message.PLAYER_QUIT.getText(replacements));
		
		if(PluginUtils.gameStatus == GameStatus.GAME) {
			PluginUtils.alive.remove(player.getUniqueId().toString());
			if(PluginUtils.alive.size() <= 1) {
				PluginUtils.tasks.winQuit();
			}
		}
	}

}
