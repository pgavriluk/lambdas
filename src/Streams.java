import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {

		List<String> bingoNumbers = Arrays.asList(
				"N40", "N36",
				"B12", "B6",
				"G53", "G49", "G60", "G50", "g64",
				"I26", "I17", "I29",
				"071");


		// old way
		List<String> gNumbers = new ArrayList<>();

		System.out.println("Not sorted: ");
		bingoNumbers.forEach(number -> {
			if (number.toUpperCase().startsWith("G")) {
				gNumbers.add(number);
				System.out.println(number);
			}
		});

		gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
		System.out.println("Sorted: ");
		gNumbers.forEach(number -> System.out.println(number));

		// stream
		System.out.println("Sorted using stream");
		bingoNumbers
				.stream()
				.map(String::toUpperCase)
				.filter(s -> s.startsWith("G"))
				.sorted()
				.forEach(System.out::println);


		Stream<String> stringStream1 = Stream.of("I26", "I17", "I29", "O71");
		Stream<String> stringStream2 = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
		Stream<String> concatStream = Stream.concat(stringStream1, stringStream2);
		//System.out.println("Total number of stream items is : " + concatStream.count());
		System.out.println("===============================");
		System.out.println("Total number of unique stream items is : "
				+ concatStream
				.distinct()
				.peek(System.out::println)
				.count());

	}
}
