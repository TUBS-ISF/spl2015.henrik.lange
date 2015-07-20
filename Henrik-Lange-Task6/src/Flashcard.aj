public aspect Flashcard {
	// TODO Auto-generated aspect
	after(): call(void loadDatabank()){
		Learning_material.flashcard = true;
	}

	after(): call(void flashcardmode()) {
		System.out.println("Karteikarten");
	}
}