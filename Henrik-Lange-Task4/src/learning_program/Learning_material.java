package learning_program;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Is a class for different learn methods
 * 
 * @author Henrik Lange
 *
 */
public class Learning_material {
	Scanner sc = new Scanner(System.in);
	ArrayList<Dataset> databank;
	List themeList;
	private Engine engine = new Engine();

	public Learning_material(ArrayList<Dataset> databank) {
		this.databank = databank;
		// sc.useDelimiter("//n");
	}

	/**
	 * Show the question and the user needs to answer this question
	 * 
	 * @param data
	 */
	void choze_test(Dataset data) {
		// sc.useDelimiter("\\n");
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

	/**
	 * Show question and with a press the answers will be shown
	 * 
	 * @param data
	 */
	void flashcard(Dataset data) {
		System.out.println("Frage: " + data.getQuestion());
		Scanner scc = new Scanner(System.in);
		scc.useDelimiter("//n");
		String buffer = scc.nextLine();
		for (int i = 0; i < data.getAnswer().size(); i++) {
			System.out.println("Antwort: " + data.getAnswer().get(i));
		}
	}

	/**
	 * This Method needs answers which are wrong
	 * 
	 * @param data
	 */
	void mutiply_choice(Dataset data) {
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

	void startLearning() {
		boolean stopLearning = false;
		while (true) {
			//#ifdef ChoiceForTheTheme
//@			
//@			stopLearning = false;
//@			String newTheme = "";
//@			String choosenTheme = "";
//@			themeList = new List();
//@			System.out.println("Waehlen Sie das Thema");
//@			for (int i = 0; i < databank.size(); i++) {
//@				if (!databank.get(i).getTheme().equals(newTheme)) {
//@					themeList.add(databank.get(i).getTheme());
//@					newTheme = databank.get(i).getTheme();
//@				}
//@			}
//@			for (int i = 0; i < themeList.getItemCount(); i++) {
//@				System.out.println("[" + (i + 1) + "] Thema: "
//@						+ themeList.getItem(i));
//@			}
//@			System.out.println("[" + (themeList.getItemCount() + 1) + "] zur?ck");
//@			// Themen Auswahl nur 1
//@			while (true) {
//@				try {
//@					int choice = sc.nextInt();
//@					if (choice > themeList.getItemCount() + 1 || choice <= 0) {
//@						System.out.println("Zahl ist nicht im Bereich!");
//@						continue;
//@					}
//@					if (choice == (themeList.getItemCount() + 1)) {
//@						stopLearning = true;
//@						break;
//@					}
//@					choosenTheme = themeList.getItem(choice - 1);
//@					break;
//@				} catch (InputMismatchException e) {
//@					String errStr = sc.nextLine();
//@					System.out.println("Bitte eine Zahl eingeben, " + errStr
//@							+ " ist keine g?ltige!");
//@					continue;
//@				}
//@			}
			//#endif
			while (true) {
				if (stopLearning) {
					break;
				}
				System.out.println("Waehlen sie den Modus");
				System.out.println("[1] Karteikarten");
				System.out.println("[2] Lueckentext");
				System.out.println("[3] Multiply Choice");
				System.out.println("[4] zur?ck");
				String option = sc.next();
				if (option.equals("1")) {
					//#ifdef Flashcard
//@					System.out.println("Karteikarten");
					//#ifdef TopDown
//@					for (int i = 0; i < databank.size(); i++) {
						//#ifdef ChoiceForTheTheme
//@						if (databank.get(i).getTheme().equals(choosenTheme)) {
//@							flashcard(databank.get(i));
//@						}
						//#else
//@						flashcard(databank.get(i));
						//#endif
//@					}
//@					
					//#endif
					//#ifdef Random
//@					for (int i = 0; i < databank.size(); i++) {
//@						int randQue = engine
//@								.randomQuestion(databank.size());
					//#ifdef ChoiceForTheTheme
//@						if (databank.get(randQue).getTheme()
//@								.equals(choosenTheme)) {
//@							flashcard(databank.get(randQue));
//@						} else {
//@							i--;
//@						}
						//#else
//@						flashcard(databank.get(randQue));
						//#endif
//@					}
//@						
					//#endif
					//#else
					System.out.println("Nicht definiert");
					//#endif 
				} else if (option.equals("2")) {
					//#ifdef ClozeTest
//@					System.out.println("Lueckentext");
					//#ifdef TopDown
//@					for (int i = 0; i < databank.size(); i++) {
						//#ifdef ChoiceForTheTheme
//@						if (databank.get(i).getTheme().equals(choosenTheme)) {
//@							choze_test(databank.get(i));
//@						}
						//#else
//@						choze_test(databank.get(i));
						//#endif
//@					}					
					//#endif
					//#ifdef Random
//@					for (int i = 0; i < databank.size(); i++) {
//@						int randQue = engine
//@								.randomQuestion(databank.size());
					//#ifdef ChoiceForTheTheme
//@						if (databank.get(randQue).getTheme()
//@								.equals(choosenTheme)) {
//@							choze_test(databank.get(randQue));
//@						} else {
//@							i--;
//@						}
					//#else
//@						choze_test(databank.get(randQue));
					//#endif
//@					}	
					//#endif
//@				
					//#else
					System.out.println("Nicht definiert");
					//#endif
				} else if (option.equals("3")) {
					//#ifdef MultiplyChoice
//@					System.out.println("Multiply Choice");
					//#ifdef TopDown
//@					for (int i = 0; i < databank.size(); i++) {
						//#ifdef ChoiceForTheTheme
//@						if (databank.get(i).getTheme().equals(choosenTheme)) {
//@							if (databank.get(i).isMultiply_choice()) {
//@								mutiply_choice(databank.get(i));
//@							} 
//@						}
						//#else
//@						mutiply_choice(databank.get(i));
						//#endif
//@					}
					//#endif
//@					
					//#ifdef Random
//@					for (int i = 0; i < databank.size(); i++) {
//@						Dataset buffData = databank.get(engine
//@								.randomQuestion(databank.size()));
						//#ifdef ChoiceForTheTheme
//@						while (!buffData.isMultiply_choice()
//@								|| !buffData.getTheme()
//@										.equals(choosenTheme)) {
//@							buffData = databank.get(engine
//@									.randomQuestion(databank.size()));
//@						}
//@						//else
//@						while (!buffData.isMultiply_choice(){
//@							buffData = databank.get(engine.randomQuestion(databank.size()));
//@						}
						//#endif
//@						mutiply_choice(buffData);
//@					}
					//#endif
					//#else
					System.out.println("Nicht definiert");
					//#endif		
				} else if (option.equals("4")) {
					break;
				} else {
					continue;
				}
			}
			if (stopLearning) {
				break;
			}
		}

	}
}
