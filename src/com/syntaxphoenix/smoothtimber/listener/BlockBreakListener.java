package com.syntaxphoenix.smoothtimber.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.syntaxphoenix.smoothtimber.SmoothTimber;
import com.syntaxphoenix.smoothtimber.config.CutterConfig;
import com.syntaxphoenix.smoothtimber.utilities.Locator;
import com.syntaxphoenix.smoothtimber.utilities.PluginUtils;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;

public class BlockBreakListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		if (CutterConfig.onSneak) {
			if (!p.isSneaking()) {
				return;
			}
		}
		VersionChanger change = PluginUtils.changer;
		if (change.isWoodBlock(e.getBlock())) {
			if (change.hasCuttingItemInHand(p)) {
				e.setCancelled(true);
				ItemStack tool = change.getItemInHand(p);
				Location l = e.getBlock().getLocation();
				Bukkit.getScheduler().runTaskAsynchronously(PluginUtils.m, new Runnable() {
					@Override
					public void run() {
						ArrayList<Location> woodBlocks = new ArrayList<>();
						Location bl = l;
						for (int y = 0;; y++) {
							List<Location> located = Locator.locateWood(
									new Location(bl.getWorld(), bl.getBlockX(), bl.getBlockY() + y, bl.getBlockZ()),
									woodBlocks);
							if (located.isEmpty()) {
								break;
							}
							woodBlocks.addAll(located);
						}
						if(SmoothTimber.triggerChopEvent(p, l, change, tool, woodBlocks)) {
							return;
						}
						Bukkit.getScheduler().runTask(PluginUtils.m, new Runnable() {
							@Override
							public void run() {
								for (int v = 0; v < woodBlocks.size(); v++) {
									Block b = woodBlocks.get(v).getBlock();
									if (change.hasPermissionForWood(p, b)) {
										if (change.removeDurabilityFromItem(tool) == null) {
											break;
										}
										b.breakNaturally();
									}
									
								}
							}
						});
					}
				});
			}
		}
	}

}
