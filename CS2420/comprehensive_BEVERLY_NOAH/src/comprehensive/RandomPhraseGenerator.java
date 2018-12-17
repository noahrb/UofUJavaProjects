package comprehensive;

public class RandomPhraseGenerator {

	public static void main(String[] args) {

		GrammarTree tree = new GrammarTree(args[0]);

		int total = Integer.parseInt(args[1]);
		for (int i = 0; i < total; i++) {
			System.out.println(tree.generateRandomPhrase());
		}
	}
	
}