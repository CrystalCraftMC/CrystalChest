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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.crystalcraftmc.crystalchest.api.CrystalChestFunctions;
import com.crystalcraftmc.crystalchest.fileutil.ConfigFile;
import com.crystalcraftmc.crystalchest.gui.DonateInventory;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Responds to when a crystalchest is opened*/
public class OpenCrystalChest implements Listener {
	
	public OpenCrystalChest(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**Responds to clicks on CrystalChests - also 
	 * overrides other plugins to have final say
	 * in whether chest opens or not
	 */
	@EventHandler (priority = EventPriority.HIGHEST)
	public void clickOpen(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		if(e.getClickedBlock().getType() != Material.CHEST) {
			return;
		}
		
		int crystalChestIndex = CrystalChestFunctions.isCrystalChest(
				e.getClickedBlock().getLocation());
		
		if(crystalChestIndex == -1) { //then it is not a crystal chest
			return;
		}
		
		Player p = e.getPlayer();
		boolean isNewPlayer = CrystalChest.newPlayers.containsKey(p.getUniqueId().toString());
		boolean isWand = false;
		ItemStack inHand = p.getItemInHand();
		if(inHand != null) {
			if(inHand.getType() != Material.AIR && inHand.getAmount() != 0) {
				if(inHand.getType() == Material.STICK) {
					ItemMeta im = inHand.getItemMeta();
					if(im.hasDisplayName() && im.hasLore()) {
						if(im.getDisplayName().equals(ChatColor.GREEN+"Noob Wand")) {
							List<String> lore = im.getLore();
							if(lore.size() == 3) {
								if(lore.get(0).equals("This wand gives you") &&
										lore.get(1).equals("the exclusive noob-power") &&
										lore.get(2).equals("of opening "+ChatColor.AQUA+
												"CrystalChests")) {
									isWand = true;
								}
							}
						}
					}
				}
			}
		}
		if(isNewPlayer || (isWand && p.isOp()) ||
				(isWand && !(Boolean)ConfigFile.configData.get(
						"Only ops can use noobwands"))) {
			e.setCancelled(false);
		}
		else {
			e.setCancelled(true);
			new DonateInventory(CrystalChest.crystalChests.get(crystalChestIndex), p);
		}
		
	}
	
}
