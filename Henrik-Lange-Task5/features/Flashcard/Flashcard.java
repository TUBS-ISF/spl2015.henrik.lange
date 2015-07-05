

import java.util.Scanner;

/**
 * Show question and with a press the answers will be shown
 * 
 * @param data
 */
public class Flashcard implements MaterialPlugin {

	public void askQuestion(Dataset data) {
		// TODO Auto-generated method stub
		System.out.println("Frage: " + data.getQuestion());
		Scanner scc = new Scanner(System.in);
		scc.useDelimiter("//n");
		String buffer = scc.nextLine();
		for (int i = 0; i < data.getAnswer().size(); i++) {
			System.out.println("Antwort: " + data.getAnswer().get(i));
		}

	}

}
