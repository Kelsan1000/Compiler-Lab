/** A <code>DeclarationStmt</code> creates a variable that can have things
 *  assigned to it.
 */

public class DeclarationStmt extends Stmt {

	private String decStmt;
	private int position;
	private String pointer;
	private String val;

	/** Constructs a <code>DeclarationStmt</code> from a string.
	 *  @param decStmt is the name of our variable
	 *  @param position is the position of our variable
	 *  @param pointer tell us the scope level
	 *  @param val is the value the register value
	 */
	public DeclarationStmt(String decStmt, int position, String pointer, String val){
		this.decStmt = decStmt;
		this.position = position;
		this.pointer = pointer;
		this.val = val;
	}

	public String toLLVM(){
		if (pointer != null) {
			Compiler.error("Error: " + decStmt + " at position " +
				position + " is an existing variable!\n");
		}
		SymbolTable.getTable().putVal(decStmt,val);
		return  " " + val + " = alloca i32\n"; 
	}
}


