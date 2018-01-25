package interpreter;

/**
 * ADT for the class Set
 *
 * @author Manuel & Michael
 * @elements All elements of type Character.
 * @structure None
 * @domain All rows of numeric Characters.
 * @constructors Number();
 * @PREcondition -
 * @POSTcondition The new Number-object contains a non-empty character row.
 **/

public interface NumberInterface extends Data<Number> {

	/**
	 * Initializes this object.
	 * 
	 * @PREcondition C must be numeric.
	 * @POSTcondition Initializes this object with a non-empty character row.
	 **/
	void init(char c);

	/**
	 * Adds Character c to the Character row.
	 * 
	 * @PREcondition Character c is zero or a positive integer.
	 * @POSTcondition The Character c is added to the Character row and its size
	 *                is 1 more than before.
	 **/
	void add(char c);

	/**
	 * Compares this object to id2.
	 * 
	 * @PREcondition -
	 * @POSTcondition Checks if this object's Character row has the same content
	 *                -in order and size- as n2. True: this object and n2 are
	 *                the same. False: this object and n2 are not the same.
	 **/
	boolean equals(Number n2);

	/**
	 * Returns Character row as String.
	 * 
	 * @PREcondition -
	 * @POSTcondition Parses this object's Character row to a String.
	 **/
	String toString();

	/**
	 * Returns copy of Character row.
	 * 
	 * @PREcondition -
	 * @POSTcondition A copy of the Character row is returned.
	 **/
	Number get();

	/**
	 * Compares this Number object to another number object
	 * 
	 * @PREcondition -
	 * @POSTcondition Compares this object's Character row with that of n2 and
	 *                returns int defining their natural ordering. If returns:
	 *                int > 0 ==> this.n < n2 int = 0 ==> this.n == n2 int < 0
	 *                ==> this.n > n2
	 **/
	int compareTo(Number n2);

	/**
	 * Returns copy of the Number object.
	 * 
	 * @PREcondition -
	 * @POSTcondition A copy of this object is returned.
	 **/
	Number clone();
}
