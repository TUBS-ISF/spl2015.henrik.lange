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
		return compareAnswer(answer, givenAnswer);
	}

	private boolean compareAnswer(ArrayList<String> answer,
			String givenAnswer) {
		boolean isCorrect =false;
		//#ifdef Comp1vsM
		if (answer.contains(givenAnswer)) {
			isCorrect= true;
		} else {
			isCorrect= false;
		}
		//#endif 
		//#ifdef Comp1vs1
//@		if (answer.get(0).equals(givenAnswer)) {
//@			isCorrect= true;
//@		} else {
//@			isCorrect= false;
//@		}
		//#endif
		
		return isCorrect;
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
