package models;

/**
 * Maps the employee information of a json file
 * @author Jaime Romero
 *
 */
public class RawData {
	/**
	 * Id of the employee
	 */
	public Integer id;
	/**
	 * First name of the employee
	 */
	public String firstName;
	/**
	 * Last name of the employee
	 */
	public String lastName;
	/**
	 * Position of the employee
	 */
	public Integer position;
	
	/**
	 * Constructor of the class
	 */
	public RawData() {
	}
	
	/**
	 * Method to transform the raw data into a employee
	 * @return Employee
	 */
	public IEmployee transformEmployee() {
		if(this.position == Employee.DIRECTOR_POSITON) {
			return new Director(id, firstName, lastName);
		} else if(this.position == Employee.OPERATOR_POSITON) {
			return new Operator(id, firstName, lastName);
		} else if(this.position == Employee.SUPERVISOR_POSITON) {
			return new Supervisor(id, firstName, lastName);
		} else {
			return null;
		}
	}

}
