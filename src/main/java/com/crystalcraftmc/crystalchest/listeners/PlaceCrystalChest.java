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
