package interview.assignment.patients;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/***
 * @author Shravan Nitta
 */

@SpringBootApplication
@ComponentScan(basePackages = "interview.assignment.patients.*")
public class PatientsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsManagementApplication.class, args);
	}

}
