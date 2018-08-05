package models;

/**
 * Class that represents an Operator of the callcenter
 * @author Jaime Romero
 *
 */
public class Operator extends Employee{
	/**
	 * Constructor of the class
	 * @param id Id of the operator
	 * @param firstName FirstName of the operator
	 * @param lastName LastName of the operator
	 */
	public Operator(Integer id, String firstName, String lastName) {
		super(id, firstName, lastName, Employee.OPERATOR_POSITON);
	}

}
