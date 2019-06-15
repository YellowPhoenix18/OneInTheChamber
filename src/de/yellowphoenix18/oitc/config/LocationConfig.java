package de.yellowphoenix18.oitc.config;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationConfig extends Config {
	
	public LocationConfig(File file) {
		super(file);
	}
	
	public void setLocation(String path, Location loc) {
		this.set(path + ".world", loc.getWorld().getName());
		this.set(path + ".x", loc.getX());
		this.set(path + ".y", loc.getY());
		this.set(path + ".z", loc.getZ());
		this.set(path + ".yaw", loc.getYaw());
		this.set(path + ".pitch", loc.getPitch());
	}
	
	public Location getLocation(String path) {
		if(this.contains(path)) {
			World world = Bukkit.getWorld((String) this.get(path + ".world"));
			double x = (double) this.get(path + ".x");
			double y = (double) this.get(path + ".y");
			double z = (double) this.get(path + ".z");
			double yaw = (double) this.get(path + ".yaw");
			double pitch = (double) this.get(path + ".pitch");
			Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
			return loc;
		}
		return null;	
	}
}
