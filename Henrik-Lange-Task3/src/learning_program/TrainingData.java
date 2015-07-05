package learning_program;

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
	String saveData = "data.ser"; // Destination of the savepoint

	TrainingData() {
		// load data.ser
		loadDatabank();
	}

	ArrayList<Dataset> getDatabank() {
		return databank;
	}

	void importData(String filename, String theme) {
		try {
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
					databank.add(new Dataset(question, answer, 0, buf, theme));

				} else if (lineReader.countTokens() == 1) {
					// Points but no alternative wrong answer
					ArrayList<String> buf = new ArrayList<String>();
					databank.add(new Dataset(question, answer, Integer
							.parseInt(lineReader.nextToken()), buf, theme));
				} else if (lineReader.countTokens() == 2) {
					// everything
					int points = Integer.parseInt(lineReader.nextToken());
					ArrayList<String> wrongAnswer = new ArrayList<String>();
					answerReader = new StringTokenizer(lineReader.nextToken(),
							"|");
					while (answerReader.countTokens() > 0) {
						wrongAnswer.add(answerReader.nextToken());
					}
					databank.add(new Dataset(question, answer, points,
							wrongAnswer, theme));
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

	void editDatabank() {
		int start = 0;
		int end = 0;
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
				//#ifdef DeleteEntry
//@				if (databank.size() > 0) {
//@					System.out
//@							.println("Soll ein Bereich von Eintr?gen gel?scht werden? [j] oder [n]");
//@					option = sc.next();
//@					if (option.equals("j")) {
//@						printDatabank();
//@						while (true) {
//@							try {
//@								System.out
//@										.print("Geben Sie die erste Nummer des Bereiches an:");
//@								start = sc.nextInt();
//@								if (start > databank.size() || start < 0) {
//@									System.out.println("Ungueltiger Bereich");
//@									continue;
//@								}
//@								break;
//@							} catch (InputMismatchException e) {
//@								String errStr = sc.next();
//@								System.out.println("Bitte eine Zahl eingeben, "
//@										+ errStr + " ist keine!");
//@								continue;
//@							}
//@						}
//@						while (true) {
//@							try {
//@								System.out
//@										.print("Geben Sie die letzte Nummer des Bereiches an:");
//@								end = sc.nextInt();
//@								if (start > end || end < 0
//@										|| end > databank.size()) {
//@									System.out.println("Ungueltiger Bereich");
//@									continue;
//@								}
//@								break;
//@							} catch (InputMismatchException e) {
//@								String errStr = sc.next();
//@								System.out.println("Bitte eine Zahl eingeben, "
//@										+ errStr + " ist keine!");
//@								continue;
//@							}
//@						}
//@						for (int i = end; i >= start; i--) {
//@							System.out.println(i);
//@							databank.remove(i);
//@							System.out.println("gel?scht" + i);
//@						}
//@						saveDatabank();
//@						printDatabank();
//@					} else if (option.equals("n")) {
//@						// to delete more than one entry
//@						while (true) {
//@							printDatabank();
//@							boolean deleteAction = true;
//@							int entry = 0;
//@							while (true) {
//@								try {
//@									System.out
//@											.print("Geben Sie den Eintrag an der gel?scht werden soll, falls Sie fertig sind geben sie [n] ein:");
//@									entry = sc.nextInt();
//@									if (entry > databank.size() || entry < 0) {
//@										System.out
//@												.println("Ungueltiger Bereich");
//@										continue;
//@									}
//@									break;
//@								} catch (InputMismatchException e) {
//@									String errStr = sc.next();
//@									if (errStr.equals("n")) {
//@										deleteAction = false;
//@										break;
//@									} else {
//@										System.out
//@												.println("Bitte eine Zahl eingeben, "
//@														+ errStr
//@														+ " ist keine!");
//@										continue;
//@									}
//@
//@								}
//@							}
//@							if (deleteAction) {
//@								databank.remove(entry);
//@								saveDatabank();
//@							} else {
//@								break;
//@							}
//@						}
//@					}
//@				} else {
//@					System.out.println("keine Daten");
//@				}
			//#else
				System.out.println("Nicht im Programm erhaeltlich");
			//#endif
			} else if (option.equals("2")) {
				//#ifdef Individually
//@				System.out.println("Datenbank wird veraendert");
//@				System.out.println("[1] Daten per Hand hinzufuegen");
//@				System.out.println("[2] Daten per Hand ver?ndern");
//@				System.out.println("[3] zur?ck");
//@				Scanner ssc = new Scanner(System.in);
//@				ssc.useDelimiter("\\n");
//@				option = sc.next();
//@				if (option.equals("1")) {
//@					int countAnswer;
//@					int countWrongAnswer;
//@					System.out.print("Frage: ");
//@					question = ssc.nextLine();
//@					while (true) {
//@						try {
//@							System.out.println("Anzahl der Antworten: ");
//@							countAnswer = sc.nextInt();
//@							break;
//@						} catch (InputMismatchException e) {
//@							String errStr = sc.next();
//@							System.out.println("Bitte eine Zahl eingeben, "
//@									+ errStr + " ist keine!");
//@							continue;
//@						}
//@					}
//@					for (int i = 0; i < countAnswer; i++) {
//@						System.out.print("Antwort " + i + 1 + ": ");
//@						answer.add(ssc.nextLine());
//@					}
//@
//@					while (true) {
//@						try {
//@							System.out.print("Punkte: ");
//@							points = sc.nextInt();
//@							break;
//@						} catch (InputMismatchException e) {
//@							String errStr = sc.next();
//@							System.out.println("Bitte eine Zahl eingeben, "
//@									+ errStr + " ist keine!");
//@							continue;
//@						}
//@					}
//@					while (true) {
//@						try {
//@							System.out
//@									.println("Anzahl der Falschen Antworten: ");
//@							countWrongAnswer = sc.nextInt();
//@							break;
//@						} catch (InputMismatchException e) {
//@							String errStr = sc.next();
//@							System.out.println("Bitte eine Zahl eingeben, "
//@									+ errStr + " ist keine!");
//@							continue;
//@						}
//@					}
//@					for (int i = 0; i < countWrongAnswer; i++) {
//@						System.out.print("Falsche Antwort " + i + 1 + ": ");
//@						wrongAnswer.add(ssc.nextLine());
//@					}
//@					System.out.print("Thema: ");
//@					String theme = sc.next();
//@					databank.add(new Dataset(question, answer, points,
//@							wrongAnswer, theme));
//@					saveDatabank();
//@				} else if (option.equals("2")) {
//@					System.out.println("Soon");
//@					continue;
//@
//@				}
				//#else
				System.out.println("Nicht in der Version enthalten!");
				//#endif
			} else if (option.equals("3")) {
				//#ifdef DeleteBib
//@				System.out.println("Die Datenbank wird geloescht");
//@				databank.clear();
//@				saveDatabank();
				//#else
				System.out.println("Nicht in der Version enthalen");
				//#endif
			} else if (option.equals("4")) {
				//#ifdef Files
//@				System.out.println("Import Datei einlesen");
//@				System.out.println("Geben sie den Pfad zur Datei an: ");
//@				String path = sc.next();
//@				System.out.println("Geben sie das Thema an: ");
//@				String theme = sc.next();
//@				importData(path, theme);
//@				printDatabank();
				//#else
				System.out.println("Nicht in der Version enthalten!");
				//#endif
			} else if (option.equals("5")) {
				//#ifdef Export
//@				exportData("Data/Training_Data_export.txt");
				//#else
				System.out.println("Nicht in der Version enthalten!");
				//#endif
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

	private void saveDatabank() {
		Collections.sort(databank, new Comparator<Dataset>() {
			public int compare(Dataset set1, Dataset set2) {

				return set1.getTheme().compareTo(set2.getTheme());
			}
		});
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(saveData);
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
