package de.yellowphoenix18.rwgtreecutter.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Dye;

public class ItemUtils {
	
	public static ItemStack ItemStackCreator(String name, String lore1, String lore2, Material m, int a) {		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		ItemStack i = new ItemStack(m);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(name);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;
	}
	
	public static ItemStack ItemStackCreatorList(String name,List<String> lore, Material m, int a) {		
		ItemStack i = new ItemStack(m);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(name);
		imeta.setLore(lore);
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;
	}
	
	public static ItemStack ItemStackCreatorEnch(String name, String lore1, String lore2, Material m, int a, Enchantment e, int e_num) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		ItemStack i = new ItemStack(m);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(name);
		imeta.addEnchant(e, e_num, true);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack SkullCreator(String name, String owner, String lore1, String lore2, int a) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		
		ItemStack i = new ItemStack(397,1, (short) 3);
		SkullMeta imeta = (SkullMeta)i.getItemMeta();
		imeta.setOwner(owner);
		imeta.setDisplayName(name);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack ItemStackCreatorID(String name, String lore1, String lore2, int typeid, int data, int a) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		ItemStack i = new ItemStack(typeid, 1, (short) data);
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(name);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;
	}
	
	public static ItemStack LeatherCreator(String name, String lore1, String lore2, Material m, int a, Color c) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		ItemStack i = new ItemStack(m);
		LeatherArmorMeta imeta = (LeatherArmorMeta) i.getItemMeta();
		imeta.setDisplayName(name);
		imeta.setColor(c);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;		
	}
	
	public static ItemStack LeatherCreatorEnch(String name, String lore1, String lore2, Material m, int a, Color c, Enchantment e, int e_num) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		ItemStack i = new ItemStack(m);
		LeatherArmorMeta imeta = (LeatherArmorMeta) i.getItemMeta();
		imeta.addEnchant(e, e_num, true);
		imeta.setDisplayName(name);
		imeta.setColor(c);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;		
	}
	
	public static ItemStack DyeCreator(String name, String lore1, String lore2, int a, DyeColor c) {
		
		List<String> list = new ArrayList<String>();
		if(lore1 != null) {
			list.add(lore1);
		}
		if(lore2 != null) {
			list.add(lore2);
		}
		Dye d = new Dye();
		d.setColor(c);
		ItemStack i = d.toItemStack();
		ItemMeta imeta = i.getItemMeta();
		imeta.setDisplayName(name);
		if(!(lore1 == null && lore2 == null)) {
		imeta.setLore(list);
		}
		i.setItemMeta(imeta);
		i.setAmount(a);
		return i;		
	}

}
