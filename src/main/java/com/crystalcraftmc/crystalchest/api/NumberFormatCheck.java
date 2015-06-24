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
