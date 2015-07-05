

import java.util.ArrayList;

public class OneVsOne implements ComparePlugin {

	public boolean isCorrect(ArrayList<String> answer, String givenAnswer) {
		// TODO Auto-generated method stub
		if (answer.get(0).equals(givenAnswer)) {
			return true;
		} else {
			return false;
		}
	}

}
