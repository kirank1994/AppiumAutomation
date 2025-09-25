package API;

import java.util.ArrayList;
import java.util.Arrays;

public class Practice {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9, 9, 9));
		System.out.println(printAllNumbers(list));
	}

	public static ArrayList<Integer> printAllNumbers(ArrayList<Integer> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) < 9) {
				list.set(i, list.get(i) + 1);
				return list;
			} else {
				list.set(i, 0);
			}
		}
		list.add(0, 1);
		return list;
	}

}