/** Checks if the LHS expression is equal to the RHS
 */

public class EQExpr extends SuperCompExpr {

    /** Constructs a <code>EQExpr</code> with two expressions that are given
     *  @param e1 the first expression
     *  @param e2 the second expression
     */

    public EQExpr(Expr e1, Expr e2){
        super(e1,e2);
        operator = "eq";
    }

}
