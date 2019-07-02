import java.util.*;

public class DeclareProc{
	private String proc;
	private List<String> params;

	/** Constructs a <code>DeclareProc</code> with specified name and body.
	 *  @param proc the procedure's name
	 *  @param params the list of procedures
	 */

	public DeclareProc(String proc, List<String> params){
		this.proc = proc;
		this.params = params;
	}

	/** Returns a <code>String</code> which is the name of the procedure.
	 */

	public String getName(){
		return proc;
	}

	/** Returns a <code>int</code> which is the amount of procedures in the 
	 *  list of procedures, params
	 */

	public int getParamSize(){
		return params.size();
	}

	public String toLLVM(){
		String code = "\ndeclare i32 @";
		code += (proc);
		code += ("(");

		if (params.size() > 0){
			code += ("i32");
		}
		for (int i = 1; i < params.size(); i++){
			code += (", i32");
		}
		code += (")\n");

		return code;
	}
}