package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;

public class StatisticUtil {

	public static int average(int[] elements) {
		int average = 0;
		for (int num : elements) {
			average += num;
		}
		average = average / elements.length;
		return average;
	}

	public static int mode(int[] elements) {
		HashMap<Integer, Integer> numbersMap = new HashMap<>();

		for (int num : elements) {
			int count = numbersMap.getOrDefault(num, 0);
			numbersMap.put(num, count + 1);
		}
		return numbersMap.entrySet()
				.stream()
				.reduce((previous, actual) -> actual = previous.getValue() > actual.getValue() ? previous : actual)
				.map(e -> e.getKey())
				.get();
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		if (elements.length % 2 == 0){
			return (elements[elements.length / 2] + elements[(elements.length / 2) - 1]) / 2;
		}
		return elements[elements.length / 2];
	}
}