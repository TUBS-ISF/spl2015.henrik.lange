

import java.util.ArrayList;
/**
 * Profile class for the user
 * @author Henrik Lange
 *
 */
public class Profile {
	private int id; // indetification of a user
	private String username; // choosen username
	private int points; // scored points
	private ArrayList<Integer> theme; // store the ids of the choosen themes
	private ArrayList<Integer> achievments; // store the finished achievments
	private int wrongAnswers;
	private int rightAnswers;

	public Profile(int id, String username) {
		this.id = id;
		this.username = username;
		wrongAnswers = 0;
		rightAnswers = 0;
		points = 0;
		theme = new ArrayList<Integer>();
		achievments = new ArrayList<Integer>();
	}

	/**
	 * Return the userid
	 * 
	 * @return int id
	 */
	int getId() {
		return id;
	}

	/**
	 * Return the user name
	 * 
	 * @return String username
	 */
	String getUsername() {
		return username;
	}

	/**
	 * Change the username
	 * 
	 * @param username
	 */
	void setUsername(String username) {
		this.username = username;
	}

	/**
	 * add points
	 * 
	 * @param points
	 */
	void addPoints(int points) {
		this.points = +points;
	}

	/**
	 * subtract points
	 * 
	 * @param points
	 */
	void subPoints(int points) {
		this.points = -points;
	}
	/**
	 * the amount of points
	 * @return points
	 */
	int getPoints(){
		return points;
	}

	/**
	 * Deliver the Arraylist for themes of this user
	 * 
	 * @return theme
	 */
	ArrayList<Integer> getThemes() {
		return theme;
	}

	/**
	 * Deliver the finished achievments
	 * 
	 * @return achievments
	 */
	ArrayList<Integer> getAchievments() {
		return achievments;
	}

	/**
	 * The amount of wrong answers
	 * 
	 * @return
	 */
	int getCountWrongAnswer() {
		return wrongAnswers;
	}

	/**
	 * The amount of right answers
	 * 
	 * @return
	 */
	int getCountRightAnswer() {
		return rightAnswers;
	}

}
