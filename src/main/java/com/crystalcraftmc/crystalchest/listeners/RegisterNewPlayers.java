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

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Places first-time players on a list, which
 * will enable them to open the new-player chests
 */
public class RegisterNewPlayers implements Listener {
	
	public RegisterNewPlayers(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**Checks players who log in if they're new -
	 * adds them to a list if they are*/
	@EventHandler
	public void areTheyNew(PlayerLoginEvent e) {
		@SuppressWarnings("deprecation") //normal player doesn't work
		//TODO test that works for actual new player
		OfflinePlayer p = Bukkit.getOfflinePlayer(e.getPlayer().getName());
		if(!p.hasPlayedBefore()) {
			CrystalChest.newPlayers.put(p.getUniqueId().toString(),
					System.currentTimeMillis());
		}
	}
	
}
