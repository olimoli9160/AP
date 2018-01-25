package interpreter;

public class List<E extends Data<E>> implements ListInterface<E> {

	private int size;
	private Node current, first, last;

	public List() {
		init();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public ListInterface<E> init() {
		current = first = last = null;
		size = 0;

		return this;
	}

	public int size() {
		return size;
	}

	public ListInterface<E> insert(E d) {
		Node newNode;

		if (isEmpty()) {
			newNode = new Node(d);
			current = first = last = newNode;
		} else {
			find(d);
			if (current.data.compareTo(d) > 0) {
				newNode = new Node(d, current.prior, current);
				if (current == first) {
					current.prior = newNode;
					current = first = newNode;
				} else {
					current.prior.next = newNode;
					current.prior = newNode;
					current = newNode;
				}
			} else {
				newNode = new Node(d, current, current.next);
				if (current == last) {
					current.next = newNode;
					current = last = newNode;
				} else {
					current.next.prior = newNode;
					current.next = newNode;
					current = newNode;
				}
			}
		}
		size++;

		return this;
	}

	public E retrieve() {
		return current.data.clone();
	}

	public ListInterface<E> remove() {
		if (current.prior == null && current.next == null) {
			first = last = current = null;
		} else if (current.prior == null) {
			current.next.prior = null;
			current = current.next;
			first = current;
		} else if (current.next == null) {
			current.prior.next = null;
			current = current.prior;
			last = current;
		} else {
			current.prior.next = current.next;
			current.next.prior = current.prior;
			current = current.next;
		}
		size--;

		return this;
	}

	public boolean find(E d) {
		boolean isEmpty, isInbetween, isListEnd, listStartsBigger;

		isEmpty = (current == null);
		if(isEmpty){
			return false;
		}else{
			isListEnd = (current.next == null);
			isInbetween = (current.data.compareTo(d) < 0 && (!isListEnd && current.next.data.compareTo(d) > 0));
			listStartsBigger = (first.data.compareTo(d) > 0);
		}

		if (listStartsBigger) {
			current = first;
			return false;
		} else if (isEmpty || isInbetween) {
			return false;
		}

		if (current.data.compareTo(d) == 0) {
			return true;
		} else if (current.data.compareTo(d) > 0) {
			goToFirst();
			return find(d);
		} else {
			if (isListEnd) {
				return false;
			}
			goToNext();
			return find(d);
		}
	}

	public boolean goToFirst() {
		if (this.isEmpty()) {

			return false;
		} else {
			current = first;

			return true;
		}
	}

	public boolean goToLast() {
		if (this.isEmpty()) {

			return false;
		} else {
			current = last;

			return true;
		}
	}

	public boolean goToNext() {
		if (this.isEmpty() || current.next == null) {
			return false;
		} else {
			current = current.next;
			return true;
		}
	}

	public boolean goToPrevious() {
		if (this.isEmpty() || current.prior == null) {
			return false;
		} else {
			current = current.prior;
			return true;
		}
	}

	public ListInterface<E> clone() {
		List<E> result = new List<E>();

		goToFirst();
		for (int i = 0; i < size(); i++) {
			result.insert(current.data.clone());
			goToNext();
		}

		return result;
	}

	private class Node {
		E data;
		Node prior, next;

		public Node(E d) {
			this(d, null, null);
		}

		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}

	}
}
