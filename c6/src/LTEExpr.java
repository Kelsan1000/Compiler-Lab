/** Checks if the LHS expression is less than or equal to the RHS
 */

public class LTEExpr extends SuperCompExpr {


    /** Constructs a <code>LTEExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public LTEExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "sle";
    }

}
