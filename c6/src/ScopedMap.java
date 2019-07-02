import java.util.*;

/** A ScopedMap is similar to a Map, but with nested scopes. */
public class ScopedMap<K, V> {

	private List<Map<K,V>> scopemap;
	private int nestingLevel;

	/**
	 * makes a ScopedMap that maps no keys to values and is set to the global scope
	 * (nesting level 0)
	 */
	public ScopedMap() {
		scopemap = new ArrayList<Map<K,V>>();
		scopemap.add(new HashMap<K,V>());
		nestingLevel = 0;
	}

	/**
	 * sets the ScopedMap to a new scope nested inside the previous one; the nesting
	 * level increases by one
	 */
	public void enterScope() {
		scopemap.add(new HashMap<K,V>());
		nestingLevel++;
	}

	/**
	 * exits from the current scope back to the containing one; the nesting level,
	 * which must have been positive, decreases by one
	 */
	public void exitScope() {
		scopemap.remove(nestingLevel--);
		
	}

	/**
	 * puts the key/value pair in at the current scope; if the key is already in at
	 * the current nesting level, the new value replaces the old one; neither the
	 * key nor the value may be null
	 */
	public void putVal(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		scopemap.get(nestingLevel).put(key, value);
	}

	/**
	 * gets the value corresponding to the key, at the innermost scope for which
	 * there is one; if there is none, returns null
	 */
	public V getVal(K key) {
		V acc;
		for (int i = nestingLevel; i>=0; i--) {
			acc = scopemap.get(i).get(key);
			if (acc != null) {return acc;}
		}
		return null;
	}

	/**
	 * returns true if the key has a value at the current scope (ignoring
	 * surrounding ones)
	 */
	public boolean isLocal(K key) {
		return scopemap.get(nestingLevel).containsKey(key);
	}

	/**
	  * returns our nesting level
	  */ 
	public V getLocal(K key){
		if (isLocal(key)){
			return getVal(key);
		}
		return null;
	} 

	/** returns the current nesting level */
	public int getNestingLevel() {
		return nestingLevel;
	}

}
