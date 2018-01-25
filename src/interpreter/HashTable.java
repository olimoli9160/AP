package interpreter;

public class HashTable<Key extends Data<Key>, Value extends Clonable<Value>> implements HashTableInterface<Key, Value> {
	List<Variable> variables;

	public HashTable() {
		init();
	}

	public void init() {
		variables = new List<Variable>();
	}

	public boolean isEmpty() {
		return variables.isEmpty();
	}

	public boolean contains(Key k) {
		if (variables.find(new Variable(k, null))) {
			return true;
		} else {
			return false;
		}
	}

	public HashTableInterface<Key, Value> add(Key k, Value v) {
		if (!isEmpty() && contains(k)) {
			remove(k);
		}

		variables.insert(new Variable(k, v));

		return this;
	}

	public Value getValue(Key k) {
		if (variables.find(new Variable(k, null))) {
			Variable var = variables.retrieve();

			return var.getValue();
		}

		return null;
	}

	public HashTableInterface<Key, Value> remove(Key k) {
		if (variables.find(new Variable(k, null))) {
			variables.remove();
		}

		return this;
	}

	public HashTableInterface<Key, Value> clone() {
		HashTable<Key, Value> result = new HashTable<Key, Value>();
		result.variables = (List<Variable>) this.variables.clone();

		return result;
	}

	private class Variable implements Data<Variable> {
		private Key k;
		private Value v;

		public Variable() {
			init();
		}

		public Variable(Key k, Value v) {
			this.k = k;
			this.v = v;
		}

		private void init() {
			k = null;
			v = null;
		}

		private Value getValue() {
			return v.clone();
		}

		private void update(Value v) {
			this.v = v;
		}

		public int compareTo(Variable var2) {
			return this.k.compareTo(var2.k);
		}

		public Variable clone() {
			return new Variable(k.clone(), v.clone());
		}
	}

}
