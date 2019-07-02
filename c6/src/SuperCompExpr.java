/**  Limitations: the procedure must take two <code>int</code> arguments 
 * and return an <code>int</code> value that represents boolean.
 * 0 for false and 1 for true.
 */

public abstract class SuperCompExpr extends Expr {

    protected Expr e1, e2;
    protected String operator;

    /** Constructs a <code>SuperCompExpr</code> with given expressions and comparison operator
     *  @param e1 the first operand
     *  @param e2 the second operand
     */

    public SuperCompExpr(Expr e1, Expr e2){
        this.e1 = e1;
        this.e2 = e2;
        operator = "";
    }

    /** an empty constructor that allows the program to work. Causes
     * problems without it
     */
    
    public SuperCompExpr(){}




    public ValueAndCode toLLVM(){
        ValueAndCode leftExpr = e1.toLLVM();
        ValueAndCode rightExpr = e2.toLLVM();
        String reg = " = icmp " + operator + " i32 " + leftExpr.getValue() + ", " + rightExpr.getValue() + "\n";
        String val1 = NameAllocator.getTempAllocator().next();
        String val2 = NameAllocator.getTempAllocator().next();
        String code = leftExpr.getCode() + rightExpr.getCode() + val1 + reg 
        			+ val2 + " = zext i1 " + val1 + " to i32\n";
        return new ValueAndCode(val2,code);
    }

}
