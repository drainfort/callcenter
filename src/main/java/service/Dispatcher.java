package service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Call;
import models.IEmployee;
import properties.AppProperties;


/**
 * Implementation of the Dispatcher
 * @author Jaime Romero
 *
 */
@Service
public class Dispatcher implements IDispatcher{
	/**
	 * Blocking queue for the calls
	 */
	private BlockingQueue<Call> queue;
	/**
	 * Priority Queue for employees
	 */
	private PriorityBlockingQueue<IEmployee> employesQueue = new PriorityBlockingQueue<IEmployee>();
	
	/**
	 * Constructor of the class
	 */
	@Autowired
	public Dispatcher(AppProperties app) {
		queue = new LinkedBlockingDeque<Call>(app.getQueueSize());
		startProcesing();
	}
		
	/**
	 * Method that starts the processing of the queue
	 */
	public void startProcesing() {
		Thread thread = new Thread(() -> {
		    while (true) {
		        try {
		            Call call = this.queue.take();
		            dispatchCall(call);
		        } 
		        catch (InterruptedException e) { 
		            e.printStackTrace();
		        }
		    }
		});
		thread.start();
	}
	/**
	 * Add call to queue
	 * @param calls Call to add
	 */	
	public void addCallToQueue(Call call) throws IllegalStateException{
		queue.add(call);
	}
	
	/**
	 * Method to dispatch a call
	 * @param call Call that is going to be processed
	 */
	public void dispatchCall (Call call) throws InterruptedException{
		IEmployee employee = employesQueue.take();
		Thread thread = new Thread(() -> {
	        try {
	            processCall(call, employee);
	        } 
	        catch (Exception e) { 
	            e.printStackTrace();
	        }
		});
		thread.start();	
	}
	
	/**
	 * Method to process the call
	 * @param call Call to process
	 * @param employee Employee that is going to process the call
	 */
	private void processCall(Call call, IEmployee employee) {
		try {
			System.out.println(call.getDuration()+ " "+employee.getName());
			employee.setBusy(true);
			TimeUnit.SECONDS.sleep(call.getDuration());
			employee.setBusy(false);
			employesQueue.add(employee);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add list of employees to queue
	 * @param employees List of employees to add 
	 */
	public void addEmployees(List<IEmployee> employees) {
		employesQueue.addAll(employees);
	}

}
