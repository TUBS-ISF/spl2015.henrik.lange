
public aspect ClozeTest {
	// TODO Auto-generated aspect
	
	after(): call(void loadDatabank()){
		Learning_material.chozeTest= true;
	} 
	
	after(): call(void chozeTestmode()) {
		
		System.out.println("Lückentest");
	}
}