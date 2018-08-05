package controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Call;
import models.IEmployee;
import service.IDispatcher;

/**
 * Controller for the calls
 * @author Jaime Romero
 *
 */
@RestController
public class CallController {
	
	/**
	 * Dispatcher of the calls
	 */
	@Autowired
    IDispatcher dispatcher;
	
	/**
	 * Method to respond the index request
	 * @return Response of the index
	 */
	@RequestMapping("/")
    public String index() {
        return "Welcome to the callcenter";
    }
	
	/**
	 * Method to attend a call in the call center
	 * @return Response of the call
	 */
	@RequestMapping("/call/attend")
    public String attendCall(@RequestParam("calls") Optional<Integer> size) {	
		Integer calls = 1;
		if(size.isPresent()  && size.get() > 0)
			calls = size.get();
		Random rand = new Random();
		for(int i = 0; i< calls;i++) {
			try {
				Integer  seconds = rand.nextInt(5) + 5;
				this.dispatcher.addCallToQueue(new Call(seconds));
			}
			catch (IllegalStateException exception) {
				return "Queue full. We only could attend "+i+" calls of "+calls ;
			}
		}
		
        return "All the calls are been attended";
    }

	/**
	 * Method to add employees to queue
	 * @param employees List of employees 
	 */
	public void addEmployees(List<IEmployee> employees) {
		dispatcher.addEmployees(employees);
	}

}
