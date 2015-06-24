package com.crystalcraftmc.crystalchest.fileutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.crystalcraftmc.crystalchest.main.CrystalChest;

/**Responsible for file commands involving
 * the crystalchest file of locations/world*/
public class CrystalChestFile {
	
	/**Reads in data from file and places in
	 * CrystalChest.crystalChests ArrayList of
	 * String arrays, each containing the world / location
	 * of all the crystalchests
	 */
	public static void initializeCrystalChests() {
		FileUtil.createFolder();
		File crystalChestFile = new File("plugins/CrystalChest/locations.txt");
		if(!crystalChestFile.exists()) {
			return;
		}
		Scanner in = null;
		try{
			in = new Scanner(crystalChestFile);
		}catch(FileNotFoundException e) { e.printStackTrace(); }
		while(in.hasNext()) {
			String line = in.nextLine();
			CharSequence actualData = ";";
			if(line.contains(actualData)) {
				CrystalChest.crystalChests.add(line.split(";"));
			}
		}
		in.close();
	}
	
	/**Updates the CrystalChest file to the current
	 * CrystalChest.crystalChests ArrayList data
	 */
	public static void updateCrystalChestFile() {
		FileUtil.createFolder();
		File crystalChestFile = new File("plugins/CrystalChest/locations.txt");
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(crystalChestFile);
		}catch(FileNotFoundException e) { e.printStackTrace(); }
		for(String[] s : CrystalChest.crystalChests) {
			pw.println(s[0]+";"+s[1]+";"+s[2]+";"+s[3]);
		}
		pw.flush();
		pw.close();
	}
	
}
