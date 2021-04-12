package ro.ase.acs.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ro.ase.acs.classes.Sum;
import ro.ase.acs.interfaces.BinaryOperator;
import ro.ase.acs.interfaces.Displayable;
import ro.ase.acs.interfaces.Printable;

public class Main {
	
	private static long cube(int x) {
		return (long)Math.pow(x, 3);
	}

	public static void main(String[] args) {
		BinaryOperator op = new Sum();
		
		double value = op.operate(5, 3);
		System.out.println(value);
		
		op = new BinaryOperator() {
			
			@Override
			public double operate(double op1, double op2) {
				return op1 - op2;
			}
		};
		
		System.out.println(new Sum().operate(4, 5));
		
		value = op.operate(5, 3);
		System.out.println(value);
		
		op = (op1, op2) -> op1 * op2;
		value = op.operate(5, 3);
		System.out.println(value);
		
		op = (x, y) -> {
			double sum = x + y;
			return sum / 2;
		};
		
		value = op.operate(5, 3);
		System.out.println(value);
		
		Displayable d = () -> System.out.println("Hello, World!");
		d.display();
		
		Printable p = s -> System.out.println(s);
		p.print("Hi!");
		
		List<Integer> list = Arrays.asList(3, 1, 2, 4, 1, 5, 7, 9, 8);
		long count = list.stream().filter(x -> x % 2 == 0).count();
		System.out.println(count);
		
		List<Integer> subList = list.stream()
				.filter(e -> e < 8).sorted().distinct()
				.collect(Collectors.toList());
		for(Integer x : subList) {
			System.out.println(x);
		}
		
		List<String> strings = 
				Arrays.asList("a", "ab", "bc", "abc", "bca", "b");
		strings.stream().filter(s -> s.startsWith("a"))
			.forEach(s -> System.out.println(s));
		
		String result = strings.stream()
				.filter(s -> s.length() > 2).sorted()
				.collect(Collectors.joining(", "));
		System.out.println(result);
		
		list.stream().distinct().map(Main::cube)
			.sorted().forEach(System.out::println);
		
		strings.parallelStream()
		.filter(s -> s.contains("a")).forEach(System.out::println);
		
		Random rand = new Random();
		System.out.println(rand.nextInt(6));
		
		rand.ints().limit(5).forEach(System.out::println);
	}

}
