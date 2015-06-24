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
