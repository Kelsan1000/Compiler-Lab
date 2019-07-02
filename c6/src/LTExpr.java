/** Checks if the LHS expression is less than the RHS
 */

public class LTExpr extends SuperCompExpr {

    /** Constructs a <code>LTExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public LTExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "slt";
    }


}
