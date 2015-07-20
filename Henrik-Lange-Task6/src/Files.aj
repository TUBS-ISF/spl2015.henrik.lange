import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public aspect Files {
	// TODO Auto-generated aspect
	Scanner sc = new Scanner(System.in);
	after() : call(void fileImport()) {
		System.out.println("Import Datei einlesen");
		System.out.println("Geben sie den Pfad zur Datei an: ");
		String path = sc.next();
		System.out.println("Geben sie das Thema an: ");
		String theme = sc.next();
		importData(path, theme);
		printDatabank();
	}

	void printDatabank() {
		if (!Learning_material.databank.isEmpty()) {
			String activeTheme = Learning_material.databank.get(0).getTheme();
			System.out.println(activeTheme + ":");
			for (int i = 0; i < Learning_material.databank.size(); i++) {
				if (!activeTheme.equals(Learning_material.databank.get(i).getTheme())) {
					System.out.println();
					activeTheme = Learning_material.databank.get(i).getTheme();
					System.out.println(activeTheme + ":");
					System.out.print(i + " " + Learning_material.databank.get(i));
				}
				System.out.print(i + " " + Learning_material.databank.get(i));
			}
		}
	}

	void importData(String filename, String theme) {
		try {
			String question;
			ArrayList<String> answer = new ArrayList<String>();
			ArrayList<String> wrongAnswer = new ArrayList<String>();
			StringTokenizer lineReader;
			StringTokenizer answerReader;
			int points;
			// initiate all reader
			FileReader datareader = new FileReader(filename);
			BufferedReader br = new BufferedReader(datareader);
			// Read first line
			String data = br.readLine();
			// test if data is empty
			while (data != null) {
				// delete every Object in the arraylist from answer for a new
				// dataset
				answer.clear();
				// Set delimiter char
				lineReader = new StringTokenizer(data, ";");
				// Read first token (question)
				question = lineReader.nextToken();
				// The next Token is the answer and sometimes it does not give
				// only 1 answer
				answerReader = new StringTokenizer(lineReader.nextToken(), "|");
				while (answerReader.countTokens() > 0) {
					answer.add(answerReader.nextToken());
				}

				if (lineReader.countTokens() == 0) {
					// no points or alternative wrong answer
					ArrayList<String> buf = new ArrayList<String>();
					Learning_material.databank.add(new Dataset(question,
							answer, 0, buf, theme));

				} else if (lineReader.countTokens() == 1) {
					// Points but no alternative wrong answer
					ArrayList<String> buf = new ArrayList<String>();
					Learning_material.databank.add(new Dataset(question,
							answer, Integer.parseInt(lineReader.nextToken()),
							buf, theme));
				} else if (lineReader.countTokens() == 2) {
					// everything
					points = Integer.parseInt(lineReader.nextToken());
					wrongAnswer = new ArrayList<String>();
					answerReader = new StringTokenizer(lineReader.nextToken(),
							"|");
					while (answerReader.countTokens() > 0) {
						wrongAnswer.add(answerReader.nextToken());
					}
					Learning_material.databank.add(new Dataset(question,
							answer, points, wrongAnswer, theme));
				} else {
					// something gone wrong
					System.out.println("unsupported number of entrys");
				}
				data = br.readLine();
			}

		} catch (IOException nofile) {
			System.out.println(nofile);

		}
		// Save databank in data.ser
		saveDatabank();
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