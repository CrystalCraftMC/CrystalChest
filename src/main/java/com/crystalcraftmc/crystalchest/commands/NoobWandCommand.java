package com.crystalcraftmc.crystalchest.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.crystalcraftmc.crystalchest.main.CrystalChest;

public class NoobWandCommand implements CommandExecutor {
	
	/**The NoobWand*/
	private ItemStack noobWand = new ItemStack(Material.STICK, 1);
	
	
	public NoobWandCommand() {
		ItemMeta im = noobWand.getItemMeta();
		im.setDisplayName(ChatColor.GREEN+"Noob Wand");
		im.setLore(Arrays.asList(new String[]{ "This wand gives you",
				"the exclusive noob-power", "of opening "+ChatColor.AQUA+
				"CrystalChests" }));
		noobWand.setItemMeta(im);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) { return false; }
		Player p = (Player) sender;
		if(!p.isOp()) {
			p.sendMessage(CrystalChest.noPerms);
			return true;
		}
		p.getInventory().addItem(noobWand);
		p.sendMessage(CrystalChest.pluginStr+ChatColor.DARK_AQUA+"A noob-wand has been " +
				"added to your inventory.");
		return true;
	}
	
}
