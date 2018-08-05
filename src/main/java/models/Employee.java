package models;

/**
 * Abstract class for a employee of the call center
 * @author Jaime Romero
 *
 */
public abstract class Employee implements IEmployee, Comparable<IEmployee>{
	/**
	 * Constant for the position of director
	 */
	public final static Integer DIRECTOR_POSITON = 2;
	/**
	 * Constant for the position of supervisor
	 */
	public final static Integer SUPERVISOR_POSITON = 1;
	/**
	 * Constant for the position of operator
	 */
	public final static Integer OPERATOR_POSITON = 0;
	/**
	 * FirstName of the employee
	 */
	private String firstName;
	/**
	 * LastName of the employee
	 */
	private String lastName;
	/**
	 * Position of the employee
	 */
	private Integer position;
	/**
	 * Id of the employee
	 */
	private Integer id;
	/**
	 * Attribute to check if the employee is busy
	 */
	private Boolean busy;
	
	/**
	 * Constructor of the class
	 * @param id Id of the employee
	 * @param firstName FirstName of the employee
	 * @param lastName LastName of the employee
	 * @param position Position of the employee
	 */
	public Employee(Integer id, String firstName, String lastName, Integer position) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.busy = false;
	}
	
	public Boolean isBusy() {
		return busy;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return this.firstName + " "+ this.lastName;
	}
	
	public Integer getPosition() {
		return this.position;
	}
	
	public void setBusy(Boolean busy) {
		this.busy = busy;
	}
	
	@Override
	public int compareTo(IEmployee employee) {
		int a = 0;
		if(getPosition().equals(employee.getPosition())){
			a = 0;
		}
		else {
			if(getPosition()< employee.getPosition()) {
				a = -1;
			}
			else if(getPosition()> employee.getPosition()) {
				a = 1;
			}
		}
		return a;
	}

}
