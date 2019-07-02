
public class WhileStmt extends Stmt {

    private Expr loop;
    private Stmt cond;

    /** Constructs a <code>WhileStmt</code> with given expression and statement
     *  @param loop the expression
     *  @param cond the second operand
     */

    public WhileStmt(Expr loop, Stmt cond){
        this.loop = loop;
        this.cond = cond;
    }

    public String toLLVM(){
        String beforeLabel = NameAllocator.getLabelAllocator().next();
        String middleLabel = NameAllocator.getLabelAllocator().next();
        String afterLabel = NameAllocator.getLabelAllocator().next();
        ValueAndCode loopExpr = loop.toLLVM();
        String loopVal = loopExpr.getValue();
        String loopData = loopExpr.getCode();
        String reg = NameAllocator.getTempAllocator().next();
        String l1 = " br label %" + beforeLabel + "\n";
        String l2 = beforeLabel + ":\n";
        String l3 = loopData + " " + reg + " = icmp ne i32 " + loopVal + ", 0\n";
        String l4 = " br i1 " + reg + ", label %" + middleLabel 
                + ", label %" + afterLabel + "\n";
        String l5 = middleLabel + ":\n";
        String l6 = cond.toLLVM() + " br label %" + beforeLabel + "\n";
        String l7 = afterLabel + ":\n";
        return l1 + l2 + l3 + l4 + l5 + l6 + l7;
    }

}
