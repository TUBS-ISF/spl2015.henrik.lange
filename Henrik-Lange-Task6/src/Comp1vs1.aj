

public privileged aspect Comp1vs1 {
	// TODO Auto-generated aspect
	after(): call(void Engine.compareAnswer()) {
		if (Engine.answer.get(0).equals(Engine.givenAnswer)) {
			Engine.isCorrect = true;
		} else {
			Engine.isCorrect = false;
		}
	}

}