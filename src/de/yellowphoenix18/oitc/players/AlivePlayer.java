package de.yellowphoenix18.oitc.players;

import org.bukkit.entity.Player;

import de.yellowphoenix18.oitc.utils.PluginUtils;

public class AlivePlayer {
	
	private Player player;
	
	private int kills;
	private int deaths;

	public AlivePlayer(Player player) {
		this.player = player;
		
		this.kills = 0;
		this.deaths = 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
		PluginUtils.scoreboard.updateScore(this);
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

}
