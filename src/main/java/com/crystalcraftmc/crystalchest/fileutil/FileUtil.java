package com.crystalcraftmc.crystalchest.fileutil;

import java.io.File;

/**File utility methods*/
public class FileUtil {
	
	/**Creates the holding folder if it doesn't already exist*/
	public static void createFolder() {
		if(!new File("plugins/CrystalChest").exists()) {
			new File("plugins/CrystalChest").mkdir();
		}
	}
	
}
