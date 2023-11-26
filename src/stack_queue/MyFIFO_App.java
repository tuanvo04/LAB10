package stack_queue;

	

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
	// method stutter that accepts a queue of integers as
	// a parameter and replaces
	// every element of the queue with two copies of that
	// element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		Queue<E> temp = new LinkedList<E>();
		while (!input.isEmpty()) {
			E element = input.remove();
			temp.add(element);
			temp.add(element);

		}
		while (!temp.isEmpty()) {
			input.add(temp.remove());
		}
	}

	// Method mirror that accepts a queue of strings as a
	// parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c] back
	// becomes
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		Stack<E> stack = new Stack<>();
		for (int i = 0; i < input.size(); i++) {
			E element = input.remove();
			stack.push(element);
			input.add(element);
		}
		while (!stack.isEmpty()) {
			E element = stack.pop();
			input.add(element);
		}
	}

	public static void main(String[] args) {
		Queue<Integer> stutt = new LinkedList<>();
		stutt.add(1);
		stutt.add(2);
		stutt.add(3);
		System.out.println("Before: " + stutt);
		stutter(stutt);
		System.out.println("After: " + stutt);
		System.out.println("---------------------------");
		Queue<String> mirr = new LinkedList<>();
		mirr.add("a");
		mirr.add("b");
		mirr.add("c");
		System.out.println("Before: " + mirr);
		mirror(mirr);
		System.out.println("After: " + mirr);
	}
}