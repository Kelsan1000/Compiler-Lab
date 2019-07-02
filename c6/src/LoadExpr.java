/**  Limitations: the procedure must take an <code>int</code>  and two <code>String<code>
 */

public class LoadExpr extends Expr {

    private String decStmt,pointer;
    private int position;

    /** Constructs a <code>LoadExpr</code> with a given operands and operator.
     *  @param decStmt the name of the variable
     *  @param position of the variable
     *  @pointer keeps track of what nesting level it is on
     */
    public LoadExpr(String decStmt, int position, String pointer) {
    assert pointer != null;
    this.decStmt = decStmt;
    this.position = position;
    this.pointer = pointer;
    }

    public ValueAndCode toLLVM(){
        if (pointer == null) {
            Compiler.error("Error: " + decStmt + " at position " +
                position + " does not exist!\n");
        }
		String value = NameAllocator.getTempAllocator().next();
		String code = value + " = load i32, i32* " + pointer + "\n";
		return new ValueAndCode(value,code);
		
	}

}