package learning_program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/**
 * This Method needs answers which are wrong
 * 
 * @param data
 */
public class Mutiply_choice implements MaterialPlugin {
	private Scanner sc = new Scanner(System.in);
	private Engine engine = new Engine();

	@Override
	public void askQuestion(Dataset data) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int answer;
		System.out.println(data.getQuestion());
		ArrayList<String> answerOption = new ArrayList<String>();
		answerOption.add(data.getAnswer().get(
				rand.nextInt(data.getAnswer().size()))); // W?hlt eine zuf?llige
															// richtige Antwort
		String correctAnswer = answerOption.get(0);
		answerOption.addAll(data.getWrongAnswers());
		Collections.shuffle(answerOption);
		int correctPosition = answerOption.indexOf(correctAnswer) + 1;
		for (int i = 1; i <= answerOption.size(); i++) {
			System.out.println("[" + i + "] " + answerOption.get(i - 1));
		}
		while (true) {
			try {
				answer = sc.nextInt();
				if (answer > answerOption.size() || answer < 0) {
					System.out.println("Zahl ist nicht im Bereich!");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				String errStr = sc.nextLine();
				System.out.println("Bitte eine Zahl eingeben, " + errStr
						+ " ist keine g?ltige!");
				continue;
			}
		}
		if (answer == correctPosition) {
			System.out.println("Die Antwort war Korrekt!");
		} else {
			System.out
					.println("Die Antwort war falsch, korrekt w?re die Antwort '"
							+ correctAnswer + "'");
		}

	}

}
