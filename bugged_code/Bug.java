package bugged_code;

import java.util.ArrayList;

public class Bug {
	
	private class Node {
		int data = 0;
		Node next = null;
		
		public Node() {
			data = 0;
			next = null;
		}
	}

	// Bitcamp theme

	// NullPointerException

	// Method that tests if a Linked List is ordered
	public boolean isIncreasingOrder(Node head) {
		boolean result = true;

		Node curr = head;
		while (result && curr != null) {
			if (curr.data > curr.next.data)
				result = false;
			curr = curr.next;
		}

		return result;
	}
	
	// StackOverflowError
	
	public int addNumber(int n) {
		n += 2;
		return addNumber(n + 2);
	}
	
	 
	public static void main(String[] args) {
		// ArrayIndexOutOfBoundsException\
		String[] arr = {"We", "love", "bitcamp"};
		System.out.println(arr[3]);
		
		// ConcurrentModificationException
		ArrayList<String> list = new ArrayList<>();
		list.add("We");
		list.add("love");
		list.add("bitcamp!");
		
		for (String s : list) {
			System.out.println(s);
			list.remove(s);
		}
		
		// Syntax Error
		System.out.println("We love bitcamp!");
		System.out.prinln("Because bitcamp is so much fun");
		System.out.println("Live love bitcamp");
		
		for (int i = 0; i < 2024; i++) 
			System.out.println("Let's get hacking!")							;
			
		
			
		// Uninitialized variable
		int x = 20;
		int y = 24;
		
		if (x + y + z > 0)
			System.out.println("Bitcamp is the best");
		else
			System.out.println("Bitcamp is still the best");
		
	}

	
}
