package de.yellowphoenix18.rwgtreecutter.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MainConfig {
	
	public static File f = new File("plugins/RWGTreeCutter", "config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static List<String> cutters = new ArrayList<String>();
	
	public static void load() {
		cutters.add("258");
		cutters.add("271");
		cutters.add("275");
		cutters.add("279");
		cutters.add("286");
		
		cutters = setObject("Customize.Cutter-Objects", cutters);
	}
	
	public static List<String> setObject(String path, List<String> obj) {
		if(cfg.contains(path)) {
			return cfg.getStringList(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
