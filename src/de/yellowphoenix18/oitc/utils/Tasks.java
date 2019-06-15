package de.yellowphoenix18.oitc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.yellowphoenix18.oitc.OITC;
import de.yellowphoenix18.oitc.players.AlivePlayer;

public class Tasks {
	
	private int waiting;
	private int lobby;
	private int pregame;
	private int game;
	private int endgame;
	
	private int waitingTime;
	private int lobbyTime;
	private int pregameTime;
	private int gameTime;
	private int endgameTime;
	
	public void load() {
		this.waitingTime = PluginUtils.mainConfig.getWaitingTime();
		this.lobbyTime = PluginUtils.mainConfig.getLobbyTime();
		this.pregameTime = PluginUtils.mainConfig.getPregameTime();
		this.gameTime = PluginUtils.mainConfig.getGameTime();
		this.endgameTime = PluginUtils.mainConfig.getEndgameTime();
		
		this.waiting();
	}
	
	private void waiting() {
		PluginUtils.gameStatus = GameStatus.WAITING;
		this.waiting = Bukkit.getScheduler().scheduleSyncRepeatingTask(OITC.m, new Runnable() {
			@Override
			public void run() {
				waitingTime--;
				
				if (waitingTime == 0) {
					waitingTime = PluginUtils.mainConfig.getWaitingTime();
					
					HashMap<String, String> replacements = new HashMap<String, String>();
					replacements.put("%Current_Players%", "" + Bukkit.getOnlinePlayers().size());
					replacements.put("%Needed_Players%", "" + PluginUtils.mainConfig.getNeededPlayers());
					
					Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_WAITING.getText(replacements));
				}
				
				if(Bukkit.getOnlinePlayers().size() >= PluginUtils.mainConfig.getNeededPlayers()) {
					Bukkit.getScheduler().cancelTask(waiting);
					waitingTime = PluginUtils.mainConfig.getWaitingTime();
					lobby();
				}
			}		
		}, 15, 20);
	}
	
	private void lobby() {
		PluginUtils.gameStatus = GameStatus.LOBBY;
		this.lobby = Bukkit.getScheduler().scheduleSyncRepeatingTask(OITC.m, new Runnable() {
			@Override
			public void run() {
				lobbyTime--;

				if(lobbyTime == 60 || lobbyTime == 30 || lobbyTime == 15 || lobbyTime == 10 || lobbyTime == 5 || lobbyTime == 3 || lobbyTime == 2 || lobbyTime == 1) {
					HashMap<String, String> replacements = new HashMap<String, String>();
					replacements.put("%Time%", "" + lobbyTime);
					
					Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_LOBBY.getText(replacements));
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					}
				}
				
				if(lobbyTime == 0) {
					Bukkit.getScheduler().cancelTask(lobby);
					int count = 0;
					for(Player all : Bukkit.getOnlinePlayers()) {
						count++;
						all.teleport(PluginUtils.locConfig.getLocation("map.spawn." + count));
						PluginUtils.alive.put(all.getUniqueId().toString(), new AlivePlayer(all));
					}
					pregame();
				}
				
				if(Bukkit.getOnlinePlayers().size() < PluginUtils.mainConfig.getNeededPlayers()) {
					Bukkit.getScheduler().cancelTask(lobby);
					lobbyTime = PluginUtils.mainConfig.getLobbyTime();
					waiting();
				}
			}		
		}, 15, 20);
	}
	
	private void pregame() {
		PluginUtils.gameStatus = GameStatus.PREGAME;
		this.pregame = Bukkit.getScheduler().scheduleSyncRepeatingTask(OITC.m, new Runnable() {
			@Override
			public void run() {
				pregameTime--;
				
				if(pregameTime == 10 || pregameTime == 5 || pregameTime == 3 || pregameTime == 2 || pregameTime == 1) {
					HashMap<String, String> replacements = new HashMap<String, String>();
					replacements.put("%Time%", "" + pregameTime);
					
					Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_PREGAME.getText(replacements));
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					}
				}
				
				if(pregameTime == 0) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						setPlayerInventory(all);
					}
					Bukkit.getScheduler().cancelTask(pregame);
					game();
				}
				
			}		
		}, 15, 20);
	}
	
	private void game() {
		PluginUtils.scoreboard.setScoreboardPlayers();
		PluginUtils.gameStatus = GameStatus.GAME;
		this.game = Bukkit.getScheduler().scheduleSyncRepeatingTask(OITC.m, new Runnable() {
			@Override
			public void run() {
				gameTime--;
				
				int modGameTime = gameTime % 60;
				
				if(modGameTime == 0 && gameTime != 0 || gameTime == 30 || gameTime == 15 || gameTime == 10 || gameTime == 5 || gameTime == 3 || gameTime == 2 || gameTime == 1) {
					HashMap<String, String> replacements = new HashMap<String, String>();
					
					if(modGameTime == 0) {
						int minutes = (int) gameTime/60;
						replacements.put("%Time%", "" + minutes);
						Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_GAME_MINUTES.getText(replacements));
					} else {
						replacements.put("%Time%", "" + gameTime);
						Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_GAME_SECONDS.getText(replacements));
					}
				}
				
				if(gameTime == 0) {
					Bukkit.getScheduler().cancelTask(game);
					endgame(winKills());
				}
				
			}		
		}, 15, 20);		
	}
	
	private void endgame(AlivePlayer player) {
		announceWinner(player);
		PluginUtils.gameStatus = GameStatus.ENDGAME;
		this.endgame = Bukkit.getScheduler().scheduleSyncRepeatingTask(OITC.m, new Runnable() {
			@Override
			public void run() {
				endgameTime--;
				
				if(endgameTime == 15 || endgameTime == 10 || endgameTime == 5 || endgameTime == 3 || endgameTime == 2 || endgameTime == 1) {
					HashMap<String, String> replacements = new HashMap<String, String>();
					
					replacements.put("%Time%", "" + endgameTime);
					Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.TIMES_ENDGAME.getText(replacements));
				}
				
				if(endgameTime == 0) {
					Bukkit.getScheduler().cancelTask(endgame);
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.kickPlayer(Message.PREFIX.getText() + Message.END_KICK.getText());
					}
					Bukkit.shutdown();
				}
				
			}		
		}, 15, 20);	
	}
	
	public void winQuit() {
		
		List<String> stringList = new ArrayList<>(PluginUtils.alive.keySet());
		AlivePlayer player = PluginUtils.alive.get(stringList.get(0));
		
		Bukkit.getScheduler().cancelTask(game);
		endgame(player);
	}
	
	public AlivePlayer winKills() {
		int kills = 0;
		AlivePlayer winner = null;
		for(String key : PluginUtils.alive.keySet()) {
			AlivePlayer player = PluginUtils.alive.get(key);
			if(player.getKills() >= kills) {
				kills = player.getKills();
				winner = player;
			}
		}
		
		return winner;
	}
	
	private void announceWinner(AlivePlayer player) {
		HashMap<String, String> replacements = new HashMap<String, String>();		
		replacements.put("%Player%", player.getPlayer().getDisplayName());
		Bukkit.broadcastMessage(Message.PREFIX.getText() + Message.PLAYER_WIN.getText(replacements));
	}
	
	private void setPlayerInventory(Player p) {
		p.getInventory().setItem(0, new ItemStack(Material.BOW));
		p.getInventory().setItem(1, new ItemStack(Material.WOODEN_SWORD));
		p.getInventory().setItem(8, new ItemStack(Material.ARROW));
	}

}
