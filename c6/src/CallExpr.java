/** An expression evaluated by calling a named procedure.
 *  Limitations: the procedure must take one <code>int</code> argument and return an <code>int</code> value.
 */
import java.util.*;
public class CallExpr extends Expr {

	private String procName;
	private List<Expr> argument;

	/** Constructs a <code>CallExpr</code> with a given procedure name and argument.
	 *  @param procName the name of the procedure to call
	 *  @param argument the list of arguments
	 */

	public CallExpr(String procName, List<Expr> argument){
		this.procName = procName;
		this.argument = argument;
	}

	public ValueAndCode toLLVM(){
		String value = NameAllocator.getTempAllocator().next();
		String start = "";
		String body = " ";
		body += value + " = call i32 @" + procName + "(";

		for (int i = 0; i < argument.size(); i++){
			Expr e = argument.get(i);
			ValueAndCode argValAndCode = e.toLLVM();
			start += argValAndCode.getCode();
			if (i > 0){
				body += ", ";
			}
			body += "i32 " + argValAndCode.getValue();
		}
		body += ")\n";
		String total = start + body;
		return new ValueAndCode(value,total);
	}
}
		// ValueAndCode argValAndCode = argument.toLLVM();
		// String value = NameAllocator.getTempAllocator().next();
		// String code = argValAndCode.getCode() +
		// 		"    " + value + " = call i32 @" + procName + "(i32 " + argValAndCode.getValue() + ")\n";
		// return new ValueAndCode(value, code);

