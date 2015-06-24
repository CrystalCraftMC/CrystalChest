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

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**Tests to validate inventory*/
public class DragonTest {
	
	/**Tests if an itemstack is the signature dragonegg or not
	 * @param is, ItemStack of the item to test
	 * @return boolean, true if it's the signature itemstack for crystalchest
	 */
	public static boolean testEgg(ItemStack is) {
		if(is == null) { return false; }
		if(is.getType() == Material.AIR) { return false; }
		if(is.getAmount() == 0) { return false; }
		if(is.getType() != Material.DRAGON_EGG) { return false; }
		ItemMeta im = is.getItemMeta();
		if(im.getDisplayName() != null) {
			if(im.getDisplayName().equals(ChatColor.DARK_AQUA + "CrystalChest Data-Block")) {
				if(im.hasLore()) {
					List<String> lore = im.getLore();
					if(lore.size() == 4) {
						for(int i = 1; i < lore.size(); i++) {
							try{
								Double.parseDouble(lore.get(i));
							}catch(NumberFormatException e) { return false; }
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
