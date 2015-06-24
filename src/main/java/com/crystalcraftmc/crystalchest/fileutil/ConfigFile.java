package com.crystalcraftmc.crystalchest.fileutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

/**This class is responsible for the
 * reading / creating of the config file
 */
public class ConfigFile {
	
	/**Holds path of config file*/
	public String path = "plugins/crystalchest/config.yaml";
	
	/**Holds the file of config*/
	public File config = new File(path);
	
	/**Holds config default data*/
	public Map<String, Object> defaultData = new HashMap<String, Object>();
	
	/**Holds data currently in config*/
	public static Map<String, Object> configData = new HashMap<String, Object>();
	
	/**Holds keys that do not exist yet in config*/
	public ArrayList<String> keysToAdd = new ArrayList<String>();
	
	/**Yaml object*/
	Yaml yaml = new Yaml();
	
	
	/**Creates the default data*/
	public void createDefaultData() {
		defaultData.put("How long new players can access CrystalChests (minutes)", 60);
		defaultData.put("Only ops can use noobwands", false);
	}
	
	/**Constructor; initializes config data*/
	public ConfigFile() {
		FileUtil.createFolder();
		createDefaultData();
		
		if(configIsEmpty()) {
			createConfigUsingDefaultData();
		}
		
		if(config.exists()) {
			loadExistingConfigData();
		}
		
		
		boolean wasMissingData = false;
		for(Entry<String, Object> entries : defaultData.entrySet()) {
			if(!configData.containsKey(entries.getKey())) {
				wasMissingData = true;
				configData.put(entries.getKey(), entries.getValue());
			}
		}
		
		if(wasMissingData) {
			updateConfig();
		}
	}
	
	
	/**Creates the config file using the default data*/
	public void createConfigUsingDefaultData() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
		}catch(IOException e) { e.printStackTrace(); }
		yaml.dump(defaultData, fw);
	}
	
	/**Loads data inside config*/
	@SuppressWarnings("unchecked")
	public void loadExistingConfigData() {
		try{
			configData = (Map<String, Object>)yaml.load(new FileReader(new File(path)));
		}catch(FileNotFoundException e) { e.printStackTrace(); }
	}
	
	/**Updates config to configData*/
	public void updateConfig() {
		FileWriter fw = null;
		try{
			fw = new FileWriter(path);
		}catch(IOException e) { e.printStackTrace(); }
		yaml.dump(configData, fw);
	}
	
	/**Tests if the config file is empty or non-existant
	 * @return boolean, true if config is empty or non-existant
	 */
	@SuppressWarnings("unchecked")
	public boolean configIsEmpty() {
		if(!new File(path).exists()) {
			return true;
		}
		
		Map<String, Object> testConfig = null;
		
		try{
			testConfig = (Map<String, Object>)yaml.load(
				new FileReader(new File(path)));
		}catch(FileNotFoundException e) { e.printStackTrace(); }
		//if testConfig is currently null - config is empty
		if(testConfig == null) {
			return true;
		}
		
		return false;
	}
	
}
