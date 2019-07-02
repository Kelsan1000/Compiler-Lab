/**  Limitations: the procedure must take two <code>int</code> arguments and return an <code>int</code> value.
 */

public class SuperExpr extends Expr {

    private Expr operand1;
    private Expr operand2;
    private String operator;

    /** Constructs a <code>SuperExpr</code> with a given operands and operator.
     *  @param operand1 the name of one of the operands
     *  @param operand2 the name of the other operand
     */

    public SuperExpr(Expr operand1, Expr operand2) {
	this.operand1 = operand1;
    this.operand2 = operand2;
    }

    /** an empty constructor that allows the program to work. Causes
     * problems withot it
     */
    
    public SuperExpr() {
    
    }

    /** A method used to change the operator in the expression
     *  @param operator the type of operator to be used for the expression
     */

    public void operation(String operator) {
    this.operator = operator;
    }

    public ValueAndCode toLLVM(){
    ValueAndCode oper1 = operand1.toLLVM();
    ValueAndCode oper2 = operand2.toLLVM();
	String value = NameAllocator.getTempAllocator().next();
    String code = oper1.getCode() + oper2.getCode() + value + " = " + operator + " i32 " + oper1.getValue() + " , " + oper2.getValue() + "\n";
    return new ValueAndCode(value, code);
    }

}