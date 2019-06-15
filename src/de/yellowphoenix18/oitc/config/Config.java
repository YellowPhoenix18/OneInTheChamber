package de.yellowphoenix18.oitc.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Config {
	
	public File file;
	public FileConfiguration config;
	
	public Config(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public void set(String path, Object obj) {
		this.config.set(path, obj);
		this.save();
	}
	
	public boolean contains(String path) {
		return this.config.contains(path);
	}
	
	public Object setDefault(String path, Object obj) {
		if (this.contains(path)) {
			return this.get(path);
		} else {
			this.set(path, obj);
			return obj;
		}
	}
	
	public Object get(String path) {
		return this.config.get(path);	
	}
	
	public void save()
	{
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
