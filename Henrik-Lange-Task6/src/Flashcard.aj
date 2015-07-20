
public aspect Flashcard {
	// TODO Auto-generated aspect
	after(): call(void flashcardmode()) {
		Learning_material.flashcard= true;
		System.out.println("Karteikarten");
	}
}