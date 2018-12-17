package assignment10;

import java.util.Random;

public class GoodHashFunctor implements HashFunctor {

	public GoodHashFunctor() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Hash Function defined for the Good Hash Functor. A good Hasher. 
	 * Takes an input item and returns an int good hashed value.
	 */
	@Override
	public int hash(String item) {
		if (item == "") return 0;
		
		int out = 0;
		Random rand = new Random((int) item.charAt(item.length() -1));
		for (int i = 0; i < 15; i++) {
			if (i > item.length()) out += rand.nextInt(1000000);
			if (i == item.length()-1) out += (int) item.charAt(i);
			if (i < item.length()) out += (int) 'a' + rand.nextInt(26);
		}
		
		if(item.length() > 2) {
			out *= (int) item.charAt(0)/item.charAt(1);
		} else {
			out *= (int) item.charAt(0)/item.charAt(1);
		}
		
		return out;
	}

}
