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

package com.crystalcraftmc.crystalchest.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DonateInventory {
	
	/**Inventory people donate to new players with*/
	private Inventory di;
	
	
	/**Constructor - opens inventory for a specific player
	 * @param chest, String[] of the selected chest
	 * @param p, Player who is using inventory
	 */
	public DonateInventory(String[] chest, Player p) {
		
		di = Bukkit.createInventory(p, 54, "Donate To New Players Here!");
		ItemStack[] contents = new ItemStack[54];
		for(int i = 0; i < contents.length; i++) {
			switch(i) {
			case 0:
				ItemStack data = new ItemStack(Material.DRAGON_EGG, 1);
				ItemMeta dataMeta = data.getItemMeta();
				dataMeta.setDisplayName(ChatColor.DARK_AQUA + "CrystalChest Data-Block");
				dataMeta.setLore(Arrays.asList(new String[]{
						chest[0], chest[1], chest[2], chest[3] }));
				data.setItemMeta(dataMeta);
				contents[i] = data;
				break;
			default:
				contents[i] = new ItemStack(Material.AIR);
				break;
			}
		}
		di.setContents(contents);
		p.openInventory(di);
	}
	
}
