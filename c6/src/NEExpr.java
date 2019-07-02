/** Checks if the LHS expression is not equal to the RHS
 */

public class NEExpr extends SuperCompExpr {


    /** Constructs a <code>NEExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public NEExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "ne";
    }

}
