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

package com.crystalcraftmc.crystalchest.api;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.crystalcraftmc.crystalchest.fileutil.CrystalChestFile;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Class to deal with some functions involving
 * crystalchests
 */
public class CrystalChestFunctions {
	
	
	/**Gives CrystalChests to a player
	 * @param p, Player we're giving to
	 * @param amount, int amount of CrystalChests to give
	 */
	public static void giveCrystalChest(Player p, int amount) {
		ItemStack crystalChest = new ItemStack(Material.CHEST, amount);
		ItemMeta im = crystalChest.getItemMeta();
		im.setLore(Arrays.asList(new String[]{
				"Official CrystalChest Of MidnightSun"}));
		crystalChest.setItemMeta(im);
		
		p.getInventory().addItem(crystalChest);
	}
	
	/**Registers a new crystalchest location
	 * @param loc, Location of the new crystalchest
	 */
	public static void registerCrystalChest(Location loc) {
		CrystalChest.crystalChests.add(new String[]{loc.getWorld().getName(),
				String.format("%.2f", loc.getX()), 
				String.format("%.2f", loc.getY()), 
				String.format("%.2f", loc.getZ())} );
		CrystalChestFile.updateCrystalChestFile();
	}
	
	
	/**Checks whether a location is in fact a crystalchest or not
	 * @param loc, Location we're testing
	 * @return int, index of CrystalChest returns -1 if non-existant
	 */
	public static int isCrystalChest(Location loc) {
		int index = -1;
		int iteration = 0;
		for(String[] s : CrystalChest.crystalChests) {
			if(loc.getWorld().getName().equals(s[0])) {
				Block check = new Location(loc.getWorld(),
						Double.parseDouble(s[1]), Double.parseDouble(s[2]),
						Double.parseDouble(s[3])).getBlock();
				if(check.getLocation().getBlockX() == 
						loc.getBlockX() &&
						check.getLocation().getBlockY() == 
								loc.getBlockY() &&
						check.getLocation().getBlockZ() == 
								loc.getBlockZ()) {
					index = iteration;
					break;
				}
			}
			iteration++;
		}
		
		return index;
	}
	
}
