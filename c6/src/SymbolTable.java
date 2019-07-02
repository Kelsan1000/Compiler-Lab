import java.util.*;

/** A <code>SymbolTable</code> creates a symbol table that maps assignment
 *  values to declared variable names
 */
public class SymbolTable {

	private static ScopedMap<String,String> table = new ScopedMap<String,String>();

	public SymbolTable(){}

	public static ScopedMap <String, String> getTable(){
		return table;
	}

}   