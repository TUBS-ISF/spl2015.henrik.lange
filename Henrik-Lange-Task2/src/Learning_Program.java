
public class Learning_Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainingData tr = new TrainingData("Data/Training_Data.txt");
		for(int i = 0;i<tr.getDatabank().size();i++){
			System.out.print(tr.getDatabank().get(i));
			System.out.println();
		}
		tr.exportData("Data/Training_Data_export.txt");

	}

}