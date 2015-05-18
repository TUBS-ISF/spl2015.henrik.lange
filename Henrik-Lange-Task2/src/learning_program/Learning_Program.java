package learning_program;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program for learning different things, with different methods 
 * @author Henrik Lange
 *
 */
public class Learning_Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainingData tr = new TrainingData();
		Learning_material lr = new Learning_material(tr.getDatabank());
		Scanner sc = new Scanner(System.in);
		// tr.importData("Data/Training_Data.txt","default");
		// tr.exportData("Data/Training_Data_export.txt");
		for (String arg : args) {
			if(arg.equals("-ovm")){
				Configuration.oneVsM=true;
				System.out.println("oneVsM activated");
			}else if(arg.equals("-td")){
				Configuration.topDown=true;
				System.out.println("topDown activated");
			}else if(arg.equals("-help")){
				System.out.println("-ovm, oneVsM");
				System.out.println("-td, topDown");
				System.exit(0);
			}
		}
		while (true) {
			int option;
			while (true) {
				try {
					System.out.println("Hauptmenü");
					System.out.println("[1] Lernen");
					System.out.println("[2] Einstellung");
					System.out.println("[3] Beenden");
					option = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					String errStr = sc.next();
					System.out.println("Bitte eine Zahl eingeben, " + errStr
							+ " ist keine!");
					continue;
				}
			}
			if (option == 1) {
				lr.startLearning();
			} else if (option == 2) {
				tr.editDatabank();
			} else {
				break;
			}
		}
	}

}
