package com.syntaxphoenix.smoothtimber;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.syntaxphoenix.smoothtimber.utilities.PluginUtils;

public class SmoothTimber extends JavaPlugin {
	
	public static SmoothTimber m;
	
	public void onLoad() {
		m = this;
	}
	
	public void onEnable() {
		PluginUtils.setUp(m);
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}

}
