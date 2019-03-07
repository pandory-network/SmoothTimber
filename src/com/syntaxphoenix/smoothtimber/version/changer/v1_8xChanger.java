package com.syntaxphoenix.smoothtimber.version.changer;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.syntaxphoenix.smoothtimber.config.CutterConfig;
import com.syntaxphoenix.smoothtimber.utilities.Lists;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;
import com.syntaxphoenix.smoothtimber.version.manager.VersionExchanger;
import com.syntaxphoenix.smoothtimber.version.manager.WoodType;

public class v1_8xChanger implements VersionChanger {

	@Override
	public boolean hasCuttingItemInHand(Player player) {
		return CutterConfig.cutterMaterials.contains(player.getItemInHand().getType().name());
	}

	@Override
	public ItemStack removeDurabilityFromItem(ItemStack stack) {
		stack.setDurability(Integer.valueOf(stack.getDurability() - 1).shortValue());
		return stack;
	}

	@Override
	public void setItemInPlayerHand(Player player, ItemStack stack) {
		player.setItemInHand(stack);
	}

	@Override
	public boolean isWoodBlock(Block block) {
		return block.getType().name().replace("_2", "").equals("LOG");
	}

	@Override
	public void setupConfig() {
		CutterConfig.cutterMaterials.addAll(Lists.asList(Material.WOOD_AXE.name(), Material.STONE_AXE.name(), Material.IRON_AXE.name(), Material.GOLD_AXE.name(), Material.DIAMOND_AXE.name()));
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasPermissionForWood(Player p, Block b) {
		if (!CutterConfig.permissionsEnabled) {
			return true;
		}
		WoodType type = WoodType.OAK;
		Material mat = b.getType();
		int id = b.getData();
		if (mat == Material.LOG) {
			if (id == 1 || id == 5 || id == 9 || id == 13) {
				type = WoodType.SPRUCE;
			} else if (id == 2 || id == 6 || id == 10 || id == 14) {
				type = WoodType.BIRCH;
			} else if (id == 3 || id == 7 || id == 11 || id == 15) {
				type = WoodType.JUNGLE;
			}
		} else {
			if (id == 1 || id == 3 || id == 5 || id == 7 || id == 9 || id == 11 || id == 13 || id == 15) {
				type = WoodType.DARKOAK;
			} else if (id == 0 || id == 2 || id == 4 || id == 6 || id == 8 || id == 10 || id == 12 || id == 14) {
				type = WoodType.ACACIA;
			}
		}
		return VersionExchanger.checkPermission(type, p);
	}
	
	

}
