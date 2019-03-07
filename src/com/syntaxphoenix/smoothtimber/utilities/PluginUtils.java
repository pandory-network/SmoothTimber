package com.syntaxphoenix.smoothtimber.utilities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.syntaxphoenix.smoothtimber.SmoothTimber;
import com.syntaxphoenix.smoothtimber.config.CutterConfig;
import com.syntaxphoenix.smoothtimber.listener.BlockBreakListener;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;
import com.syntaxphoenix.smoothtimber.version.manager.VersionExchanger;

import net.md_5.bungee.api.ChatColor;

public class PluginUtils {

	public static PluginUtils utils;
	public static SmoothTimber m;
	public static VersionChanger changer;

	public static void setUp(SmoothTimber main) {
		m = main;
		utils = new PluginUtils();
	}

	public PluginUtils() {
		changer = VersionExchanger.getVersionChanger(VersionExchanger.getMinecraftVersion());
		if (changer != null) {
			CutterConfig.load();
			registerListener();
		}
	}

	/*
	 * 
	 */

	private void registerListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockBreakListener(), SmoothTimber.m);
	}

	/*
	 * Console Util
	 */

	public static void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

}
