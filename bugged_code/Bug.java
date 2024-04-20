package bugged_code;

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
	}

	
}
