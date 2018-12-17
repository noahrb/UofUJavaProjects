package assignment10;

public class MediocreHashFunctor implements HashFunctor {

	public MediocreHashFunctor() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * Hash Function defined for the Mediocre Hash Functor. A mediocre Hasher. 
	 * Takes an input item and returns an int mediocre hashed value.
	 */
	@Override
	public int hash(String item) {
		int out = 0;
		for (int i = 0; i < item.length(); i++) {
			out  += (int) item.charAt(i);
		}
		return out;
	}

}
