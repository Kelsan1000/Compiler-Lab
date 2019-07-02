/** Checks if the LHS expression is greater than or equal to the RHS
 */

public class GTEExpr extends SuperCompExpr {


    /** Constructs a <code>GTEExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public GTEExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "sge";
    }

}
