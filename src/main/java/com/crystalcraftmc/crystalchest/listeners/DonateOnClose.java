package com.crystalcraftmc.crystalchest.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.crystalcraftmc.crystalchest.gui.DragonTest;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

public class DonateOnClose implements Listener {
	
	public DonateOnClose(CrystalChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(e.getInventory().getName().equals("Donate To New Players Here!")) {
			if(DragonTest.testEgg(e.getInventory().getItem(0)) &&
					(e.getPlayer() instanceof Player)) {
				
				ItemStack[] donation = e.getInventory().getContents();
				List<String> chestData = donation[0].getItemMeta().getLore();
				Chest chest = (Chest) new Location(
						Bukkit.getWorld(chestData.get(0)),
						Double.parseDouble(chestData.get(1)),
						Double.parseDouble(chestData.get(2)),
						Double.parseDouble(chestData.get(3))).getBlock().getState();
				
				ArrayList<ItemStack> toAdd = new ArrayList<ItemStack>();
				donation[0] = new ItemStack(Material.AIR); //ignore dragon egg
				for(ItemStack is : donation) {
					if(is == null) { continue; }
					if(is.getType() == Material.AIR ||
							is.getAmount() == 0) { continue; }
					toAdd.add(is.clone());
				}
				Inventory chestInv = chest.getInventory();
				ItemStack[] chestContents = chestInv.getContents();
				int amountAdded = 0;
				for(int i = 0; i < chestContents.length && amountAdded < toAdd.size(); i++) {
					if(chestContents[i] == null) {
						chestContents[i] = toAdd.get(amountAdded);
						amountAdded++;
						continue;
					}
					if(chestContents[i].getType() == Material.AIR ||
							chestContents[i].getAmount() == 0) {
						chestContents[i] = toAdd.get(amountAdded);
						amountAdded++;
						continue;
					}
				}
				chestInv.setContents(chestContents);
				
				Player p = (Player)e.getPlayer();
				
				if(amountAdded > 0) {
					p.sendMessage(CrystalChest.pluginStr+ChatColor.LIGHT_PURPLE+
							"Donation added- thank you for helping out the new players!");
				}
				if(amountAdded < toAdd.size()) {
					while(amountAdded < toAdd.size()) {
						p.getInventory().addItem(toAdd.get(amountAdded));
						amountAdded++;
					}
					p.sendMessage(CrystalChest.pluginStr+ChatColor.YELLOW +
							"The chest filled up - excess items have been " +
							"returned to your inventory.");
				}
			}
		}
	}
}
