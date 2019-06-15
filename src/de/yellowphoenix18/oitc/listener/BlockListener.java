package de.yellowphoenix18.oitc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
	
	@EventHandler
	public void on(BlockBreakEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e) {
		e.setCancelled(true);
	}

}
