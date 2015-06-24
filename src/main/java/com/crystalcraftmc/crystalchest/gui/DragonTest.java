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
