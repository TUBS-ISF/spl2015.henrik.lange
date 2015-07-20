import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public aspect DeleteEntry {
	// TODO Auto-generated aspect
	
	after(): call(void loadDatabank()){
		TrainingData.deleteEntry = true;
	}
	after(): call(void deleteEntry()){
		Scanner sc = new Scanner(System.in);
		String option;
		int start = 0;
		int end = 0;

		if (Learning_material.databank.size() > 0) {
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
						if (start > Learning_material.databank.size() || start < 0) {
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
								|| end > Learning_material.databank.size()) {
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
					Learning_material.databank.remove(i);
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
							if (entry > Learning_material.databank.size() || entry < 0) {
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
						Learning_material.databank.remove(entry);
						saveDatabank();
					} else {
						break;
					}
				}
			}
		} else {
			System.out.println("keine Daten");
		}
	}
	
	private void printDatabank() {
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