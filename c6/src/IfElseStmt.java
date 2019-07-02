public class IfElseStmt extends Stmt {

    private Expr loop; 
    private Stmt cond;
    private Stmt elseStmt;

    /** Constructs a <code>IfElseStmt</code> with given expressions and statements
     *  @param loop the if loop
     *  @param cond the condition
     *  @param elseStmt the else condition
     */

    public IfElseStmt(Expr loop, Stmt cond, Stmt elseStmt){
        this.loop = loop;
        this.cond = cond;
        this.elseStmt = elseStmt;
    }

    public String toLLVM(){
        String ifLabel = NameAllocator.getLabelAllocator().next();
        String otherLabel = NameAllocator.getLabelAllocator().next();
        String elseLabel = NameAllocator.getLabelAllocator().next();
        ValueAndCode loopExpr = loop.toLLVM();
        String loopVal = loopExpr.getValue();
        String loopData = loopExpr.getCode();
        String reg = NameAllocator.getTempAllocator().next(); 
        String l1 = loopData + " " + reg + " = icmp ne i32 " + loopVal + ", 0\n";
        String l2 = " br i1 " + reg + ", label %" + ifLabel + ", label %" + otherLabel + "\n";
        String l3 = ifLabel + ":\n";
        String l4 = cond.toLLVM() + " br label %" + elseLabel + "\n";
        String l5 = otherLabel + ":\n";
        String l6 = elseStmt.toLLVM() + " br label %" + elseLabel + "\n";
        String l7 = elseLabel + ":\n";
        return l1 + l2 + l3 + l4 + l5 + l6 + l7;
    }

}
