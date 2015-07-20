public aspect Random {
	// TODO Auto-generated aspect
	after(): call(void chooseQuestion()) {
		Learning_material.numberQue = Engine.rand
				.nextInt(Learning_material.databank.size());
		if (Learning_material.options.equals("3")) {
			while (!Learning_material.databank.get(Learning_material.numberQue)
					.isMultiply_choice()) {
				Learning_material.numberQue = Engine.rand
						.nextInt(Learning_material.databank.size());

			}
		}

	}
}