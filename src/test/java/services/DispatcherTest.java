package services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Call;
import models.IEmployee;
import models.RawData;
import properties.AppProperties;
import service.Dispatcher;
import service.IDispatcher;

@RunWith(SpringRunner.class)
public class DispatcherTest {
	
	
	private IDispatcher dispatcher;
	
	@Before
	public void setUp() {
		AppProperties properties = new AppProperties();
		properties.setQueueSize(10);
		dispatcher = new Dispatcher(properties);
		loadEmployees();
	}
	
	public void loadEmployees() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<RawData>> typeReference = new TypeReference<List<RawData>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/data/operator.json");
		try {
			List<RawData> rawData = mapper.readValue(inputStream,typeReference);
			List<IEmployee> employees = new ArrayList<IEmployee>();
			for(RawData rawEmployee:  rawData) {
				employees.add(rawEmployee.transformEmployee());
			}
			dispatcher.addEmployees(employees);
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestAddCalls_10Calls() {
		for(int i= 0; i< 10; i++ ) {
			dispatcher.addCallToQueue(new Call(1));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
			assertEquals(dispatcher.getProcessedCalls(), 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestAddCalls_11Calls() {	
		try {
			for(int i= 0; i< 11; i++ ) {
				dispatcher.addCallToQueue(new Call(8));
			}
            fail(); 
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is("Deque full"));
        }
	}

}
