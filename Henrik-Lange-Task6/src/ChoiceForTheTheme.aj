import java.awt.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public aspect ChoiceForTheTheme {
	// TODO Auto-generated aspect
	declare precedence: ChoiceForTheTheme,TopDown, Random;
	Scanner sc = new Scanner(System.in);

	after(): call(void chooseQuestion()) {
		if (Learning_material.numberQue < Learning_material.databank.size()) {
			if (Learning_material.databank.get(Learning_material.numberQue)
					.getTheme().equals(Learning_material.choosenTheme)) {
				Learning_material.correctQuestion = true;
			} else {
				Learning_material.correctQuestion = false;
			}
		}

	}

	after(): call(void Learning_material.chooseTheme()) {
		Learning_material.stopLearning = false;
		Learning_material.newTheme = "";
		Learning_material.choosenTheme = "";
		Learning_material.themeList = new List();
		System.out.println("Waehlen Sie das Thema");
		for (int i = 0; i < Learning_material.databank.size(); i++) {
			if (!Learning_material.databank.get(i).getTheme()
					.equals(Learning_material.newTheme)) {
				Learning_material.themeList.add(Learning_material.databank.get(
						i).getTheme());
				Learning_material.newTheme = Learning_material.databank.get(i)
						.getTheme();
			}
		}
		for (int i = 0; i < Learning_material.themeList.getItemCount(); i++) {
			System.out.println("[" + (i + 1) + "] Thema: "
					+ Learning_material.themeList.getItem(i));
		}
		System.out
				.println("[" + (Learning_material.themeList.getItemCount() + 1)
						+ "] zur?ck");
		// Themen Auswahl nur 1
		while (true) {
			try {
				int choice = sc.nextInt();
				if (choice > Learning_material.themeList.getItemCount() + 1
						|| choice <= 0) {
					System.out.println("Zahl ist nicht im Bereich!");
					continue;
				}
				if (choice == (Learning_material.themeList.getItemCount() + 1)) {
					Learning_material.stopLearning = true;
					break;
				}
				Learning_material.choosenTheme = Learning_material.themeList
						.getItem(choice - 1);
				break;
			} catch (InputMismatchException e) {
				String errStr = sc.nextLine();
				System.out.println("Bitte eine Zahl eingeben, " + errStr
						+ " ist keine g?ltige!");
				continue;
			}
		}
	}
}