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
	static boolean export = false;
	static boolean deleteEntry = false;
	static boolean individually = false;
	static boolean deletebib = false;
	OutputStream outputStream = null;
	String saveData = "data.ser"; // Destination of the savepoint

	TrainingData() {
		// load data.ser
		loadDatabank();
	}

	ArrayList<Dataset> getDatabank() {
		return databank;
	}

	void exportData() {
		System.out.println("Export Data");
	}

	void deleteEntry() {
		System.out.println("eintrag löschen");
	}

	void individuallImport() {
		System.out.println("Individueller Import");
	}

	void deleteDatabank() {
		System.out.println("Datenbank Löschen");
	}

	void fileImport() {
		System.out.println("Datei einlesen");
	}

	void editDatabank() {
		printDatabank();
		while (true) {
			System.out.println("Art der Bearbeitung?");
			System.out.println("[1] Eintraege Loeschen");
			System.out.println("[2] Eintraege veraendern");
			System.out.println("[3] Datenbank loeschen");
			System.out.println("[4] Import Datei einlesen");
			System.out.println("[5] Export der Datenbank");
			System.out.println("[6] zurueck");
			Scanner sc = new Scanner(System.in);
			String option = sc.next();
			if (option.equals("1")) {
				if (deleteEntry) {
					deleteEntry();
				} else {
					System.out.println("Nicht im Programm erhaeltlich");
				}
			} else if (option.equals("2")) {
				if (individually) {
					individuallImport();
				} else {
					System.out.println("Nicht in der Version enthalten!");
				}
			} else if (option.equals("3")) {
				if (deletebib) {
					deleteDatabank();
				} else {
					System.out.println("Nicht in der Version enthalen");
				}

			} else if (option.equals("4")) {
				// #ifdef Files
				fileImport();

				// #else
				// @ System.out.println("Nicht in der Version enthalten!");
				// #endif
			} else if (option.equals("5")) {
				if (export) {
					exportData();
				} else {
					System.out.println("Nicht in der Version enthalten!");
				}

			} else if (option.equals("6")) {
				break;
			}
		}
	}

	private void loadDatabank() {
		File file = new File(saveData);
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(saveData);
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
