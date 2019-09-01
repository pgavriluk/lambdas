import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println("Printing from the Runnable");
			System.out.println("This is line number 2");
			System.out.format("This is line %d\n", 3);
		}).start();


		List<Employee> employees = CreateEmployees.getEmployees();

		//		Collections.sort(employees, new Comparator<Employee>() {
		//			@Override
		//			public int compare(Employee employee1, Employee employee2) {
		//				return employee1.getName().compareTo(employee2.getName());
		//			}
		//		});


		Collections.sort(employees, Comparator.comparing(Employee::getName));

		for (Employee employee : employees) {
			System.out.println("Employee name is : " + employee.getName());
		}

		//		String someString = doSomeStringStuff(new UpperConcat() {
		//			@Override
		//			public String upperAndConcat(String str1, String str2) {
		//				return str1.toUpperCase() + str2.toUpperCase();
		//			}
		//		}, employees.get(0).getName() + " ", employees.get(1).getName());
		//
		//		System.out.println(someString);

		UpperConcat upperConcat = (str1, str2) -> {
			String result = str1.toUpperCase() + " " + str2.toUpperCase();
			return result;
		};

		System.out.println(doSomeStringStuff(upperConcat, employees.get(0).getName(), employees.get(1).getName()));


		employees.forEach(employee -> {
			System.out.println(employee.getName());
			System.out.println(employee.getAge());
		});

		for (Employee employee : employees) {
			System.out.println(employee.getName());
			new Thread(()-> System.out.println(employee.getAge())).start();
		}


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		// Predicates

		printEmployeesByAge(employees, "\nEmployees over 30: ", employee -> employee.getAge()>30);
		printEmployeesByAge(employees, "\nEmployees 30 and under: ", employee -> employee.getAge()<=30);
		printEmployeesByAge(employees, "\nEmployees 25 and under: ", new Predicate<Employee>() {
			@Override
			public boolean test(Employee employee) {
				return employee.getAge() <=25;
			}
		});

		IntPredicate greaterThan15 = i -> i > 15;
		IntPredicate lessThan100 = i -> i < 100;
		System.out.println(greaterThan15.test(10));
		int number = 20;
		System.out.println(greaterThan15.test(number + 5));

		System.out.println(greaterThan15.and(lessThan100).test(50));
		System.out.println(greaterThan15.and(lessThan100).test(200));

		// Suppliers
		Random random = new Random();
		Supplier<Integer> integerSupplier = () -> random.nextInt(1000);
		for(int i =0; i<10; i++){
			System.out.println(integerSupplier.get());
		}


		employees.forEach(employee -> {
			String lastName = employee.getName().substring(employee.getName().indexOf(" ") + 1);
			System.out.println("Last name is: " + lastName);
		});

	}

	public final static String doSomeStringStuff(UpperConcat upperConcat, String str1, String str2) {
		return upperConcat.upperAndConcat(str1, str2);
	}

	private static void printEmployeesByAge(List<Employee> employees, String text, Predicate<Employee> ageCondition){
		System.out.println(text);
		System.out.println("===================");
//		employees.forEach(employee -> {
//			if(employee.getAge()> 30){
//				System.out.println(employee.getName());
//			}
//		});

		for(Employee employee: employees ){
			if(ageCondition.test(employee)){
				System.out.println(employee.getName());
			}
		}
	}

}

interface UpperConcat {
	public String upperAndConcat(String str1, String str2);
}

class NewClass {

	public void printValue() {
		int number = 25;

		Runnable r = () -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("The value is: " + number);
		};

		new Thread(r).start();
	}
}