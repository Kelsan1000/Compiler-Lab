/** An expression evaluated that returns the sum of two operands.
 *  Limitations: the procedure must take two <code>int</code> arguments and return an <code>int</code> value.
 */

public class SumExpr extends SuperExpr {

    private Expr operand1;
    private Expr operand2;

    /** Constructs a <code>SumExpr</code> with two operands that are given.
     *  @param operand1 the name of one of the operands
     *  @param operand2 the name of the other operand
     */

    public SumExpr(Expr operand1, Expr operand2){
	this.operand1 = operand1;
	this.operand2 = operand2;
    }

    public ValueAndCode toLLVM(){
    SuperExpr sum = new SuperExpr(operand1,operand2);
    sum.operation("add"); 
    return sum.toLLVM();
    }
}