

import java.util.ArrayList;

public class OneVsM implements ComparePlugin {

	
	public boolean isCorrect(ArrayList<String> answer, String givenAnswer) {
		// TODO Auto-generated method stub
		if (answer.contains(givenAnswer)) {
			return true;
		} else {
			return false;
		}

	}
}
