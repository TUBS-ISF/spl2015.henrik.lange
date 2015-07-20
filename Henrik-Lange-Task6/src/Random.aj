
public aspect Random {
	// TODO Auto-generated aspect
	after(): call(void chooseQuestion()) {
		Learning_material.numberQue = Engine.rand
				.nextInt(Learning_material.databank.size());
		while (!Learning_material.databank.get(Learning_material.numberQue).isMultiply_choice()) {
			Learning_material.numberQue = Engine.rand
					.nextInt(Learning_material.databank.size());
			
		}

	}
}