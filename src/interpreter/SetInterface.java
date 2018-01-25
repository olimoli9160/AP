package interpreter;

/**
 * ADT for the class Set
 *
 * @author Manuel & Michael
 * @elements Elements of type <E>.
 * @structure None
 * @domain All elements of type <E>.
 * @constructors Set();
 * @PREcondition -
 * @POSTcondition The new Set-object contains only the empty set.
 **/

public interface SetInterface<E extends Data<E>> extends Clonable<Set<E>> {

	/**
	 * Initializes this object.
	 * 
	 * @PREcondition -
	 * @POSTcondition Initializes this object with an empty <E> set.
	 **/
	void init();

	/**
	 * Returns <E>.
	 * 
	 * @PREcondition Set is not empty.
	 * @POSTcondition An <E> is returned from the <E> set.
	 **/
	E get();

	/**
	 * Adds an <E> to the set.
	 * 
	 * @PREcondition The set does not contain d.
	 * @POSTcondition The <E> d is added to set.
	 **/
	SetInterface<E> add(E d);

	/**
	 * Removes an <E> to the set.
	 * 
	 * @PREcondition The set contains d.
	 * @POSTcondition The <E> d is deleted from the set.
	 **/
	SetInterface<E> delete(E d);

	/**
	 * Checks if d2 is contained in this set.
	 * 
	 * @PREcondition -
	 * @POSTcondition Checks if this object's set contains an <E> that has the
	 *                same content -in order and size- as d2. True: an <E> with
	 *                the same content as d2 was found in this set. False: an
	 *                <E> with the same content as d2 was not found in this set.
	 **/
	boolean contains(E d2);

	/**
	 * Checks if this set is empty.
	 * 
	 * @PREcondition -
	 * @POSTcondition Checks if the number of <E>s in this set is 0. True: the
	 *                number of <E>s is 0. False: the number of <E>s is >= 1.
	 **/
	boolean isEmpty();

	/**
	 * Returns the set size.
	 * 
	 * @PREcondition -
	 * @POSTcondition The number of <E>s in the set is returned.
	 **/
	int size();

	/**
	 * Returns the set as a String.
	 * 
	 * @PREcondition -
	 * @POSTcondition Returns a String representation of this set.
	 **/
	String toString();

	/**
	 * Calculates complement of this set and set2.
	 * 
	 * @PREcondtion -
	 * @POSTcondition Returns an Set-object including all <E>s contained in this
	 *                object, but not set2.
	 **/
	SetInterface<E> complement(Set<E> set2);

	/**
	 * Calculates intersection of this set and set2.
	 * 
	 * @PREcondition -
	 * @POSTcondition Returns an Set-object including all <E>s contained
	 *                simultaneously in this Set-object and set2.
	 **/
	SetInterface<E> intersection(Set<E> set2);

	/**
	 * Calculates union of this set and set2.
	 * 
	 * @PREcondition -
	 * @POSTcondition Returns an Set-object including all <E>s contained in this
	 *                Set-object and set2.
	 **/
	SetInterface<E> union(Set<E> set2);

	/**
	 * Calculates symmetric difference of this set and set2.
	 * 
	 * @PREcondition -
	 * @POSTcondition Returns an Set-object including all <E>s not contained
	 *                simultaneously in this Set-object and set2.
	 **/
	SetInterface<E> symmetricDifference(Set<E> set2);

	/**
	 * Returns copy of Set object.
	 * 
	 * @PREcondition -
	 * @POSTcondition A copy of this object is returned.
	 **/
	Set<E> clone();
}
