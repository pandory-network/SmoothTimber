package de.yellowphoenix18.rwgtreecutter.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.yellowphoenix18.rwgtreecutter.RWGTreeCutter;
import de.yellowphoenix18.rwgtreecutter.config.MainConfig;
import de.yellowphoenix18.rwgtreecutter.listener.BlockListener;

public class PluginUtils {
	
	public static void setUp() {
		loadListener();
		MainConfig.load();
	}
	
	public static void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockListener(), RWGTreeCutter.m);
	}

}
