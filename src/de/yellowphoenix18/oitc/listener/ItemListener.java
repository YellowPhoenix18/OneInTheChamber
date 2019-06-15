package de.yellowphoenix18.oitc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class ItemListener implements Listener {
	
	@EventHandler
	public void on(EntityPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void on(EntityDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void on(PlayerItemDamageEvent e) {
		e.setCancelled(true);
	}

}
