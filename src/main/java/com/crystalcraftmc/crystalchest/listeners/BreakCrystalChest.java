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
