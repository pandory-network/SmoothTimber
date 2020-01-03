/**
 * 
 * @author StevenLPHD
 * 
 */
package com.syntaxphoenix.smoothtimber.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;

public class Locator {
	protected static boolean blockylog = false;

	public static List<Location> locateWood(Location start, List<Location> current) {
		if (blockylog) {
			try {
				return locateBlocky(start, current);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return locateOnly(start, current);
	}

	private static List<Location> locateOnly(Location start, List<Location> current){
		List<Location> all = current;
		List<Location> list = new ArrayList<>();
		VersionChanger change = PluginUtils.changer;
		World w = start.getWorld();
		int x = start.getBlockX();
		int y = start.getBlockY();
		int z = start.getBlockZ();
		
		
		for (int cx = x - 3; cx <= x + 3; cx++) {
			for (int cz = z - 3; cz <= z + 3; cz++) {
				boolean checkLoc = true;
				if (cx == x && cz == z) {
					checkLoc = false;
				}
				Location l = new Location(w, cx, y, cz);
				if (change.isWoodBlock(l.getBlock())) {
					if (all.contains(l)) {
						continue;
					}
					all.add(l);
					list.add(l);
					if (checkLoc) {
						list.addAll(locateOnly(l, all));
					}
				}
			}
		}
		return list;
	}

	private static List<Location> locateBlocky(Location start, List<Location> current) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Location> all = current;
		List<Location> list = new ArrayList<>();
		VersionChanger change = PluginUtils.changer;
		World w = start.getWorld();
		int x = start.getBlockX();
		int y = start.getBlockY();
		int z = start.getBlockZ();

		/*
		 * 
		 * Reflections
		 * 
		 */

		Class<?> blockyWorldClass = Reflector.getBLClass("log.BlockyWorld");
		Class<?> blockyChunkClass = Reflector.getBLClass("log.BlockyChunk");

		Method get = blockyWorldClass.getMethod("get", World.class);
		Method getChunk = blockyWorldClass.getDeclaredMethod("getChunk", int.class, int.class);
		Method containsChunk = blockyWorldClass.getDeclaredMethod("containsChunk", int.class, int.class);

		Method containsBlock = blockyChunkClass.getDeclaredMethod("containsBlock", int.class, int.class, int.class);

		/*
		 * 
		 * Executions
		 * 
		 */

		Object bw = get.invoke(blockyWorldClass, w);
		for (int cx = x - 3; cx <= x + 3; cx++) {
			for (int cz = z - 3; cz <= z + 3; cz++) {
				boolean checkLoc = true;
				if (cx == x && cz == z) {
					checkLoc = false;
				}
				Location l = new Location(w, cx, y, cz);
				if (change.isWoodBlock(l.getBlock())) {
					Chunk c = l.getChunk();
					if ((boolean) containsChunk.invoke(bw, c.getX(), c.getZ())) {
						Object bc = getChunk.invoke(bw, c.getX(), c.getZ());
						if ((boolean) containsBlock.invoke(bc, cx, y, cz)) {
							continue;
						}
					}
					if (all.contains(l)) {
						continue;
					}
					all.add(l);
					list.add(l);
					if (checkLoc) {
						list.addAll(locateBlocky(l, all));
					}
				}
			}
		}
		return list;
	}

}
