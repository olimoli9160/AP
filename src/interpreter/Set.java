package interpreter;

public class Set<E extends Data<E>> implements SetInterface<E> {

	private List<E> dataList;

	public Set() {
		init();
	}

	public void init() {
		dataList = new List<E>();
	}
	
	public List<E> getSetData(){
		
		return dataList;
	}

	public E get() {
		if (dataList.isEmpty()) {
			return null;
		}
		return dataList.retrieve();
	}

	public SetInterface<E> add(E d) {
		dataList.insert(d);

		return this;
	}

	public SetInterface<E> delete(E d) {
		dataList.remove();

		return this;
	}

	public boolean contains(E d2) {

		return dataList.find(d2);
	}

	public boolean isEmpty() {

		return dataList.isEmpty();
	}

	public int size() {

		return dataList.size();
	}

	public SetInterface<E> complement(Set<E> set2) {
		Set<E> result = new Set<E>();
		dataList.goToFirst();

		for (int i = 0; i < size(); i++) {
			E current = dataList.retrieve();

			if (!set2.contains(current)) {
				result.add(current);
			}
			dataList.goToNext();
		}
		return result;
	}
	
	public boolean equal(Set<E> set2){
		if(!(size() == set2.size())){
			return false;
		}
		dataList.goToFirst();
		
		for(int i = 0; i < size(); i++){
			E current = dataList.retrieve();
			if(set2.contains(current)){
				dataList.goToNext();
			} else{
				return false;
			}
		}
	
		return true;
	}
	
	public boolean less(Set<E> set2){
		if(!(size() <= set2.size())){
			return false;
		}
		dataList.goToFirst();
		
		for(int i = 0; i < size(); i++){
			E current = dataList.retrieve();
			
			if(set2.contains(current)){
				dataList.goToNext();
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean greater(Set<E> set2){
		if(!(size() >= set2.size())){
			return false;
		}
		set2.dataList.goToFirst();
		
		for(int i = 0; i < set2.size(); i++){
			E current = set2.dataList.retrieve();
			if(this.contains(current)){
				set2.dataList.goToNext();
			}else{
				return false;
			}
		}
		
		return true;
	}

	public SetInterface<E> intersection(Set<E> set2) {
		Set<E> result = new Set<E>();
		dataList.goToFirst();

		for (int i = 0; i < size(); i++) {
			E current = dataList.retrieve();
			if (set2.contains(current)) {
				result.add(current);
			}
			dataList.goToNext();
		}

		return result;
	}

	public SetInterface<E> union(Set<E> set2) {
		Set<E> result = this.clone();

		set2.dataList.goToFirst();

		for (int i = 0; i < set2.size(); i++) {
			E current = set2.dataList.retrieve();
			if (result.isEmpty() || !result.contains(current)) {
				result.add(current);
			}
			set2.dataList.goToNext();
		}
		return result;
	}

	public SetInterface<E> symmetricDifference(Set<E> set2) {
		SetInterface<E> result = this.clone();
		set2.dataList.goToFirst();

		for (int i = 0; i < set2.size(); i++) {
			E current = set2.dataList.retrieve();
			if (result.contains(current)) {
				result.delete(current);
			} else {
				result.add(current);
			}
			set2.dataList.goToNext();
		}

		return result;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		dataList.goToFirst();
		for (int i = 0; i < dataList.size(); i++) {
			result.append(dataList.retrieve().toString() + " ");
			dataList.goToNext();
		}

		return result.toString();
	}

	public Set<E> clone() {
		Set<E> result = new Set<E>();
		result.dataList = (List<E>) this.dataList.clone();

		return result;
	}

}
