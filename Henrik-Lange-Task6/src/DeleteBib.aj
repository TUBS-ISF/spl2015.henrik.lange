import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;

public aspect DeleteBib {
	// TODO Auto-generated aspect
	declare precedence: DeleteBib,Export, Files, Individually;
	after(): call(void loadDatabank()){
		TrainingData.deletebib = true;
	}  
	
	
	after(): call(void deleteDatabank()){
		System.out.println("Die Datenbank wird geloescht");
		Learning_material.databank.clear();
		saveDatabank();
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