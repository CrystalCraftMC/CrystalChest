/*
 * Copyright 2015 CrystalCraftMC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    @authors - Lead:  Alex Woodward  contributors:  Ivan Frasure, Justin Flory
 */

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
