package com.crystalcraftmc.crystalchest.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.crystalcraftmc.crystalchest.api.CrystalChestFunctions;
import com.crystalcraftmc.crystalchest.api.MathUtil;
import com.crystalcraftmc.crystalchest.api.NumberFormatCheck;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**This command spawns in crystalchests*/
public class CrystalChestCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		if(!p.isOp()) {
			p.sendMessage(CrystalChest.noPerms);
			return true;
		}
		
		int amount;
		
		switch(args.length) {
		case 0:
			amount = 1;
			break;
		case 1:
			if(!NumberFormatCheck.isInt(args[0])) {
				p.sendMessage(CrystalChest.pluginStr+ChatColor.DARK_AQUA+
						"Error; your first argument was not a valid int value.");
				return false;
			}
			amount = MathUtil.restrict(Integer.parseInt(args[0]), 1, 64);
			break;
		default:
			return false;
		}
		
		CrystalChestFunctions.giveCrystalChest(p, amount);
		
		String noun = amount == 1 ? "CrystalChest has" : "CrystalChests have";
		p.sendMessage(CrystalChest.pluginStr+ChatColor.DARK_AQUA +
				String.valueOf(amount)+" "+noun+" been added to " +
				"your inventory.");
		
		return true;
	}
	
}
