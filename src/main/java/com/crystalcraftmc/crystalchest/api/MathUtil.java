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

/**Utility class for some math operations*/
public class MathUtil {

	/**Restricts an int number so to speak -
	 * given a high value, and a low value, a number
	 * will be made sure to be low <= num <= high
	 * @param num, int we're restricting
	 * @param low, int low end of restriction
	 * @param high, int high end of restriction
	 * @return int, restricted number
	 * @throws IllegalArgumentException if low is greater than high
	 */
	public static int restrict(int num, int low, int high) {
		if(low > high) {
			throw new IllegalArgumentException(
					"Error; low argument can not be greater than " +
					"the high argument.");
		}
		
		if(num < low) {
			return low;
		}
		else if(num > high) {
			return high;
		}
		else {
			return num;
		}
	}
	
}
