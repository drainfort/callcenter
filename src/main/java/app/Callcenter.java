package app;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.CallController;
import models.IEmployee;
import models.RawData;

/**
 * Main class of the project
 * @author Jaime Romero
 *
 */
@SpringBootApplication
@ComponentScan({"controllers","service", "properties"})
public class Callcenter {

    public static void main(String[] args) {
        SpringApplication.run(Callcenter.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CallController controller) {
        return args -> {
        	//Reads json data of employees
        	ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<RawData>> typeReference = new TypeReference<List<RawData>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/operator.json");
			try {
				List<RawData> rawData = mapper.readValue(inputStream,typeReference);
				List<IEmployee> employees = new ArrayList<IEmployee>();
				for(RawData rawEmployee:  rawData) {
					employees.add(rawEmployee.transformEmployee());
				}
				controller.addEmployees(employees);
			} catch (IOException e){
				System.out.println(e.getMessage());
			}
        };
    }

}
