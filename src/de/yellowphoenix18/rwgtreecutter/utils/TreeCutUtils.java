package de.yellowphoenix18.rwgtreecutter.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;

public class TreeCutUtils {
	
	public static Random rnd = new Random();
	
	@SuppressWarnings("deprecation")
	public static void cutTree(Location loc) {
		
		int subid = loc.getBlock().getData();
		if(loc.getBlock().getTypeId() == 17) {
			if(subid == 4 || subid == 8 || subid == 12) {
				subid = 0;
			} else if(subid == 5 || subid == 9 || subid == 13) {
				subid = 1;
			} else if(subid == 6 || subid == 10 || subid == 14) {
				subid = 2;
			} else if(subid == 7 || subid == 11 || subid == 15) {
				subid = 3;
			}
		}
		loc.getWorld().dropItemNaturally(loc, ItemUtils.ItemStackCreatorID(null, null, null, loc.getBlock().getTypeId(), subid, 1));
		loc.getBlock().setType(Material.AIR);
		cutLeaves(loc);
		int startX = loc.getBlockX()-1;
		int startY = loc.getBlockY();
		int startZ = loc.getBlockZ()-1;
		for(int x = 0; x <= 3; x++) {
			for(int y = 0; y <= 2; y++) {
				for(int z = 0; z <= 3; z++) {
					Location nloc = new Location(loc.getWorld(), startX+x, startY+y, startZ+z);
					if(nloc.getBlock().getTypeId() == 17 || nloc.getBlock().getTypeId() == 162  || nloc.getBlock().getTypeId() == 85) {
						cutTree(nloc);
					}
				}
			}
		}		
	}
	
	@SuppressWarnings("deprecation")
	public static void cutLeaves(Location loc) {
		int chance = rnd.nextInt(24);
		if(chance <= 4) {
			loc.getWorld().dropItemNaturally(loc, ItemUtils.ItemStackCreator(null, null, null, Material.APPLE, 1));
		} else if(chance == 5) {
			int subid = loc.getBlock().getData();
			if(subid == 4 || subid == 8 || subid == 12) {
				subid = 0;
			} else if(subid == 5 || subid == 9 || subid == 13) {
				subid = 1;
			} else if(subid == 6 || subid == 10 || subid == 14) {
				subid = 2;
			} else if(subid == 7 || subid == 11 || subid == 15) {
				subid = 3;
			}
			loc.getWorld().dropItemNaturally(loc, ItemUtils.ItemStackCreatorID(null, null, null, 6, subid, 1));
		}
		
		int startX = loc.getBlockX()-2;
		int startY = loc.getBlockY()-2;
		int startZ = loc.getBlockZ()-2;
		for(int x = 0; x <= 5; x++) {
			for(int y = 0; y <= 5; y++) {
				for(int z = 0; z <= 5; z++) {
					Location nloc = new Location(loc.getWorld(), startX+x, startY+y, startZ+z);
					if(nloc.getBlock().getTypeId() == 18 || nloc.getBlock().getTypeId() == 161) {
						nloc.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}

}
