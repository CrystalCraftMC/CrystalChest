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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.crystalcraftmc.crystalchest.gui.DragonTest;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**This class disables the acquisition of the
 * data dragon-egg in CrystalChests
 */
public class NoTakeEgg implements Listener {
	
	public NoTakeEgg(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**Disables clicks on the data dragon-egg*/
	@EventHandler (priority = EventPriority.HIGHEST)
	public void noTakeEgg(InventoryClickEvent e) {
		if(e.getInventory().getName().equals("Donate To New Players Here!")) {
			if(DragonTest.testEgg(e.getInventory().getItem(0))) {
				if(e.getRawSlot() == 0) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
