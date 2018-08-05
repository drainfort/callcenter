package models;

/**
 * Interface of an employee of the callcenter
 * @author Jaime Romero
 *
 */
public interface IEmployee{
	/**
	 * Method to get the id of the employee
	 * @return Id of the employee
	 */
	public Integer getId();
	
	/**
	 * Method to get the name of the employee
	 * @return Name of the employee
	 */
	public String getName();
	
	/**
	 * Method to get the position of the employee
	 * @return Position of the employee
	 */
	public Integer getPosition();
	
	/**
	 * Method to know if the employee is busy
	 * @return True if the employee is busy. False if is not busy. 
	 */
	public Boolean isBusy();
	
	/**
	 * Method to change the busy status to the employee
	 * @param busy New status of busy of the employee
	 */
	public void setBusy(Boolean busy);

}
