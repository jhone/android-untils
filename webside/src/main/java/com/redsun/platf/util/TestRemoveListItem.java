package com.redsun.platf.util;

import java.util.ArrayList;
import java.util.List;

import com.redsun.platf.entity.account.User;

public class TestRemoveListItem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<User> results = new ArrayList<User>();
		int length = 10;
		for (int j = length; j > 0; j--) {

			User s = new User();
			s.setName("a" + j);

			results.add(s);
		}

		// Integer[] remove=new Integer[results.size()];

		for (int j = length - 1; j > -1; j--) {
			User s = (User) results.get(j);
			// if (s.getName().equals(person.getName())) {
			System.out.println(j + "searched:" + s);
			results.remove(j);
		}
	}

	// Collections.iteratorBinarySearch(results, person);
	// for (Person s : results) {
	// if (s.getName().equals(person.getName())) {
	// System.out.println("searched:" + s);
	// results.remove(s);
	// }
	// }

}
