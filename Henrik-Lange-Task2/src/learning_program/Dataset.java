package learning_program;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Datatyp to store all parameters for one dataset question:
 *      package learning_program;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Datatyp to store all parameters for one dataset question:
 *         The question which should be asked answer: The correct answer(s)
 *         points: opional, if the programm need it
 * @author Henrik Lange
 * 
 *
 */
class Dataset implements Serializable  {
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private int points = -1;
	private boolean multiply_choice;
	private ArrayList<String> wrongAnswer = new ArrayList<String>();
	private String theme;

	/**
	 * 
	 * @param question The Question
	 * @param answer The answers is a Arraylist
	 * @param points Points for correct answer
	 * @param wrongAnswer Alternative wrong answers, need for multiply choice
	 * @param theme The theme of the questions
	 */
	Dataset(String question, ArrayList<String> answer, int points,
			ArrayList<String> wrongAnswer, String theme) {
		this.question = question;
		this.answer.addAll(answer);
		this.points = points;
		this.wrongAnswer = wrongAnswer;
		this.theme = theme;
		if (wrongAnswer.size() > 0) {
			multiply_choice = true;
		}
	}

	/**
	 * 
	 * @return the value of question
	 */
	String getQuestion() {
		return question;
	}

	/**
	 * 
	 * @return the arraylist for answer
	 */
	ArrayList<String> getAnswer() {
		return answer;
	}

	/**
	 * 
	 * @return the arraylist for the wrong answers
	 */
	ArrayList<String> getWrongAnswers() {
		return wrongAnswer;
	}

	/**
	 * 
	 * @return the value of points, if not set the return is 0
	 */
	int getPoints() {
		if (points <= 0) {
			return 0;
		} else {
			return points;
		}
	}

	public String toString() {
		return "Question: " + question + " Answer: " + answer + " Points: "
				+ getPoints() +" Wrong Answer: "+getWrongAnswers()+" Theme: "+getTheme()+ "\n";
	}

	String getTheme() {
		return theme;
	}

	public boolean isMultiply_choice() {
		return multiply_choice;
	}
	


}   The question which should be asked answer: The correct answer(s)
 *         points: opional, if the programm need it
 * @author Henrik Lange
 * 
 *
 */
class Dataset implements Serializable {
	private String question;
	private ArrayList<String> answer = new ArrayList<String>();
	private int points = -1;
	private boolean multiply_choice;
	private ArrayList<String> wrongAnswer = new ArrayList<String>();
	private String theme;

	/**
	 * 
	 * @param question The Question
	 * @param answer The answers is a Arraylist
	 * @param points Points for correct answer
	 * @param wrongAnswer Alternative wrong answers, need for multiply choice
	 * @param theme The theme of the questions
	 */
	Dataset(String question, ArrayList<String> answer, int points,
			ArrayList<String> wrongAnswer, String theme) {
		this.question = question;
		this.answer.addAll(answer);
		this.points = points;
		this.wrongAnswer = wrongAnswer;
		this.theme = theme;
		if (wrongAnswer.size() > 0) {
			multiply_choice = true;
		}
	}

	/**
	 * 
	 * @return the value of question
	 */
	String getQuestion() {
		return question;
	}

	/**
	 * 
	 * @return the arraylist for answer
	 */
	ArrayList<String> getAnswer() {
		return answer;
	}

	/**
	 * 
	 * @return the arraylist for the wrong answers
	 */
	ArrayList<String> getWrongAnswers() {
		return wrongAnswer;
	}

	/**
	 * 
	 * @return the value of points, if not set the return is 0
	 */
	int getPoints() {
		if (points <= 0) {
			return 0;
		} else {
			return points;
		}
	}

	public String toString() {
		return "Question: " + question + " Answer: " + answer + " Points: "
				+ getPoints() +" Wrong Answer: "+getWrongAnswers()+" Theme: "+getTheme()+ "\n";
	}

	String getTheme() {
		return theme;
	}

	public boolean isMultiply_choice() {
		return multiply_choice;
	}

}