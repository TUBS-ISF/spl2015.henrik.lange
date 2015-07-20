
public aspect Comp1vsM {
	// TODO Auto-generated aspect
	after(): call(void Engine.compareAnswer()) {
		if (Engine.answer.contains(Engine.givenAnswer)) {
			Engine.isCorrect = true;
		} else {
			Engine.isCorrect = false;
		}
	}
}