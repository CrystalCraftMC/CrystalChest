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

package com.crystalcraftmc.crystalchest.newplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.Timer;

import com.crystalcraftmc.crystalchest.fileutil.ConfigFile;
import com.crystalcraftmc.crystalchest.main.CrystalChest;

public class NewPlayerUpdater {
	
	/**Timer for recurring code*/
	public Timer tim;
	
	public NewPlayerUpdater() {
		tim = new Timer(60000, new Update());
		tim.start();
	}
	
	/**Called on plugin's disable - to ensure the timer stops*/
	public void onDisable() {
		if(tim.isRunning()) {
			tim.stop();
		}
	}
	
	/**repeating code to update new player list*/
	private class Update implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int configDuration = (Integer)ConfigFile.configData.get(
					"How long new players can access CrystalChests (minutes)");
			ArrayList<String> playersToRemove = new ArrayList<String>();
			for(Entry<String, Long> set : CrystalChest.newPlayers.entrySet()) {
				if(set.getValue()+(configDuration*60*1000) <
						System.currentTimeMillis()) {
					playersToRemove.add(set.getKey());
				}
			}
			for(String s : playersToRemove) {
				CrystalChest.newPlayers.remove(s);
			}
		}
	}
	
}
