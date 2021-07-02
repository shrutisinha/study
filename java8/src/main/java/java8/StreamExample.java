package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Comparator;
import java.util.function.*;
import java.util.stream.*;
import java.lang.*;

 class StreamExample {

	public static void main(String[] args) {
		
		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) myList.add(i);
		
		//sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		
		//parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();
		
		//using lambda with Stream API, filter example
		Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
		//using lambda in forEach
		highNums.forEach(p -> System.out.println("High Nums parallel="+p));
		
		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
		highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));
		
		
		List<String> vowels = Stream.of("a", "e", "i", "o", "u")
				.collect(Collectors.toCollection(ArrayList::new)); //constructor reference

		// sequential stream - nothing to combine
		//(x,y) -> x.append(y) can be replaced by StringBuilder::append
		StringBuilder result = vowels.stream().collect(StringBuilder::new, (x, y) -> x.append(y),
				(a, b) -> a.append(",").append(b));
		System.out.println(result.toString());

		// parallel stream - combiner is combining partial results
		StringBuilder result1 = vowels.parallelStream().collect(StringBuilder::new, StringBuilder::append,
				(a, b) -> a.append(",").append(b));
		System.out.println(result1.toString());

		String result2 = vowels.parallelStream()
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		System.out.println(result2);


		List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6)
				.collect(Collectors.toCollection(ArrayList::new));

		//collect() to list
		List<Integer> evenNumbers = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
		System.out.println(evenNumbers);

		//collect() to set
		Set<Integer> oddNumbers = numbers.parallelStream().filter(x -> x % 2 != 0).collect(Collectors.toSet());
		System.out.println(oddNumbers);

		//collect() to map
		Map<Integer, String> mapOddNumbers = numbers.parallelStream().filter(x -> x % 2 != 0)
				.collect(Collectors.toMap(Function.identity(), String::valueOf));
		System.out.println(mapOddNumbers);

		System.out.println("IS 97 PRIME?" + isPrime(97));

		//passing behaviour to method
		//sum of all numbers
		System.out.println(sumWithCondition(numbers, n -> true));
		//sum of all even numbers
		System.out.println(sumWithCondition(numbers, i -> i%2==0));
		//sum of all numbers greater than 5
		System.out.println(sumWithCondition(numbers, i -> i>5));

		//Laziness with stream api
		System.out.println("Square of largest odd: "+ findSquareOfMaxOdd(numbers));

		//Functional constructor
		// Use the City's constructor method reference to create a default constructor reference.
		Supplier<City> defaultConstructor = City::new;

		// Use the City's constructor method reference to create a two-parameter constructor reference.
		BiFunction<String, String, City> twoParameterConstructor = City::new;

		City dc = twoParameterConstructor.apply("Washington, D.C.", "DC");


		//Functional setter
		// Use the City's method references and assign them to biconsumers
		BiConsumer<City, String> setNameBiConsumer = City::setName;
		BiConsumer<City, String> setCodeBiConsumer = City::setCode;

		City ny = defaultConstructor.get();
		setNameBiConsumer.accept(ny, "New York");
		setCodeBiConsumer.accept(ny, "NY");

		//Functional getter
		// Use the City's method references and assign them to functions
		Function<City, String> getNameFunction = City::getName;
		Function<City, String> getCodeFunction = City::getCode;

		System.out.println("The code for "
				+ getNameFunction.apply(ny)
				+ " is "
				+ getCodeFunction.apply(ny));

		System.out.println("The code for "
				+ getNameFunction.apply(dc)
				+ " is "
				+ getCodeFunction.apply(dc));

	}

	//find prime number using lambda
	public static boolean isPrime(int number) {
		IntPredicate isDivisible = divisor -> number % divisor == 0;
		return number > 1
				&& IntStream.range(2, number).noneMatch(
						isDivisible
		);
	}

	//sum numbers in a list if they match a given criteria
	public static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {
		return numbers.parallelStream()
				.filter(predicate)
				.mapToInt(i -> i)
				.sum();
	 }

	 //lazy implementation
	 public static int findSquareOfMaxOdd(List<Integer> numbers) {
		 return numbers.stream()
				 .filter(StreamExample::isOdd) 		//Predicate is functional interface and
				 .filter(StreamExample::isGreaterThan3)	// we are using lambdas (method reference) to initialize it
				 .filter(StreamExample::isLessThan11)	// rather than anonymous inner classes
				 .max(Comparator.naturalOrder())
				 .map(i -> i * i)
				 .get();
	 }

	 public static boolean isOdd(int i) {
		 return i % 2 != 0;
	 }

	 public static boolean isGreaterThan3(int i){
		 return i > 3;
	 }

	 public static boolean isLessThan11(int i){
		 return i < 11;
	 }
}


