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

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.crystalcraftmc.crystalchest.api.CrystalChestFunctions;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Recognizes when a crystalchest is placed*/
public class PlaceCrystalChest implements Listener {
	
	public PlaceCrystalChest(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**Recognizes placement of crystalchests*/
	@EventHandler (priority = EventPriority.MONITOR)
	public void placeChest(BlockPlaceEvent e) {
		if(e.getBlock().getType() != Material.CHEST) {
			return;
		}
		ItemStack placed = e.getItemInHand();
		ItemMeta im = placed.getItemMeta();
		
		if(!im.hasLore()) {
			return;
		}
		
		List<String> lore = im.getLore();
		if(lore.size() < 1) {
			return;
		}
		
		if(lore.get(0).equals("Official CrystalChest Of MidnightSun")) {
			if(!e.isCancelled()) {
				CrystalChestFunctions.registerCrystalChest(e.getBlock().getLocation());
			}
		}
	}
	
}
