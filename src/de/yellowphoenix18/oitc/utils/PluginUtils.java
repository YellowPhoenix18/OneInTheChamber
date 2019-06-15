package de.yellowphoenix18.oitc.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import de.yellowphoenix18.oitc.OITC;
import de.yellowphoenix18.oitc.commands.OITCCommand;
import de.yellowphoenix18.oitc.config.LocationConfig;
import de.yellowphoenix18.oitc.config.MainConfig;
import de.yellowphoenix18.oitc.config.MessagesConfig;
import de.yellowphoenix18.oitc.listener.BlockListener;
import de.yellowphoenix18.oitc.listener.DamageListener;
import de.yellowphoenix18.oitc.listener.DeafaultListener;
import de.yellowphoenix18.oitc.listener.ItemListener;
import de.yellowphoenix18.oitc.listener.JoinListener;
import de.yellowphoenix18.oitc.listener.MoveListener;
import de.yellowphoenix18.oitc.listener.QuitListener;
import de.yellowphoenix18.oitc.players.AlivePlayer;
import de.yellowphoenix18.oitc.scoreboard.ScoreboardManager;

public class PluginUtils {
	
	public static LocationConfig locConfig;
	public static MainConfig mainConfig;
	public static MessagesConfig messagesConfig;
	public static Tasks tasks;
	public static ScoreboardManager scoreboard;
	public static GameStatus gameStatus = GameStatus.WAITING;
	public static HashMap<String, AlivePlayer> alive = new HashMap<String, AlivePlayer>();
	
	public static void setUp() {
		loadConfigs();
		loadListener();
		loadCommands();
		
		tasks = new Tasks();
		tasks.load();
		
		scoreboard = new ScoreboardManager();
	}
	
	public static void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new DamageListener(), OITC.m);
		pm.registerEvents(new DeafaultListener(), OITC.m);
		pm.registerEvents(new ItemListener(), OITC.m);
		pm.registerEvents(new JoinListener(), OITC.m);
		pm.registerEvents(new MoveListener(), OITC.m);
		pm.registerEvents(new QuitListener(), OITC.m);
		pm.registerEvents(new BlockListener(), OITC.m);
	}
	
	public static void loadCommands() {
		OITC.m.getCommand("oitc").setExecutor(new OITCCommand());
	}
	
	public static void loadConfigs() {
		locConfig = new LocationConfig(new File("plugins/OneInTheChamber", "locations.yml"));
		mainConfig = new MainConfig(new File("plugins/OneInTheChamber", "config.yml"));
		messagesConfig = new MessagesConfig(new File("plugins/OneInTheChamber", "messages.yml"));
	}
	
	public static void killPlayer(Player player, Player killer) {
		AlivePlayer killed = PluginUtils.alive.get(player.getUniqueId().toString());
		
		HashMap<String, String> replacements = new HashMap<String, String>();
		replacements.put("%Player%", player.getDisplayName());
		
		if(killer != null) {
			AlivePlayer damager = PluginUtils.alive.get(killer.getUniqueId().toString());
			damager.setKills(damager.getKills()+1);
			
			killer.getInventory().setItem(8, new ItemStack(Material.ARROW));
			killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
			replacements.put("%Killer%", killer.getDisplayName());
			
			Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.PLAYER_DEATH_KILLER.getText(replacements));
		} else {
			Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.PLAYER_DEATH.getText(replacements));
		}

		killed.setDeaths(killed.getDeaths()+1);
		
		player.setHealth(20.0);
		
		Random rnd = new Random();
		int pos = rnd.nextInt(Bukkit.getServer().getMaxPlayers())+1;
		
		
		player.teleport(PluginUtils.locConfig.getLocation("map.spawn." + pos));	
		
		player.getInventory().setItem(8, new ItemStack(Material.ARROW));
	}

}
