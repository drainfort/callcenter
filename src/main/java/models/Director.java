package models;

/**
 * Class that represents the Director of the callcenter
 * @author Jaime Romero
 *
 */
public class Director extends Employee{
	/**
	 * Constructor of the class
	 * @param id Id of the Director
	 * @param firstName FirstName of the director
	 * @param lastName LastName of the director
	 */
	public Director(Integer id, String firstName, String lastName) {
		super(id, firstName, lastName, Employee.DIRECTOR_POSITON);
	}
	
}
