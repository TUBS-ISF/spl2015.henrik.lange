import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public aspect Individually {
	// TODO Auto-generated aspect
	after(): call(void loadDatabank()){
		TrainingData.individually = true;
	}

	after(): call(void individuallImport()){
		String question;
		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<String> wrongAnswer = new ArrayList<String>();
		int points;
		Scanner sc = new Scanner(System.in);
		Scanner ssc = new Scanner(System.in);
		ssc.useDelimiter("\\n");
		System.out.println("Datenbank wird veraendert");
		System.out.println("[1] Daten per Hand hinzufuegen");
		System.out.println("[2] Daten per Hand ver?ndern");
		System.out.println("[3] zur?ck");
		String option;
		option = sc.next();
		if (option.equals("1")) {
			int countAnswer;
			int countWrongAnswer;
			System.out.print("Frage: ");
			question = ssc.nextLine();
			while (true) {
				try {
					System.out.println("Anzahl der Antworten: ");
					countAnswer = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					String errStr = sc.next();
					System.out.println("Bitte eine Zahl eingeben, " + errStr
							+ " ist keine!");
					continue;
				}
			}
			for (int i = 0; i < countAnswer; i++) {
				System.out.print("Antwort " + (i + 1) + ": ");
				answer.add(ssc.nextLine());
			}

			while (true) {
				try {
					System.out.print("Punkte: ");
					points = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					String errStr = sc.next();
					System.out.println("Bitte eine Zahl eingeben, " + errStr
							+ " ist keine!");
					continue;
				}
			}
			while (true) {
				try {
					System.out.println("Anzahl der Falschen Antworten: ");
					countWrongAnswer = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					String errStr = sc.next();
					System.out.println("Bitte eine Zahl eingeben, " + errStr
							+ " ist keine!");
					continue;
				}
			}
			for (int i = 0; i < countWrongAnswer; i++) {
				System.out.print("Falsche Antwort " + i + 1 + ": ");
				wrongAnswer.add(ssc.nextLine());
			}
			System.out.print("Thema: ");
			String theme = sc.next();
			Learning_material.databank.add(new Dataset(question, answer,
					points, wrongAnswer, theme));
			saveDatabank();
		} else if (option.equals("2")) {
			System.out.println("Soon");
		}

	}

	private void saveDatabank() {
		String saveData = "data.ser"; // Destination of the savepoint
		Collections.sort(Learning_material.databank, new Comparator<Dataset>() {
			public int compare(Dataset set1, Dataset set2) {

				return set1.getTheme().compareTo(set2.getTheme());
			}
		});
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(saveData);
			ObjectOutputStream objectOutput = new ObjectOutputStream(
					outputStream);
			objectOutput.writeObject(Learning_material.databank);
			objectOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}