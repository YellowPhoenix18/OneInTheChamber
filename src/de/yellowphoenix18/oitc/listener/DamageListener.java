package de.yellowphoenix18.oitc.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

import de.yellowphoenix18.oitc.utils.GameStatus;
import de.yellowphoenix18.oitc.utils.PluginUtils;

public class DamageListener implements Listener {
	
	@EventHandler
	public void on(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getEntity();
			
			if(e.getHitEntity() != null) {
				if(e.getHitEntity() instanceof Player) {
					Player player = (Player) e.getHitEntity();
					Player killer = (Player) arrow.getShooter();
					
					PluginUtils.killPlayer(player, killer);
					arrow.remove();
				}
			} else {
				arrow.remove();
			}
		}	
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		//TODO: Check for Spectator
		if(PluginUtils.gameStatus == GameStatus.GAME) {
			if(e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				
				if(e.getDamager() instanceof Player) {
					if(player.getHealth()-e.getDamage() <= 0.0) {
						Player killer = (Player) e.getDamager();
						e.setCancelled(true);
						PluginUtils.killPlayer(player, killer);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void on(EntityDamageEvent e) {
		if(e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.PROJECTILE || PluginUtils.gameStatus != GameStatus.GAME) {
			e.setCancelled(true);
		}
	}

}
