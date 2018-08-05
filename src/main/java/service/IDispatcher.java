package service;

import java.util.List;

import models.Call;
import models.IEmployee;

/**
 * Interface for the dispatcher of calls of the callcenter
 * @author Jaime Romero
 *
 */
public interface IDispatcher {
	/**
	 * Method to dispatch a call
	 * @param call Call that is going to be processed
	 */
	public void dispatchCall (Call call) throws InterruptedException;
	/**
	 * Add call to queue
	 * @param calls Call to add
	 */
	public void addCallToQueue(Call call) throws IllegalStateException;
	/**
	 * Add list of employees to queue
	 * @param employees List of employees to add 
	 */
	public void addEmployees(List<IEmployee> employees);
	/**
	 * Get number of processed Calls
	 * @return processed calls
	 */
	public int getProcessedCalls();

}
