package interpreter;

/**
 * ADT for the class Identifier
 *
 * @author Manuel & Michael
 * @elements All alphanumeric elements of type Character
 * @structure Linear
 * @domain All rows of 1 or more alphanumeric Characters, starting with a
 *         letter.
 * @constructors Identifier();
 * @PREcondition -
 * @POSTcondition The new Identifier-object contains a non-empty character row.
 **/

public interface IdentifierInterface extends Data<Identifier> {

	/**
	 * Initializes this object.
	 * 
	 * @PREcondition C must be a letter
	 * @POSTcondition Initializes this object with a non-empty character row.
	 **/
	void init(char c);

	/**
	 * Adds Character c to the Character row.
	 * 
	 * @PREcondition Character c is alphanumeric.
	 * @POSTcondition The Character c is added to the Character row and its size
	 *                is 1 more than before.
	 **/
	void add(char c);

	/**
	 * Compares this object to id2.
	 * 
	 * @PREcondition -
	 * @POSTcondition Compares this object's Character row with that of id2 and
	 *                returns int defining their natural ordering. If returns:
	 *                int > 0 ==> this.id < id2 int = 0 ==> this.id == id2 int <
	 *                0 ==> this.id > id2
	 **/
	int compareTo(Identifier id2);

	/**
	 * Returns Character row as String.
	 * 
	 * @PREcondition -
	 * @POSTcondition Parses this object's Character row to a String.
	 **/
	String toString();

	/**
	 * Returns copy of Identifier object.
	 * 
	 * @PREcondition -
	 * @POSTcondition A copy of this object is returned.
	 **/
	Identifier clone();
}
