package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Basic {

	public static void main(String[] args) {
		
		
		
		//Concepts: filter(), collect()
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> evens = numbers.stream()
		    .filter(n -> n % 2 == 0)
		    .collect(Collectors.toList());
		System.out.println(evens); // [2, 4, 6]

		
		
		
		// Concepts: map(), method reference
		List<String> names = Arrays.asList("nikhil", "anand", "kini");
		List<String> upper = names.stream()
		    .map(String::toUpperCase)
		    .collect(Collectors.toList());
		System.out.println(upper); // [NIKHIL, ANAND, KINI]

		
		
		
		
		//Concepts: mapToInt(), primitive streams (IntStream)
		int sum = numbers.stream()
			    .mapToInt(Integer::intValue)
			    .sum();
		System.out.println(sum); // 21
		
		
		
		
		
		//Concepts: max(), Optional
		Optional<Integer> max = numbers.stream()
			    .max(Integer::compareTo);
		max.ifPresent(System.out::println); // 6
		
		
		
		
		
		//Concepts: filter(), count()
		List<String> name2 = Arrays.asList("Alice", "Bob", "Angela", "David");
		long count = name2.stream()
		    .filter(name -> name.startsWith("A"))
		    .count();
		System.out.println(count); // 2

		
		
		
		//Concepts: sorted()
		List<String> sorted = names.stream()
			    .sorted()
			    .collect(Collectors.toList());
		System.out.println(sorted); // [Alice, Angela, Bob, David]
		
		
		
		
		//Concepts: distinct()
		List<Integer> nums = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
		List<Integer> distinct = nums.stream()
		    .distinct()
		    .collect(Collectors.toList());
		System.out.println(distinct); // [1, 2, 3, 4, 5]

		
		
		

		//Concepts: findFirst()
		Optional<Integer> first = numbers.stream()
			    .filter(n -> n > 3)
			    .findFirst();
		first.ifPresent(System.out::println); // 4
		
		
		
		
		
		//Concepts: Collectors.groupingBy()
		List<String> words = Arrays.asList("one", "two", "three", "four");
		Map<Integer, List<String>> grouped = words.stream()
		    .collect(Collectors.groupingBy(String::length));
		System.out.println(grouped);
		// {3=[one, two], 4=[four], 5=[three]}
		
		
		
		
		//Concepts: average()
		OptionalDouble avg = numbers.stream()
			    .mapToInt(Integer::intValue)
			    .average();
			avg.ifPresent(System.out::println); // 3.5


			
			
		// Concepts: Collectors.joining()
		String result = names.stream()
			    .collect(Collectors.joining(", "));
		System.out.println(result); // Alice, Bob, Angela, David

		
		
		
		
		//Concepts: Collectors.partitioningBy()
		Map<Boolean, List<Integer>> partition = numbers.stream()
			    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
		System.out.println(partition);
			// {false=[1, 3, 5], true=[2, 4, 6]}

			
		
		
		//Concepts: flatMap()
		List<List<Integer>> nested = Arrays.asList(
			    Arrays.asList(1, 2),
			    Arrays.asList(3, 4),
			    Arrays.asList(5)
			);
		List<Integer> flat = nested.stream()
			    .flatMap(List::stream)
			    .collect(Collectors.toList());
		System.out.println(flat); // [1, 2, 3, 4, 5]

		
		
		
		
		//Concepts: reduce()
		int product = numbers.stream()
			    .reduce(1, (a, b) -> a * b);
			System.out.println(product); // 720


	}

}
