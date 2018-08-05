package models;

/**
 * Class that represents a call 
 * @author Jaime Romero
 *
 */
public class Call {
	/**
	 * Duration of the call
	 */
	private Integer duration;
	
	/***
	 * Constructor of the class
	 * @param duration Duration of the call
	 */
	public Call (Integer duration) {
		this.duration = duration;
	}
	
	/**
	 * Method that return the duration of the call
	 * @return Duration of the call
	 */
	public Integer getDuration() {
		return this.duration;
	}

}
