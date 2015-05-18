package learning_program;

import java.util.ArrayList;
import java.util.Random;
/**
 * Behavior methods
 * @author Henrik Lange
 *
 */
public class Engine {
	public Engine(){
		
	}
	boolean useEngine(ArrayList<String> answer, String givenAnswer) {
		System.out.println("Test");
		if (Configuration.oneVsM) {
			return compareAllAnswer(answer, givenAnswer);
		} else {
			return compareOneAnswer(answer, givenAnswer);
		}

	}

	private boolean compareAllAnswer(ArrayList<String> answer,
			String givenAnswer) {
		System.out.println("All");
		if (answer.contains(givenAnswer)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean compareOneAnswer(ArrayList<String> answer,
			String givenAnswer) {
		if (answer.get(0).equals(givenAnswer)) {
			return true;
		} else {
			return false;
		}
	}

	int randomQuestion(int size) {
		Random rand = new Random();
		return rand.nextInt(size);
	}
}
