/** Checks if the LHS expression is greater than the RHS
 */

public class GTExpr extends SuperCompExpr {


    /** Constructs a <code>GTExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public GTExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "sgt";
    }

}
