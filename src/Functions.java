import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class Functions {

	public static void main(String[] args) {

		List<Employee> employees = CreateEmployees.getEmployees();

		// Functions
		Function<Employee,String> getLastName = (Employee employee) -> {
			return employee.getName().substring(employee.getName().indexOf(" ") + 1);
		};

		String lastName = getLastName.apply(employees.get(2));
		System.out.println("Last name of the third employee is : " + lastName);

		Function<Employee,String> getFirstName = (Employee employee) -> {
			return employee.getName().substring(0, employee.getName().indexOf(" "));
		};

		for (Employee employee : employees) {
			System.out.println("First Name is: " + getAName(getFirstName, employee));
			System.out.println("Last Name is: " + getAName(getLastName, employee));
		}

		Function<Employee,String> upperCase = employee -> employee.getName().toUpperCase();
		Function<String,String> firstName = name -> name.substring(0, name.indexOf(" "));

		Function chainedFunction = upperCase.andThen(firstName);

		for (Employee employee : employees) {
			System.out.println("Uppercase First Name is: " + chainedFunction.apply(employee));
		}

		BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
			return name.concat(" " + employee.getAge());
		};

		for (Employee employee : employees) {
			String upperName = upperCase.apply(employee);
			System.out.println("Uppercase First Name and Age: " + concatAge.apply(upperName, employee));
		}

		IntUnaryOperator incBy5 = i -> i + 5;
		System.out.println(incBy5.applyAsInt(10));


	}

	private static String getAName(Function<Employee,String> getName, Employee employee) {
		return getName.apply(employee);
	}
}
