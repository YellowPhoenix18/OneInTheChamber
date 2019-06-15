package de.yellowphoenix18.oitc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.yellowphoenix18.oitc.utils.GameStatus;
import de.yellowphoenix18.oitc.utils.Message;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class LoginListener implements Listener {
	
	@EventHandler
	public void on(PlayerLoginEvent e) {
		if(PluginUtils.gameStatus != GameStatus.WAITING && PluginUtils.gameStatus != GameStatus.LOBBY) {
			e.disallow(Result.KICK_OTHER, Message.GAME_STARTED.getText());
		}
	}

}
