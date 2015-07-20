
public aspect TopDown {
	// TODO Auto-generated aspect
	after(): call(void chooseQuestion()) {
		
		if (Learning_material.options.equals("3")) {
			while (Learning_material.questionCount < Learning_material.databank.size()){
				if(!Learning_material.databank.get(Learning_material.questionCount)
					.isMultiply_choice()){
						Learning_material.questionCount++;
					}
				else{
					break;
				}
			}
			if(Learning_material.questionCount >= Learning_material.databank
					.size()){
				
			}else{
				Learning_material.numberQue = Learning_material.questionCount;
			}
		}else{
			Learning_material.numberQue = Learning_material.questionCount;
		}

	}
}