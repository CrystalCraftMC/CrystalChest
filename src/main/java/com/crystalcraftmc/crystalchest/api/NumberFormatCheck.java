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

package com.crystalcraftmc.crystalchest.api;

/**This class has several utility methods
 * where number format checks need to be made
 */
public class NumberFormatCheck {
	
	/**Checks that a number is an int
	 * @param num, String we're checking
	 * @return boolean, true if the num is an int
	 */
	public static boolean isInt(String num) {
		try{
			Integer.parseInt(num);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
}
