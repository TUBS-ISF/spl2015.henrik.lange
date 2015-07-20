
public aspect ClozeTest {
	// TODO Auto-generated aspect
	after(): call(void chozeTestmode()) {
		Learning_material.chozeTest= true;
		System.out.println("Lückentest");
	}
}