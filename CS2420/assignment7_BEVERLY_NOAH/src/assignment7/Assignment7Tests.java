package assignment7;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Assignment7Tests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {

		try {
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class1.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class2.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class3.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class4.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class5.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class6.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class7.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class8.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class9.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class10.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class11.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class12.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class13.java"));
			System.out.println(BalancedSymbolChecker.checkFile("/Users/Josh/Downloads/A7_examples/Class14.java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
