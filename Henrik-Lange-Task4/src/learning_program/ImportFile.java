package learning_program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ImportFile implements ImportPlugin{
	private Scanner sc = new Scanner(System.in);
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private ArrayList<String> wrongAnswer = new ArrayList<String>();
	private StringTokenizer lineReader;
	private StringTokenizer answerReader;
	@Override
	public void importData(ArrayList<Dataset> databank) {
		// TODO Auto-generated method stub
		System.out.println("Import Datei einlesen");
		System.out.println("Geben sie den Pfad zur Datei an: ");
		String path = sc.next();
		System.out.println("Geben sie das Thema an: ");
		String theme = sc.next();
		try {
			// initiate all reader
			FileReader datareader = new FileReader(path);
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
		saveDatabank(databank);
	}

}
