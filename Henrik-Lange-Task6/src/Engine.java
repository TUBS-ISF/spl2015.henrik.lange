import java.util.ArrayList;
import java.util.Random;

/**
 * Behavior methods
 * 
 * @author Henrik Lange
 *
 */
public class Engine {
	static boolean isCorrect;
	static ArrayList<String> answer;
	static String givenAnswer;
	static Random rand;

	public Engine() {
		rand = new Random();
	}

	boolean useEngine(ArrayList<String> answer, String givenAnswer) {
		this.answer = answer;
		this.givenAnswer = givenAnswer;
		System.out.println("test3");
		compareAnswer();
		return isCorrect;
	}

	private void compareAnswer() {

	}

}
