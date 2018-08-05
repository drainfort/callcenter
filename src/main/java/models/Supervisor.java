package models;

/**
 * Class that represents a Supervisor of the callcenter
 * @author Jaime Romero
 *
 */
public class Supervisor extends Employee{
	/**
	 * Constructor of the class
	 * @param id Id of the supervisor
	 * @param firstName FirstName of the supervisor
	 * @param lastName LastName of the supervisor
	 */
	public Supervisor(Integer id, String firstName, String lastName) {
		super(id, firstName, lastName, Employee.SUPERVISOR_POSITON);
	}

}
