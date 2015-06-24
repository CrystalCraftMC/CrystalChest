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

package com.crystalcraftmc.crystalchest.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.crystalcraftmc.crystalchest.api.CrystalChestFunctions;
import com.crystalcraftmc.crystalchest.fileutil.CrystalChestFile;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Recognizes when a crystalchest is broken*/
public class BreakCrystalChest implements Listener {
	
	public BreakCrystalChest(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**Fires when a crystalchest is broken*/
	@EventHandler (priority = EventPriority.MONITOR)
	public void brokenCrystalChest(BlockBreakEvent e) {
		if(e.getBlock().getType() != Material.CHEST) {
			return;
		}
		if(e.isCancelled()) {
			return;
		}
		
		Block broken = e.getBlock();
		
		int isCrystalChest = CrystalChestFunctions.isCrystalChest(broken.getLocation());
		
		if(isCrystalChest != -1) {
			CrystalChest.crystalChests.remove(isCrystalChest);
			CrystalChestFile.updateCrystalChestFile();
		}
	}
	
}
