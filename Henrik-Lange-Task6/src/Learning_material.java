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
	static ArrayList<Dataset> databank;
	static List themeList;
	static String newTheme;
	static String choosenTheme;
	static boolean stopLearning;
	static int numberQue;
	static boolean flashcard;
	static boolean multiplyChoice;
	static boolean chozeTest;
	static boolean correctQuestion;
	static int questionCount;
	static String options;
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

	void chooseTheme() {

	}

	void chooseQuestion() {

	}

	void flashcardmode() {

	}

	void chozeTestmode() {

	}

	void MultiplyChoiceTestmode() {

	}

	void startLearning() {
		stopLearning = false;
		while (true) {
			chooseTheme();

			while (true) {
				correctQuestion = true;
				if (stopLearning) {
					break;
				}
				System.out.println("Waehlen sie den Modus");
				System.out.println("[1] Karteikarten");
				System.out.println("[2] Lueckentext");
				System.out.println("[3] Multiply Choice");
				System.out.println("[4] zur?ck");
				
				options = sc.next();
				if (options.equals("1")) {
					flashcardmode();
					if (flashcard) {
						for (questionCount = 0; questionCount < databank.size(); questionCount++) {
							chooseQuestion();
							while (!correctQuestion) {
								chooseQuestion();
							}
							flashcard(databank.get(numberQue));
						}
					} else {
						System.out.println("Nicht definiert");
					}
				} else if (options.equals("2")) {

					chozeTestmode();
					if (chozeTest) {
						for (questionCount = 0; questionCount < databank.size(); questionCount++) {
							chooseQuestion();
							while (!correctQuestion) {
								chooseQuestion();
							}
							choze_test(databank.get(numberQue));
						}
					} else {
						System.out.println("Nicht definiert");
					}
				} else if (options.equals("3")) {
					MultiplyChoiceTestmode();
					if (multiplyChoice) {
						for (questionCount = 0; questionCount < databank.size(); questionCount++) {
							chooseQuestion();
							while (!correctQuestion&&questionCount < databank.size()) {
								chooseQuestion();
							}
							mutiply_choice(databank.get(numberQue));
						}
					} else {
						System.out.println("Nicht definiert");
					}
				} else if (options.equals("4")) {
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
