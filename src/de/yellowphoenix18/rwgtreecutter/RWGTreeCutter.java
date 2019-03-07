package de.yellowphoenix18.rwgtreecutter;

import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.rwgtreecutter.utils.PluginUtils;

public class RWGTreeCutter extends JavaPlugin {
	
	public static RWGTreeCutter m;
	
	public void onEnable() {
		m = this;
		PluginUtils.setUp();
	}
	
	public void onDisable() {
		
	}

}
