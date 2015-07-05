

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Databank with utility methods
 * 
 * @author Henrik Lange
 *
 */
class TrainingData {
	private ArrayList<Dataset> databank = new ArrayList<Dataset>();
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private ArrayList<String> wrongAnswer = new ArrayList<String>();
	private StringTokenizer lineReader;
	private StringTokenizer answerReader;
	private int points;
	OutputStream outputStream = null;


	TrainingData() {
		// load data.ser
		loadDatabank();
	}

	ArrayList<Dataset> getDatabank() {
		return databank;
	}

	void exportData(String destination) {
		Scanner sc = new Scanner(System.in);

		try {
			File file = new File(destination);
			FileWriter fw = null;

			if (file.exists()) {
				System.out
						.println("Die Datei existiert schon, soll sie geloescht [d], ergaenzt [e] oder der Vorgang abgebrochen [a] werden?");
				String option = sc.next();
				if (option.equals("d")) {
					file.delete();
					System.out.println("Datei wurde geloescht");
				} else if (option.equals("e")) {
					System.out.println("Datei wird erg?nzt");

				} else if (option.equals("a")) {
					System.out.println("Die Operation wird abgebrochen");
					return;
				}

			}
			System.out.println("Export beginnt");
			fw = new FileWriter(file.getPath(), true);

			PrintWriter pw = new PrintWriter(fw);
			for (int i = 0; i < databank.size(); i++) {
				pw.print(databank.get(i).getQuestion());
				pw.print(";");
				pw.print(databank.get(i).getAnswer().get(0));
				if (databank.get(i).getAnswer().size() > 1) {
					if (databank.get(i).getAnswer().size() > 2) {
						for (int j = 1; j < databank.get(i).getAnswer().size() - 1; j++) {
							pw.print(databank.get(i).getAnswer().get(j));
							pw.print("|");
						}
					}
					pw.print("|");
					pw.print(databank.get(i).getAnswer()
							.get(databank.get(i).getAnswer().size() - 1));
				}

				pw.print(";");
				pw.print(databank.get(i).getPoints());

				pw.print(";");
				if (databank.get(i).getWrongAnswers().size() > 0) {
					pw.print(databank.get(i).getWrongAnswers().get(0));
					if (databank.get(i).getWrongAnswers().size() > 1) {
						pw.print("|");
						if (databank.get(i).getWrongAnswers().size() > 2) {
							for (int j = 1; j < databank.get(i)
									.getWrongAnswers().size() - 1; j++) {
								pw.print(databank.get(i).getWrongAnswers()
										.get(j));
								pw.print("|");
							}
						}
						// pw.print("|");
						pw.print(databank
								.get(i)
								.getWrongAnswers()
								.get(databank.get(i).getWrongAnswers().size() - 1));
					}
				}
				pw.print(";");

				pw.println(databank.get(i).getTheme());
			}

			fw.flush();
			fw.close();

			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadDatabank() {
		File file = new File("data.ser");
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream("data.ser");
				ObjectInputStream objectInput = new ObjectInputStream(
						inputStream);
				databank = (ArrayList<Dataset>) objectInput.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveDatabank() {
		Collections.sort(databank, new Comparator<Dataset>() {
			public int compare(Dataset set1, Dataset set2) {

				return set1.getTheme().compareTo(set2.getTheme());
			}
		});
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("data.ser");
			ObjectOutputStream objectOutput = new ObjectOutputStream(
					outputStream);
			objectOutput.writeObject(databank);
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

	/**
	 * Print out the databank with the position in the array
	 */
	private void printDatabank() {
		if (!databank.isEmpty()) {
			String activeTheme = databank.get(0).getTheme();
			System.out.println(activeTheme + ":");
			for (int i = 0; i < databank.size(); i++) {
				if (!activeTheme.equals(databank.get(i).getTheme())) {
					System.out.println();
					activeTheme = databank.get(i).getTheme();
					System.out.println(activeTheme + ":");
					System.out.print(i + " " + databank.get(i));
				}
				System.out.print(i + " " + databank.get(i));
			}
		}
	}
}
