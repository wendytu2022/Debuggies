package bugged_code;

public class Bug {

	// Bitcamp theme

	// Null Pointer Exception

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
	
	
}
