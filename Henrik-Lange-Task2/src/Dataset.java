import java.util.ArrayList;

/**
 * 
 * @author Henrik's
 * Datatyp to store all parameters for one dataset
 * question: The question which should be asked
 * answer: The correct answer(s)
 * points: opional, if the programm need it
 *  
 *
 */
class Dataset{
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private int points=-1;
	Dataset(String question, ArrayList<String> answer){
		this.question = question;
		this.answer = answer;
	}
	
	Dataset(String question, ArrayList<String> answer, int points){
		this.question = question;
		this.answer.addAll(answer) ;
		this.points = points;
	}
	/**
	 * 
	 * @return the value of question
	 */
	String getQuestion(){
		return question;
	}
	/**
	 * 
	 * @return the arraylist for answer
	 */
	ArrayList<String> getAnswer(){
		return answer;
	}
	
	/**
	 * 
	 * @return the value of points, if not set the return is 0
	 */
	int getPoints(){
		if(points <= 0){
			return 0;
		}else{
			return points;
		}
	}
	public String toString(){
		return "Question: "+question + " Answer: " + answer + " Points: " +getPoints();
	}
}