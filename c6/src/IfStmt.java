
public class IfStmt extends Stmt {

    private Expr loop; 
    private Stmt cond;

    /** Constructs a <code>IfStmt</code> with given expression and statement
     *  @param loop the if loop
     *  @param cond the condition
     */

    public IfStmt(Expr loop, Stmt cond){
        this.loop = loop;
        this.cond = cond;
    }

    public String toLLVM(){
        String ifLabel = NameAllocator.getLabelAllocator().next();
        String otherLabel = NameAllocator.getLabelAllocator().next();
        ValueAndCode loopExpr = loop.toLLVM();
        String loopVal = loopExpr.getValue();
        String loopData = loopExpr.getCode();
        String reg = NameAllocator.getTempAllocator().next(); 
        String l1 = loopData + " " + reg + " = icmp ne i32 " + loopVal + ", 0\n";
        String l2 = " br i1 " + reg + ", label %" + ifLabel + ", label %" + otherLabel + "\n";
        String l3 = ifLabel + ":\n";
        String l4 = cond.toLLVM() + " br label %" + otherLabel + "\n";
        String l5 = otherLabel + ":\n";
        return l1 + l2 + l3 + l4 + l5;
    }

}
