package de.yellowphoenix18.oitc.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.yellowphoenix18.oitc.players.AlivePlayer;
import de.yellowphoenix18.oitc.utils.Message;

public class ScoreboardManager {
	
	public Scoreboard score;
	public Objective obj;
	
	public ScoreboardManager() {
		this.score = Bukkit.getScoreboardManager().getMainScoreboard();
		
		this.obj = this.score.registerNewObjective("oitc", "dummy", Message.SCOREBOARD_TITLE.getText());
		this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
	}
	
	public void updateScore(AlivePlayer player) {
		this.obj.getScore(player.getPlayer().getName()).setScore(player.getKills());
	}
	
	public void setScoreboardPlayers() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			this.obj.getScore(all.getName()).setScore(0);
			all.setScoreboard(this.score);
		}	
	}
	
	public void stopManager() {
		this.obj.unregister();
	}

}
