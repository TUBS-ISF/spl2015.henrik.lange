import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public aspect Export {
	// TODO Auto-generated aspect
	after(): call(void loadDatabank()){
		TrainingData.export = true;
	}
	after(): call(void exportData()) {
		Scanner sc = new Scanner(System.in);
		String destination = "Data/Training_Data_export.txt";
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
			for (int i = 0; i < Learning_material.databank.size(); i++) {
				pw.print(Learning_material.databank.get(i).getQuestion());
				pw.print(";");
				pw.print(Learning_material.databank.get(i).getAnswer().get(0));
				if (Learning_material.databank.get(i).getAnswer().size() > 1) {
					if (Learning_material.databank.get(i).getAnswer().size() > 2) {
						for (int j = 1; j < Learning_material.databank.get(i).getAnswer().size() - 1; j++) {
							pw.print(Learning_material.databank.get(i).getAnswer().get(j));
							pw.print("|");
						}
					}
					pw.print("|");
					pw.print(Learning_material.databank.get(i).getAnswer()
							.get(Learning_material.databank.get(i).getAnswer().size() - 1));
				}

				pw.print(";");
				pw.print(Learning_material.databank.get(i).getPoints());

				pw.print(";");
				if (Learning_material.databank.get(i).getWrongAnswers().size() > 0) {
					pw.print(Learning_material.databank.get(i).getWrongAnswers().get(0));
					if (Learning_material.databank.get(i).getWrongAnswers().size() > 1) {
						pw.print("|");
						if (Learning_material.databank.get(i).getWrongAnswers().size() > 2) {
							for (int j = 1; j < Learning_material.databank.get(i)
									.getWrongAnswers().size() - 1; j++) {
								pw.print(Learning_material.databank.get(i).getWrongAnswers()
										.get(j));
								pw.print("|");
							}
						}
						// pw.print("|");
						pw.print(Learning_material.databank
								.get(i)
								.getWrongAnswers()
								.get(Learning_material.databank.get(i).getWrongAnswers().size() - 1));
					}
				}
				pw.print(";");

				pw.println(Learning_material.databank.get(i).getTheme());
			}

			fw.flush();
			fw.close();

			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}