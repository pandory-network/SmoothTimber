package de.yellowphoenix18.rwgtreecutter.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.yellowphoenix18.rwgtreecutter.config.MainConfig;
import de.yellowphoenix18.rwgtreecutter.utils.TreeCutUtils;

public class BlockListener implements Listener {

	@SuppressWarnings("deprecation")	
	@EventHandler
	public void on(BlockBreakEvent e) {
		Player p = e.getPlayer();
		int id = e.getBlock().getTypeId();
		int sub_id = e.getBlock().getData();
		if(id == 17 && sub_id == 0 || id == 17 && sub_id == 4 || id == 17 && sub_id == 8 || id == 17 && sub_id == 12) {
			if(p.hasPermission("rwgtreecutter.*") || p.hasPermission("rwgtreecutter.oak")) {
				if(MainConfig.cutters.contains(p.getItemInHand().getTypeId() + "") || MainConfig.cutters.contains(p.getItemInHand().getTypeId() + ":" + p.getItemInHand().getData().getData())) {
					TreeCutUtils.cutTree(e.getBlock().getLocation());
				}
			}
		} else if(id == 17 && sub_id == 1 || id == 17 && sub_id == 5 || id == 17 && sub_id == 9 || id == 17 && sub_id == 13) {
			if(p.hasPermission("rwgtreecutter.*") || p.hasPermission("rwgtreecutter.spruce")) {
				if(MainConfig.cutters.contains(p.getItemInHand().getTypeId() + "") || MainConfig.cutters.contains(p.getItemInHand().getTypeId() + ":" + p.getItemInHand().getData().getData())) {
					TreeCutUtils.cutTree(e.getBlock().getLocation());
				}
			}
		} else if(id == 17 && sub_id == 2 || id == 17 && sub_id == 6 || id == 17 && sub_id == 10 || id == 17 && sub_id == 14) {
			if(p.hasPermission("rwgtreecutter.*") || p.hasPermission("rwgtreecutter.birch")) {
				if(MainConfig.cutters.contains(p.getItemInHand().getTypeId() + "") || MainConfig.cutters.contains(p.getItemInHand().getTypeId() + ":" + p.getItemInHand().getData().getData())) {
					TreeCutUtils.cutTree(e.getBlock().getLocation());
				}
			}
		} else if(id == 17 && sub_id == 3 || id == 17 && sub_id == 7 || id == 17 && sub_id == 11 || id == 17 && sub_id == 15) {
			if(p.hasPermission("rwgtreecutter.*") || p.hasPermission("rwgtreecutter.jungle")) {
				if(MainConfig.cutters.contains(p.getItemInHand().getTypeId() + "") || MainConfig.cutters.contains(p.getItemInHand().getTypeId() + ":" + p.getItemInHand().getData().getData())) {
					TreeCutUtils.cutTree(e.getBlock().getLocation());
				}
			}
		}
	}

}
