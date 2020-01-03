package com.syntaxphoenix.smoothtimber;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.syntaxphoenix.smoothtimber.event.AsyncPlayerChopTreeEvent;
import com.syntaxphoenix.smoothtimber.utilities.PluginUtils;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;

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

	public static boolean triggerChopEvent(Player p, Location l, VersionChanger change, ItemStack tool,
			ArrayList<Location> woodBlocks) {
		AsyncPlayerChopTreeEvent event = new AsyncPlayerChopTreeEvent(p, l, change, tool, woodBlocks);
		Bukkit.getPluginManager().callEvent(event);
		return event.isCancelled();
	}

}
