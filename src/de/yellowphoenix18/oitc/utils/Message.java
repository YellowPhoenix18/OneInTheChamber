package de.yellowphoenix18.oitc.utils;

import java.util.HashMap;

public enum Message {
	
	PREFIX("prefix", "&8[&6OITC&8] &7"),
	NO_PERMISSION("error.permission.denied", "&cYou have no permission to do this"),
	TIMES_WAITING("times.waiting", "Waiting for more players&8(&e%Current_Players%&7/&6%Needed_Players%&8)"),
	TIMES_LOBBY("times.lobby", "The round starts in &6%Time% &7seconds"),
	TIMES_PREGAME("times.pregame", "The game starts in &6%Time% &7seconds"),
	TIMES_GAME_SECONDS("times.game.seconds", "The game ends in &6%Time% &7seconds"),
	TIMES_GAME_MINUTES("times.game.minutes", "The game ends in &6%Time% &7minutes"),
	TIMES_ENDGAME("times.endgame", "The server stop in &6%Time% &7seconds"),
	END_KICK("kick.end", "&cThe server restarts now"),
	PLAYER_DEATH("events.death.no-killer", "&e%Player% &7run into death"),
	PLAYER_DEATH_KILLER("events.death.killer", "&e%Player% &7was slain by &e%Killer%"),
	PLAYER_QUIT("events.quit", "&e%Player% &7has left the game"),
	PLAYER_JOIN("events.join", "&e%Player% &7has joined the game"),
	PLAYER_WIN("events.win", "&e%Player% &7has won the game"),
	SCOREBOARD_TITLE("scoreboard.title", "&7» &6&lOITC &7«"),
	GAME_STARTED("events.login", "&cThe game has already started"),
	COMMAND_UNKNOWN("error.command.unknown", "&cThis command is unknown. Pleas use /oitc help");

    private String text;
    private String configName;

    Message(String configName, String text) {
    	this.configName = configName;
        this.text = text;
    }

    public String getConfigName() {
        return this.configName;
    }
    
    public void setText(String text) {
    	this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getText(HashMap<String, String> replacements) {
    	String replText = this.text;
    	for(String key : replacements.keySet()) {
    		replText = replText.replace(key, replacements.get(key));
    	}
    	
        return replText;
    }

    public static Message fromConfigName(String configName) {
        for (Message b : Message.values()) {
            if (b.configName.equalsIgnoreCase(configName)) {
                return b;
            }
        }
        return null;
    }

}
