package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import app.Callcenter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.*;

import models.Call;
import service.Dispatcher;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK, classes = Callcenter.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:app.properties")
public class CallControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private Dispatcher dispatcherMock;
	
	@Test
	public void attendCallWithParameters() {		
		Mockito.doAnswer(new Answer<Void>() {
		    public Void answer(InvocationOnMock invocation) {
		        Object[] args = invocation.getArguments();
		        System.out.println("called with arguments: " + Arrays.toString(args));
		        return null;
		      }
		  }).when(dispatcherMock).addCallToQueue(any(Call.class));
		 
	    try {
			mvc.perform(get("/call/attend?calls=10")
			  .contentType(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(content().string(containsString("All the calls are been attended")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	@Test
	public void attendCallWithoutParameter() {		
		Mockito.doAnswer(new Answer<Void>() {
		    public Void answer(InvocationOnMock invocation) {
		        Object[] args = invocation.getArguments();
		        System.out.println("called with arguments: " + Arrays.toString(args));
		        return null;
		      }
		  }).when(dispatcherMock).addCallToQueue(any(Call.class));
		 
	    try {
			mvc.perform(get("/call/attend")
			  .contentType(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(content().string(containsString("All the calls are been attended")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	@Test
	public void attendCallWithError() {		
		Mockito.doThrow(new IllegalStateException()).when(dispatcherMock).addCallToQueue(any(Call.class));
		 
	    try {
			mvc.perform(get("/call/attend")
			  .contentType(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(content().string(containsString("Queue full. We only could attend 0 calls of 1")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	@Test
	public void attendCallWithErrorWithParameter() {		
		Mockito.doThrow(new IllegalStateException()).when(dispatcherMock).addCallToQueue(any(Call.class));
		 
	    try {
			mvc.perform(get("/call/attend?calls=10")
			  .contentType(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(content().string(containsString("Queue full. We only could attend 0 calls of 10")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }



}
