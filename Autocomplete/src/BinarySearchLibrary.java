import java.util.*;

/*
 * Conner Lewis
 */
public class BinarySearchLibrary {
	
	public static <T>
	    int firstIndexSlow(List<T> list, 
	    		           T target, Comparator<T> comp) {
		int index = Collections.binarySearch(list, target,comp);
		
		if (index < 0) return index;
		
		while (0 <= index && comp.compare(list.get(index),target) == 0) {
			index -= 1;
		}
		return index+1;
	}
	
	/**
	 * Uses binary search to find the index of the first object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index < i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	
	public static <T>
    	int firstIndex(List<T> list, 
	               	T target, Comparator<T> comp) {
		//Sets an invariant over the list
		int low = -1;
		int high = list.size()-1;
		while(low+1 != high) {
			//Compares mid to target
			int mid = (high+low)/2;
			int c = comp.compare(target, list.get(mid));
			//Adjusts mid to find earliest example, adjusting high and low to mid accordingly
			if(c <= 0) {
				high = mid;
			} else if(c > 0) {
				low = mid;
			}
		}
		//If the value at (low, high] is the target, returns high
		if(comp.compare(target, list.get(high)) == 0) {
			return high;
		}
		
		return -1;
	}

	/**
	 * Uses binary search to find the index of the last object in parameter
	 * list -- the first object o such that comp.compare(o,target) == 0.
	 * 
	 * This method should not call comparator.compare() more than 1+log n times
	 * @param list is the list of objects being searched
	 * @param target is the object being searched for
	 * @param comp is how comparisons are made
	 * @return index i such that comp.compare(list.get(i),target) == 0
	 * and there is no index > i such that this is true. Return -1
	 * if there is no such object in list.
	 */
	public static <T>
	int lastIndex(List<T> list, 
               	  T target, Comparator<T> comp) {
		//Sets invariant [low, high)
		int low = 0;
		int high = list.size();
		
		while(low != high-1) {
			//Sets mid and compares to target
			int mid = (high+low)/2;
			int c = comp.compare(target, list.get(mid));
			//Adjusts to find last index by shifting invariant
			if(c >= 0) {
				low = mid;
			} else if(c < 0) {
				high = mid;
			}
		}
		//If the value at [high, low) is the target, returns low

		if(comp.compare(target, list.get(low)) == 0) {
			return low;
		}
		return -1;
	}
	
}
