package interpreter;

public class Number implements NumberInterface {

	private static final char INIT_CHAR = '0';
	private StringBuffer number;

	public Number() {
		init(INIT_CHAR);
	}

	public Number(char c) {
		init(c);
		add(c);
	}

	public void init(char c) {
		number = new StringBuffer(c);
	}

	public void add(char c) {
		number.append(c);
	}

	public boolean equals(Number n2) {
		return n2.toString() == number.toString();
	}

	public String toString() {
		return number.toString();
	}

	public Number get() {
		return this.clone();
	}

	public Number clone() {
		Number result = new Number();

		result.number = copy(this.number);

		return result;
	}

	private StringBuffer copy(StringBuffer original) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < original.length(); i++) {
			result.append(original.charAt(i));
		}
		return result;
	}

	public int compareTo(Number n2) {
		return compareCharacters(n2.number);
	}

	private int compareCharacters(StringBuffer number2String) {
		int smallerLength = number2String.length() < number.length() ? number2String.length() : number.length();
		if (number.length() < number2String.length()) {
			return -1;
		} else if (number.length() > number2String.length()) {
			return 1;
		} else {
			for (int i = 0; i < smallerLength; i++) {
				if (number.charAt(i) > number2String.charAt(i)) {
					return 1;
				} else if (number.charAt(i) < number2String.charAt(i)) {
					return -1;
				}
			}
		}
		return 0;
	}
}
