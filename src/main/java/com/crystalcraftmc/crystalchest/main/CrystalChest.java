package com.crystalcraftmc.crystalchest.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.crystalcraftmc.crystalchest.commands.CrystalChestCommand;
import com.crystalcraftmc.crystalchest.commands.NoobWandCommand;
import com.crystalcraftmc.crystalchest.fileutil.ConfigFile;
import com.crystalcraftmc.crystalchest.fileutil.CrystalChestFile;
import com.crystalcraftmc.crystalchest.listeners.BreakCrystalChest;
import com.crystalcraftmc.crystalchest.listeners.OpenCrystalChest;
import com.crystalcraftmc.crystalchest.listeners.PlaceCrystalChest;
import com.crystalcraftmc.crystalchest.listeners.RegisterNewPlayers;
import com.crystalcraftmc.crystalchest.newplayer.NewPlayerUpdater;

public class CrystalChest extends JavaPlugin {
	
	/**Holds the plugin name for display*/
	public static String pluginStr = ChatColor.DARK_GRAY +
			"[" + ChatColor.GREEN + "CrystalChest" + ChatColor.DARK_GRAY +"]: ";
	
	/**Holds message for invalid permission*/
	public static String noPerms = CrystalChest.pluginStr+ChatColor.GOLD +
			"You do not have permission to perform this command.";
	
	
	/**Holds locations of all crystalchests in
	 * format world, x, y, z*/
	public static ArrayList<String[]> crystalChests = new ArrayList<String[]>();
	
	/**Player update class object*/
	public NewPlayerUpdater npu;
	
	/**Holds list of new players*/
	public static Map<String, Long> newPlayers = new HashMap<String, Long>();
	
	/**Holds config file object*/
	public ConfigFile config;
	
	/**Instance of this plugin*/
	public static CrystalChest plugin;
	
	public void onEnable() {
		plugin = this;
		CrystalChestFile.initializeCrystalChests();
		config = new ConfigFile();
		
		getCommand("crystalchest").setExecutor(new CrystalChestCommand());
		getCommand("noobwand").setExecutor(new NoobWandCommand());
		new PlaceCrystalChest(this);
		new BreakCrystalChest(this);
		new OpenCrystalChest(this);
		new RegisterNewPlayers(this);
		
		npu = new NewPlayerUpdater();
	}
	
	public void onDisable() {
		npu.onDisable();
	}
	
	
}
