

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public interface ImportPlugin {
	void importData(ArrayList<Dataset> databank);
	
	

}
