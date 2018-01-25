package interpreter;

/**
 * ADT for the class Set
 *
 * @author Manuel & Michael
 * @elements Elements of type <Key, Value>.
 * @structure None
 * @domain All elements of type <Key, Value>.
 * @constructors Set();
 * @PREcondition -
 * @POSTcondition The new Set-object contains only the empty set.
 **/

public interface HashTableInterface<Key extends Data<Key>, Value extends Clonable<Value>>
		extends Clonable<HashTableInterface<Key, Value>> {

	/**
	 * Initializes this object.
	 * 
	 * @PREcondition -
	 * @POSTcondition Initializes this object to an empty Hash Table.
	 **/
	void init();

	/**
	 * Checks if a Key is in the table
	 * 
	 * @PREcondition -
	 * @POSTcondition True: Key is found in table False: Key is not found in
	 *                table
	 **/
	boolean contains(Key k);

	/**
	 * Adds new entry to Hash Table
	 * 
	 * @PREcondition New key must not already exist in the Table.
	 * @POSTcondition <Key, Value> is in the Table.
	 **/
	HashTableInterface<Key, Value> add(Key k, Value v);

	/**
	 * Hashes key to set
	 * 
	 * @PREcondition Key must exist in Table
	 * @POSTcondition Value corresponding to key is returned
	 **/
	Value getValue(Key k);

	/**
	 * Removes Key-Value pair from table
	 * 
	 * @PREcondition Key must exist in Table
	 * @POSTcondition <Key, Value> is not in the Table
	 **/
	HashTableInterface<Key, Value> remove(Key k);

	/**
	 * Returns a clone of Hash Table
	 * 
	 * @PREcondition -
	 * @POSTcondition A copy of this Hash Table is returned
	 **/
	HashTableInterface<Key, Value> clone();

}
