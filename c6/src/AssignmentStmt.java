/** A <code>AssignmentStmt</code> assigns an expression to a declaration name
 */

public class AssignmentStmt extends Stmt {

	private String decStmt; 
	private Expr e;
	private int position;
	private String pointer;

	/** Constructs a <code>AssignmentStmt</code> from a statement and an expression.
	 *  @param decStmt the variable name that will be assigned an expression.
	 *  @param e the expression being assigned to something.
	 *  @param position will tells us the position of our variable
	 *  @param pointer will tell us the scope level
	 */

	public AssignmentStmt(String decStmt, Expr e, int position, String pointer){
		this.decStmt = decStmt;
		this.e = e;
		this.position = position;
		this.pointer = pointer;
	}

	public String toLLVM(){
        if (pointer == null) {
            Compiler.error("Error: " + decStmt + " at position " +
                position + " does not exist!\n");
        }
		ValueAndCode expr = e.toLLVM();
		String code = expr.getCode() + " store i32 " + expr.getValue() + ", i32* " 
					+ pointer + "\n";
		return code;
		
	}
}




