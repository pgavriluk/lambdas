import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMaps {

	public static void main(String[] args) {

		List<Employee> employees = CreateEmployees.getEmployees();

		Department hr = new Department("Human Resources");
		hr.addEmployee(employees.get(0));
		hr.addEmployee(employees.get(1));
		hr.addEmployee(employees.get(2));

		Department accounting = new Department("Accounting");
		hr.addEmployee(employees.get(3));
		hr.addEmployee(employees.get(4));
		hr.addEmployee(employees.get(5));

		List<Department> departments = new ArrayList<>();
		departments.add(hr);
		departments.add(accounting);

		departments.stream()
				.flatMap(department -> department.getEmployees().stream())
				.forEach(System.out::println);

		List<String> bingoNumbers = Arrays.asList(
				"N40", "N36",
				"B12", "B6",
				"G53", "G49", "G60", "G50", "g64",
				"I26", "I17", "I29",
				"071");

		List<String> sortedNumbers = bingoNumbers
				.stream()
				.map(String::toUpperCase)
				.filter(s -> s.startsWith("G"))
				.sorted()
				.collect(Collectors.toList());

		sortedNumbers.forEach(System.out::println);

		System.out.println("=========================");

		List<String> anotherSortedNumbers = bingoNumbers
				.stream()
				.map(String::toUpperCase)
				.filter(s -> s.startsWith("G"))
				.sorted()
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		anotherSortedNumbers.forEach(System.out::println);

		Map<Integer,List<Employee>> groupedByAge = departments
				.stream()
				.flatMap(department -> department.getEmployees().stream())
				.collect(Collectors.groupingBy(employee -> employee.getAge()));

		System.out.println("The youngest employee is: ");
		departments
				.stream()
				.flatMap(department -> department.getEmployees().stream())
				.reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
				.ifPresent(System.out::println);

		Stream.of("AA", "BBB", "CCCC", "D", "WWW")
				.filter(s -> {
					System.out.println(s);
					return s.length() == 3;
				})
				.count();

	}
}
