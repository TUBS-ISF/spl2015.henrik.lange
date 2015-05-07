import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Henrik's Load the Database
 *
 */
class TrainingData {
	private ArrayList<Dataset> databank = new ArrayList<Dataset>();
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private int points;
	private StringTokenizer lineReader;
	private StringTokenizer answerReader;

	TrainingData(String filename) {
		importData(filename);
	}

	ArrayList<Dataset> getDatabank() {
		return databank;
	}

	void importData(String filename) {
		try {
			FileReader datareader = new FileReader(filename);
			BufferedReader br = new BufferedReader(datareader);
			String data = br.readLine();
			while (data != null) {
				answer.clear();
				lineReader = new StringTokenizer(data, ";");
				question = lineReader.nextToken();
				answerReader = new StringTokenizer(lineReader.nextToken(), "|");
				while (answerReader.countTokens() > 0) {
					answer.add(answerReader.nextToken());
				}
				if (lineReader.countTokens() == 0) {
					databank.add(new Dataset(question, answer));
				} else if (lineReader.countTokens() == 1) {
					databank.add(new Dataset(question, answer, Integer
							.parseInt(lineReader.nextToken())));
				} else {
					System.out.println("unsupported number of entrys");
				}
				data = br.readLine();
			}

		} catch (IOException nofile) {
			System.out.println(nofile);

		}
	}

	void exportData(String destination) {
		Scanner sc = new Scanner(System.in);

		try {
			File file = new File(destination);
			FileWriter fw = null;

			if (file.exists()) {
				System.out.println("Die Datei existiert schon, soll sie gelöscht [d], ergänzt [e] oder der Vorgang abgebrochen [a] werden?");
				String option = sc.next(); 
				if(option.equals("d")){
					file.delete();
				}else if(option.equals("e")){
					
				}else if(option.equals("a")){
					System.out.println("Die Operation wird abgebrochen");
					return;
				}
				
			}
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
				pw.println(databank.get(i).getPoints());
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