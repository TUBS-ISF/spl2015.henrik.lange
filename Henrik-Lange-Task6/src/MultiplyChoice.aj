public aspect MultiplyChoice {
	// TODO Auto-generated aspect
	declare precedence: MultiplyChoice, ChoiceForTheTheme,TopDown, Random;

	after(): call(void MultiplyChoiceTestmode()) {
		Learning_material.multiplyChoice = true;
		System.out.println("Lückentest");
	}
}