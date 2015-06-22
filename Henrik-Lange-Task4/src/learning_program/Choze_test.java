package learning_program;

import java.util.Scanner;
/**
 * Show the question and the user needs to answer this question
 * 
 * @param data
 */
public class Choze_test implements MaterialPlugin {
	private Scanner sc = new Scanner(System.in);
	private Engine engine = new Engine();

	@Override
	public void askQuestion(Dataset data) {
		// TODO Auto-generated method stub
		System.out.print(data.getQuestion() + ": ");
		String answer = " ";
		answer = sc.next();
		System.out.println(data.getAnswer());
		if (engine.useEngine(data.getAnswer(), answer)) {
			System.out.println("Ihre Antwort ist Korrekt!");
		} else {
			System.out.println("Ihre Antwort ist Falsch!");
			System.out.println("Richtig ist: " + data.getAnswer().get(0));
		}

	}

}
