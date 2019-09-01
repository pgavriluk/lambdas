import java.util.ArrayList;
import java.util.List;

public class CreateEmployees {

	public static List<Employee> getEmployees(){
		Employee pavel = new Employee("Pavel Richman", 30);
		Employee alex = new Employee("Alex Gurik", 40);
		Employee snow = new Employee("Snow White", 35);
		Employee spring = new Employee("Spring Springovich", 99);
		Employee sam = new Employee("Sam Bigman", 50);
		Employee young = new Employee("Young Ung", 20);

		List<Employee> employees = new ArrayList<>();
		employees.add(pavel);
		employees.add(alex);
		employees.add(snow);
		employees.add(spring);
		employees.add(sam);
		employees.add(young);

		return employees;
	}
}
