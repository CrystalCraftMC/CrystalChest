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
