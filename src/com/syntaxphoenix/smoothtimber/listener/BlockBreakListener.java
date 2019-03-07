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
import org.bukkit.inventory.ItemStack;

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
				ItemStack tool = change.getItemInHand(p);
				Location l = e.getBlock().getLocation();
				Bukkit.getScheduler().runTaskAsynchronously(PluginUtils.m, new Runnable() {
					@Override
					public void run() {
						List<Location> woodBlocks = new ArrayList<>();
						Location bl = l;
						for (int y = -3;; y++) {
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
										for (int v = 0; v < 2; v++) {
											if (size != 0) {
												Block b = woodBlocks.remove(0).getBlock();
												if(change.hasPermissionForWood(p, b)) {
													if(change.removeDurabilityFromItem(tool) == null) {
														tim.cancel();
														break;
													}
													b.breakNaturally();
												}
												size--;
											} else {
												break;
											}
										}
										if (size == 0) {
											tim.cancel();
										}
									}
								});
							}
						}, 10, 30);
					}

					private List<Location> locateWood(Location start, List<Location> current) {
						List<Location> list = current;
						VersionChanger change = PluginUtils.changer;
						World w = start.getWorld();
						int x = start.getBlockX();
						int y = start.getBlockY();
						int z = start.getBlockZ();
						for (int cx = x - 2; cx <= x + 2; cx++) {
							for (int cz = z - 2; cz <= z + 2; cz++) {
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
