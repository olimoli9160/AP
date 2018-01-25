package interpreter;

public class Identifier implements IdentifierInterface {
	private static char INIT_CHAR = 'x';

	private StringBuffer id;

	public Identifier() {
		init(INIT_CHAR);
	}

	private int compareCharacters(StringBuffer id2String) {
		for (int i = 0; i < (id2String.length() < id.length() ? id2String.length() : id.length()); i++) {
			if (id.charAt(i) > id2String.charAt(i)) {
				return 1;
			} else if (id.charAt(i) < id2String.charAt(i)) {
				return -1;
			}
		}
		if (id.length() < id2String.length()) {
			return 1;
		} else if (id.length() > id2String.length()) {
			return -1;
		} else {
			return 0;
		}
	}

	private StringBuffer copy(StringBuffer original) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < original.length(); i++) {
			result.append(original.charAt(i));
		}
		return result;
	}

	public void init(char c) {
		if (Character.isLetterOrDigit(c)) {
			id = new StringBuffer(c);
		}
	}

	public void add(char c) {
		if (Character.isLetterOrDigit(c)) {
			id.append(c);
		}
	}

	public String toString() {
		return id.toString();
	}

	public Identifier clone() {
		Identifier result = new Identifier();

		result.id = copy(this.id);

		return result;
	}

	public int compareTo(Identifier id2) {
		return compareCharacters(id2.id);
	}

}
