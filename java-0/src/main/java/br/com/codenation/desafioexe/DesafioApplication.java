package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fibonacciNumbers = new ArrayList<>();
		fibonacciNumbers.add(0);
		fibonacciNumbers.add(1);
		for (int i = 0; fibonacciNumbers.get(fibonacciNumbers.size()-1) < 377; i++) {
			Integer number;
			number = fibonacciNumbers.get(i) + fibonacciNumbers.get(i+1);
			fibonacciNumbers.add(number);
		}
		return fibonacciNumbers;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}