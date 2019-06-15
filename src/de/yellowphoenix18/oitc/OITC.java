package de.yellowphoenix18.oitc;

import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.oitc.utils.PluginUtils;

public class OITC extends JavaPlugin {
	
	public static OITC m;
	
	public void onEnable() {
		m = this;
		PluginUtils.setUp();
	}
	
	public void onDisable() {
		PluginUtils.scoreboard.stopManager();
	}

}
