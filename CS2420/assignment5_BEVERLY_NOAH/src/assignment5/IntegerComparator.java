package assignment5;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{

	// negative if the first input is less than the second
	public int compare(Integer o1, Integer o2) {
		return o1-o2;
	}
	
	

}
