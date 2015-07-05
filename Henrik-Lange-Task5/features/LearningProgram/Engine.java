

import java.util.ArrayList;
import java.util.Random;

/**
 * Behavior methods
 * 
 * @author Henrik Lange
 *
 */
public class Engine {
	public Engine() {

	}

	boolean useEngine(ArrayList<String> answer, String givenAnswer) {
		return compareAnswer(answer, givenAnswer);
	}

	private boolean compareAnswer(ArrayList<String> answer, String givenAnswer) {
		boolean isCorrect = false;
		// #ifdef Comp1vsM
		ComparePlugin oneVsM = new OneVsM();
		isCorrect = oneVsM.isCorrect(answer, givenAnswer);
		// #endif
		// #ifdef Comp1vs1
//@		 ComparePlugin oneVsOne = new OneVsOne();
//@		 isCorrect = oneVsOne.isCorrect(answer, givenAnswer);
		// #endif

		return isCorrect;
	}

	int randomQuestion(int size) {
		Random rand = new Random();
		return rand.nextInt(size);
	}
}
