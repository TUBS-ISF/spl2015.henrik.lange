

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
	ImportPlugin importFile = new ImportFile();

	TrainingData() {
		// load data.ser
		loadDatabank();
	}

	ArrayList<Dataset> getDatabank() {
		return databank;
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
				if (databank.size() > 0) {
					System.out
							.println("Soll ein Bereich von Eintr?gen gel?scht werden? [j] oder [n]");
					option = sc.next();
					if (option.equals("j")) {
						printDatabank();
						while (true) {
							try {
								System.out
										.print("Geben Sie die erste Nummer des Bereiches an:");
								start = sc.nextInt();
								if (start > databank.size() || start < 0) {
									System.out.println("Ungueltiger Bereich");
									continue;
								}
								break;
							} catch (InputMismatchException e) {
								String errStr = sc.next();
								System.out.println("Bitte eine Zahl eingeben, "
										+ errStr + " ist keine!");
								continue;
							}
						}
						while (true) {
							try {
								System.out
										.print("Geben Sie die letzte Nummer des Bereiches an:");
								end = sc.nextInt();
								if (start > end || end < 0
										|| end > databank.size()) {
									System.out.println("Ungueltiger Bereich");
									continue;
								}
								break;
							} catch (InputMismatchException e) {
								String errStr = sc.next();
								System.out.println("Bitte eine Zahl eingeben, "
										+ errStr + " ist keine!");
								continue;
							}
						}
						for (int i = end; i >= start; i--) {
							System.out.println(i);
							databank.remove(i);
							System.out.println("gel?scht" + i);
						}
						saveDatabank();
						printDatabank();
					} else if (option.equals("n")) {
						// to delete more than one entry
						while (true) {
							printDatabank();
							boolean deleteAction = true;
							int entry = 0;
							while (true) {
								try {
									System.out
											.print("Geben Sie den Eintrag an der gel?scht werden soll, falls Sie fertig sind geben sie [n] ein:");
									entry = sc.nextInt();
									if (entry > databank.size() || entry < 0) {
										System.out
												.println("Ungueltiger Bereich");
										continue;
									}
									break;
								} catch (InputMismatchException e) {
									String errStr = sc.next();
									if (errStr.equals("n")) {
										deleteAction = false;
										break;
									} else {
										System.out
												.println("Bitte eine Zahl eingeben, "
														+ errStr
														+ " ist keine!");
										continue;
									}

								}
							}
							if (deleteAction) {
								databank.remove(entry);
								saveDatabank();
							} else {
								break;
							}
						}
					}
				} else {
					System.out.println("keine Daten");
				}
			//#else
//@				System.out.println("Nicht im Programm erhaeltlich");
			//#endif
			} else if (option.equals("2")) {
				//#ifdef Individually
				System.out.println("Datenbank wird veraendert");
				System.out.println("[1] Daten per Hand hinzufuegen");
				System.out.println("[2] Daten per Hand ver?ndern");
				System.out.println("[3] zur?ck");
				Scanner ssc = new Scanner(System.in);
				ssc.useDelimiter("\\n");
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
							System.out.println("Bitte eine Zahl eingeben, "
									+ errStr + " ist keine!");
							continue;
						}
					}
					for (int i = 0; i < countAnswer; i++) {
						System.out.print("Antwort " + i + 1 + ": ");
						answer.add(ssc.nextLine());
					}

					while (true) {
						try {
							System.out.print("Punkte: ");
							points = sc.nextInt();
							break;
						} catch (InputMismatchException e) {
							String errStr = sc.next();
							System.out.println("Bitte eine Zahl eingeben, "
									+ errStr + " ist keine!");
							continue;
						}
					}
					while (true) {
						try {
							System.out
									.println("Anzahl der Falschen Antworten: ");
							countWrongAnswer = sc.nextInt();
							break;
						} catch (InputMismatchException e) {
							String errStr = sc.next();
							System.out.println("Bitte eine Zahl eingeben, "
									+ errStr + " ist keine!");
							continue;
						}
					}
					for (int i = 0; i < countWrongAnswer; i++) {
						System.out.print("Falsche Antwort " + i + 1 + ": ");
						wrongAnswer.add(ssc.nextLine());
					}
					System.out.print("Thema: ");
					String theme = sc.next();
					databank.add(new Dataset(question, answer, points,
							wrongAnswer, theme));
					saveDatabank();
				} else if (option.equals("2")) {
					System.out.println("Soon");
					continue;

				}
				//#else
//@				System.out.println("Nicht in der Version enthalten!");
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
//@				importFile.importData(databank);
//@				printDatabank();
				//#else
				System.out.println("Nicht in der Version enthalten!");
				//#endif
			} else if (option.equals("5")) {
				//#ifdef Export
				exportData("Data/Training_Data_export.txt");
				//#else
//@				System.out.println("Nicht in der Version enthalten!");
				//#endif
			} else if (option.equals("6")) {
				break;
			}
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
