

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

	
	void startLearning() {
		boolean stopLearning = false;
		while (true) {
			String newTheme = "";
			String choosenTheme = "";
			if (conf.choiceForTheTheme) {
				stopLearning = false;

				themeList = new List();
				System.out.println("Waehlen Sie das Thema");
				for (int i = 0; i < databank.size(); i++) {
					if (!databank.get(i).getTheme().equals(newTheme)) {
						themeList.add(databank.get(i).getTheme());
						newTheme = databank.get(i).getTheme();
					}
				}
				for (int i = 0; i < themeList.getItemCount(); i++) {
					System.out.println("[" + (i + 1) + "] Thema: "
							+ themeList.getItem(i));
				}
				System.out.println("[" + (themeList.getItemCount() + 1)
						+ "] zur?ck");
				// Themen Auswahl nur 1
				while (true) {
					try {
						int choice = sc.nextInt();
						if (choice > themeList.getItemCount() + 1
								|| choice <= 0) {
							System.out.println("Zahl ist nicht im Bereich!");
							continue;
						}
						if (choice == (themeList.getItemCount() + 1)) {
							stopLearning = true;
							break;
						}
						choosenTheme = themeList.getItem(choice - 1);
						break;
					} catch (InputMismatchException e) {
						String errStr = sc.nextLine();
						System.out.println("Bitte eine Zahl eingeben, "
								+ errStr + " ist keine g?ltige!");
						continue;
					}
				}
			}
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
					if (conf.flashcard) {
						System.out.println("Karteikarten");
						if (conf.topDown) {
							for (int i = 0; i < databank.size(); i++) {
								if (conf.choiceForTheTheme) {
									if (databank.get(i).getTheme().equals(choosenTheme)) {
										flashcard.askQuestion(databank.get(i));
									}
								} else {
									flashcard.askQuestion(databank.get(i));
								}
							}

						}
						if (conf.random) {
							for (int i = 0; i < databank.size(); i++) {
								int randQue = engine.randomQuestion(databank
										.size());
								if (conf.choiceForTheTheme) {
									if (databank.get(randQue).getTheme()
											.equals(choosenTheme)) {

										flashcard.askQuestion(databank
												.get(randQue));
									} else {
										i--;
									}
								} else {
									flashcard
											.askQuestion(databank.get(randQue));
								}
							}

						}
					} else {
						System.out.println("Nicht definiert");
					}
				} else if (option.equals("2")) {
					if (conf.clozeTest) {
						System.out.println("Lueckentext");
						if (conf.topDown) {
							for (int i = 0; i < databank.size(); i++) {
								if (conf.choiceForTheTheme) {
									if (databank.get(i).getTheme().equals(choosenTheme)) {
										choze_test.askQuestion(databank.get(i));
									}
								} else {

									choze_test.askQuestion(databank.get(i));
								}
							}
						}
						if (conf.random) {
							for (int i = 0; i < databank.size(); i++) {
								int randQue = engine.randomQuestion(databank.size());
								if (conf.choiceForTheTheme) {
									if (databank.get(randQue).getTheme().equals(choosenTheme)) {

										choze_test.askQuestion(databank.get(randQue));
									} else {
										i--;
									}
								} else {

									choze_test.askQuestion(databank.get(randQue));
								}
							}
						}

					} else {
						System.out.println("Nicht definiert");
					}
				} else if (option.equals("3")) {
					if (conf.multiplyChoice) {
						System.out.println("Multiply Choice");
						if (conf.topDown) {
							for (int i = 0; i < databank.size(); i++) {
								if (conf.choiceForTheTheme) {
									if (databank.get(i).getTheme()
											.equals(choosenTheme)) {
										if (databank.get(i).isMultiply_choice()) {
											multiply_choice
													.askQuestion(databank
															.get(i));
										}
									}
								} else {

									multiply_choice
											.askQuestion(databank.get(i));
								}
							}
						}

						if (conf.random) {
							for (int i = 0; i < databank.size(); i++) {
								Dataset buffData = databank.get(engine
										.randomQuestion(databank.size()));
								if (conf.choiceForTheTheme) {
									while (!buffData.isMultiply_choice()
											|| !buffData.getTheme().equals(
													choosenTheme)) {
										buffData = databank
												.get(engine
														.randomQuestion(databank
																.size()));
									}
									// else
									while (!buffData.isMultiply_choice()) {
										buffData = databank
												.get(engine
														.randomQuestion(databank
																.size()));
									}
								}
								multiply_choice.askQuestion(buffData);
							}
						}
					} else {
						System.out.println("Nicht definiert");
					}
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