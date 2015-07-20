public aspect MultiplyChoice {
	// TODO Auto-generated aspect
	declare precedence: MultiplyChoice, ChoiceForTheTheme,TopDown, Random;
	after(): call(void loadDatabank()){
		Learning_material.multiplyChoice = true;
	}
	after(): call(void MultiplyChoiceTestmode()) {
		System.out.println("Lückentest");
	}
}