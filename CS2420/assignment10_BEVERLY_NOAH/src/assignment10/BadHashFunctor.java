package assignment10;

public class BadHashFunctor implements HashFunctor {

	public BadHashFunctor() {
	}

	/*
	 * Hash Funciton defined for the Bad Hash Functor. A poor Hasher. 
	 * Takes an input item and returns an int bad hashed value.
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}

}
