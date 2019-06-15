package de.yellowphoenix18.oitc.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.yellowphoenix18.oitc.utils.GameStatus;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class MoveListener implements Listener {
	
	@EventHandler
	public void on(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		
		if(PluginUtils.gameStatus == GameStatus.PREGAME) {
			if(player.getLocation().getY() <= 0) {
				PluginUtils.killPlayer(player, null);
			}
			
			if(PluginUtils.alive.containsKey(player.getUniqueId().toString())) {
				Location form = e.getFrom();
				Location to = e.getTo();
				
				e.setTo(new Location(form.getWorld(), form.getX(), to.getY(), form.getZ(), to.getYaw(), to.getPitch()));;
			}
		}
	}

}
