package com.syntaxphoenix.smoothtimber.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.syntaxphoenix.smoothtimber.utilities.PluginUtils;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;

public class BlockBreakListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}
		VersionChanger change = PluginUtils.changer;
		if (change.isWoodBlock(e.getBlock())) {
			Player p = e.getPlayer();
			if (change.hasCuttingItemInHand(p)) {
				e.setCancelled(true);
				Location l = e.getBlock().getLocation();
				Bukkit.getScheduler().runTaskAsynchronously(PluginUtils.m, new Runnable() {
					@Override
					public void run() {
						List<Location> woodBlocks = new ArrayList<>();
						Location bl = l;
						for (int y = 0;; y++) {
							List<Location> located = locateWood(
									new Location(bl.getWorld(), bl.getBlockX(), bl.getBlockY() + y, bl.getBlockZ()),
									new ArrayList<>());
							if (located.isEmpty()) {
								break;
							}
							woodBlocks.addAll(located);
						}
						Bukkit.getScheduler().runTask(PluginUtils.m, new Runnable() {
							@Override
							public void run() {
								for (Location l : woodBlocks) {
									l.getBlock().breakNaturally();
								}
							}
						});
						Timer tim = new Timer();
						tim.scheduleAtFixedRate(new TimerTask() {
							@Override
							public void run() {
								Bukkit.getScheduler().runTask(PluginUtils.m, new Runnable() {
									@Override
									public void run() {
										int size = woodBlocks.size();
										int v = 0;
										for (; v < 5; v++) {
											if (size != 0) {
												Block b = woodBlocks.remove(0).getBlock();
												if(change.hasPermissionForWood(p, b)) {
													b.breakNaturally();
												}
												size--;
											} else {
												break;
											}
										}
										if (v != 5) {
											tim.cancel();
										}
									}
								});
							}
						}, 0, 50);
					}

					private List<Location> locateWood(Location start, List<Location> current) {
						List<Location> list = current;
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
									if (list.contains(l)) {
										continue;
									}
									list.add(l);
									if (checkLoc) {
										list.addAll(locateWood(l, list));
									}
								}
							}
						}
						return list;
					}
				});
			}
		}
	}

}
