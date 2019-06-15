package de.yellowphoenix18.oitc.config;

import java.io.File;

public class MainConfig extends Config {
	
	private int waitingTime = 15;
	private int lobbyTime = 60;
	private int pregameTime = 10;
	private int gameTime = 600;
	private int endgameTime = 15;
	
	private int neededPlayers = 2;

	public MainConfig(File file) {
		super(file);
		this.waitingTime = (int) this.setDefault("times.waiting", this.waitingTime);
		this.lobbyTime = (int) this.setDefault("times.lobby", this.lobbyTime);
		this.pregameTime = (int) this.setDefault("times.pregame", this.pregameTime);
		this.gameTime = (int) this.setDefault("times.game", this.gameTime);
		this.endgameTime = (int) this.setDefault("times.endgame", this.endgameTime);
		this.neededPlayers = (int) this.setDefault("players.needed", this.neededPlayers);
	}

	public int getNeededPlayers() {
		return neededPlayers;
	}

	public void setNeededPlayers(int neededPlayers) {
		this.neededPlayers = neededPlayers;
		this.set("players.needed", waitingTime);
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
		this.set("times.waiting", waitingTime);
	}

	public int getLobbyTime() {
		return lobbyTime;
	}

	public void setLobbyTime(int lobbyTime) {
		this.lobbyTime = lobbyTime;
		this.set("times.lobby", lobbyTime);
	}

	public int getPregameTime() {
		return pregameTime;
	}

	public void setPregameTime(int pregameTime) {
		this.pregameTime = pregameTime;
		this.set("times.pregame", pregameTime);
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
		this.set("times.game", gameTime);
	}

	public int getEndgameTime() {
		return endgameTime;
	}

	public void setEndgameTime(int endgameTime) {
		this.endgameTime = endgameTime;
		this.set("times.endgame", endgameTime);
	}

}
